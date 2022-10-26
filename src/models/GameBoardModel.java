package models;

import backTracking.Backtracking;
import handlers.constraintHandler.OperationHandler;
import handlers.constraintHandler.SumHandler;
import graphics.gameBoards.GameBoardView;
import utils.Direction;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GameBoardModel implements Cloneable, Serializable {
    private static final long serialVersionUID = 7829136421241571165L;
    private List<CellModel> cells;
    private List<Block> blocks;
    private Color[] colors = {Color.red, Color.cyan, Color.magenta, Color.gray, Color.blue, Color.yellow, Color.black, Color.green, Color.pink};
    private int currColor;
    private int level;
    private GameBoardView view;
    private boolean creation, mustCheck;
    private CellModel pointedCell;
    private KenKenBackTracking backTracking;

    public GameBoardModel(int level){
        this.blocks = new LinkedList<>();
        this.cells = new LinkedList<>();
        int k = 0;
        this.currColor = 0;
        this.level = level;
        for(int i=0; i<level; i++){
            for(int j=0; j<level; j++){
                this.cells.add(new SimpleCellModel(k));
                k++;
            }
        }
        this.pointedCell = this.cells.get(0);
    }

    @Override
    public GameBoardModel clone(){
        GameBoardModel clone;
        try {
            clone = (GameBoardModel) super.clone();
            clone.cells = new LinkedList<>();
            clone.blocks = new LinkedList<>();
            for(int i=0; i<cells.size(); i++){
                    clone.cells.add(cells.get(i).clone());
            }
            for(int i=0; i<blocks.size(); i++){
                clone.blocks.add(new Block());
                clone.blocks.get(i).setColor(blocks.get(i).getColor());
                for(int j=0; j<blocks.get(i).size(); j++) {
                    clone.blocks.get(i).add(clone.cells.get(blocks.get(i).get(j).getId()));
                }
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }


    public boolean isInUseCell(CellModel cell) {
        for(Block block: blocks)
            if(block.contains(cell))
                return true;
        return false;
    }

    public void setView(GameBoardView view) {
        this.view = view;
    }

    public void delete(){
        for(Block b : blocks)
            if(b.contains(pointedCell)){
                if(b.get(0).getId() == pointedCell.getId()){
                    blocks.remove(b);
                    cells.set(pointedCell.getId(), new SimpleCellModel(pointedCell.getId()));
                    cells.get(pointedCell.getId()).remove();
                    this.view.deleteBlock(b);
                    break;
                }if(b.get(b.size()-1).getId() == pointedCell.getId()){
                    b.remove(pointedCell);
                    cells.get(pointedCell.getId()).remove();
                    this.view.deleteFromBlock(pointedCell);
                    break;
                }
            }
    }

    public void setpointedCell(Direction direction){
        switch(direction){
            case UP -> {
                if(pointedCell.getId()-level >= 0)
                    this.pointedCell = cells.get(pointedCell.getId()-level);
            }
            case DOWN -> {
                if(pointedCell.getId()+level < cells.size())
                    this.pointedCell = cells.get(pointedCell.getId()+level);
            }
            case LEFT -> {
                if(pointedCell.getId()-1 >= 0)
                    this.pointedCell = cells.get(pointedCell.getId()-1);
            }
            case RIGHT -> {
                if(pointedCell.getId()+1 < cells.size())
                    this.pointedCell = cells.get(pointedCell.getId()+1);
            }
        }

        this.view.setPointedCell(this.pointedCell.getId(),  isComposite(this.pointedCell));
        if(creation){
            addToBlock();
        }
    }

    public void checkConstraint(){
        for(Block b: blocks) {
            if (constraintSatisfied(b))
                view.constraintOk(b);
            else
                view.constraintError(b);
        }
    }

    private boolean isComposite(CellModel pointedCell) {
        return cells.get(pointedCell.getId()) instanceof CompositeCellModel;
    }


    public void startBlock(){
        if (!isInUseCell(pointedCell)) {
            Block block = new Block(colors[currColor]);
            currColor = (currColor+1)%colors.length;
            cells.remove(pointedCell.getId());
            cells.add(pointedCell.getId(), new CompositeCellModel(pointedCell.getId()));
            block.add(cells.get(pointedCell.getId()));
            this.blocks.add(block);
            this.view.startBlock(block, cells.get(pointedCell.getId()));
            this.creation = true;
        }
    }

    public void setConstraint() {
        if(isComposite(pointedCell)){
            CellModel currCell = cells.get(pointedCell.getId());
            String constraint = "";
            while(constraint != null &&!isValidConstraint(constraint)){
                constraint = JOptionPane.showInputDialog("Inserisci vincolo", "1,+");
            }
            currCell.getChild().set(constraint);
            this.view.setConstraint(currCell);
        }
    }

    public boolean isValidConstraint(String constraint){
        String[] data = constraint.split(",");
        try{
            int n = Integer.parseInt(data[0]);
            String op = data[1];
           if(n < 0)
               return false;
           if(op.equals("+") || op.equals("/") || op.equals("x") || op.equals("-"))
               return true;
        } catch(NumberFormatException | IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    public void addToBlock(){
        if(!isInUseCell(pointedCell)){
            this.blocks.get(blocks.size()-1).add(this.cells.get(pointedCell.getId()));
            this.view.addToBlock(this.blocks.get(blocks.size()-1), this.cells.get(pointedCell.getId()));
        }else{
            endCreate();
        }
    }

    public void endCreate() {
        this.creation = false;
    }

    public void setValue(String n) {
        if(Integer.parseInt(n) <= level){
            this.pointedCell.set(n);
            this.view.setValue(this.pointedCell);
            if(mustCheck)
                checkConstraint();
        }
    }

    public void setMustCheck() {
        this.mustCheck = !this.mustCheck;
        if(mustCheck)
            this.checkConstraint();
        else
            for(Block b: blocks)
                view.resetConstraint(b);
    }

    public boolean constraintSatisfied(Block block){
        List<Integer> valori = new LinkedList<>();;
        String operazione = "";
        int totale = 0;
        OperationHandler handler = new SumHandler();

        CellModel firstCell = block.get(0);
        if(firstCell.getValue() != null && !firstCell.getValue().isBlank())
            valori.add(Integer.parseInt(firstCell.getValue()));

        if(firstCell.getChild().getValue() != null){
            operazione = firstCell.getChild().getValue().split(",")[1];
            totale = Integer.parseInt(firstCell.getChild().getValue().split(",")[0]);
        }

        for(int j=1; j<block.size(); j++){
            CellModel pointedCell = block.get(j);
            if(pointedCell.getValue() != null && !pointedCell.getValue().isBlank())
                valori.add(Integer.parseInt(pointedCell.getValue()));
        }
        int risultato = -1;
        if(block.size() == valori.size())
            risultato = handler.handle(valori, operazione);
        return risultato == totale;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< cells.size(); i++){
            sb.append(cells.get(i)).append(" ");
            if(i!=0 && i%level==0)
                sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof GameBoardModel model))
            return false;
        for(int i=0; i<cells.size(); i++)
            if(!this.cells.get(i).equals(model.cells.get(i)))
                return false;
        return true;
    }


    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        cells = (List<CellModel>) aInputStream.readObject();
        blocks = (List<Block>)aInputStream.readObject();
        level = (Integer) aInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeObject(cells);
        aOutputStream.writeObject(blocks);
        aOutputStream.writeObject(level);

    }

    public void solve(int totSoluzioni) {
        backTracking = new KenKenBackTracking(this, totSoluzioni);
        backTracking.resolve();
    }

    public void showNextSolution() {
        GameBoardModel newModel = backTracking.getNext();
        newModel.setView(this.view);
        newModel.view.update(newModel.blocks);
    }

    public void showPrevSolution(){
        GameBoardModel newModel = backTracking.getPrev();
        newModel.setView(this.view);
        newModel.view.update(newModel.blocks);
    }

    public static class KenKenBackTracking extends Backtracking<Integer, Integer>{
        private GameBoardModel model;
        private List<GameBoardModel> solutions;
        private int nSolutions;
        private int currSol;

        public KenKenBackTracking(GameBoardModel model, int nSolutions){
            this.model = model.clone();
            this.solutions = new LinkedList<>();
            this.solutions.add(model.clone());
            this.nSolutions = nSolutions+1;
            this.currSol = 0;
        }

        @Override
        protected boolean assignable(Integer ps, Integer s) {
            String[][] mat = new String[model.level][model.level];
            int k=0;
            for(int i=0; i< mat.length; i++)
                for(int j=0; j< mat.length; j++){
                    mat[i][j] = model.cells.get(k).getValue();
                    k++;
                }
            return rowCheck(mat, ps, s) && columnCheck(mat, ps, s);
        }

        private boolean rowCheck(String[][] mat, Integer ps, Integer s){
            int y = ps% model.level;
            for(int i=0; i<model.level; i++)
                if(mat[i][y]!=null && mat[i][y].equals(String.valueOf(s)))
                    return false;
            return true;
        }

        private boolean columnCheck(String[][] mat, Integer ps, Integer s){
            int x = ps/model.level;
            for(int j=0; j<model.level; j++)
                if(mat[x][j] != null && mat[x][j].equals(String.valueOf(s)))
                    return false;
            return true;
        }

        @Override
        protected void assign(Integer ps, Integer s) {
            model.cells.get(ps).set(String.valueOf(s));
        }

        @Override
        protected void deassign(Integer ps) {
            model.cells.get(ps).remove();
        }

        @Override
        protected void addSolution() {
            solutions.add(model.clone());
        }

        @Override
        protected boolean existSolution(Integer ps) {
            for(Block b: model.blocks){
                if(!model.constraintSatisfied(b))
                    return false;
            }
            return true;
        }

        @Override
        protected boolean lastSolution() {
            return solutions.size() == nSolutions;
        }

        @Override
        protected List<Integer> choicePoints() {
            List<Integer> ris = new LinkedList<>();
            for(int i=0; i<model.cells.size(); i++){
                if(model.isInUseCell(model.cells.get(i))){
                    if(model.cells.get(i).getValue() == null)
                        ris.add(i);
                    if(model.cells.get(i).getValue() != null && model.cells.get(i).getValue().isBlank())
                        ris.add(i);
                }
            }
            return ris;
        }

        @Override
        protected Collection<Integer> choices(Integer ps) {
            List<Integer> ris = new LinkedList<>();
            for(int i=0; i<model.level; i++)
                if(assignable(ps, i+1))
                    ris.add(i+1);
            return ris;
        }

        @Override
        protected void resolve() {
            List<Integer> ps = choicePoints();
            test(ps, ps.get(0));
        }

        public GameBoardModel getNext(){
            if(currSol+1 < solutions.size()){
                currSol++;
                return solutions.get(currSol);
            }
            return solutions.get(currSol);
        }

        public GameBoardModel getPrev(){
            if(currSol-1 >= 0){
                currSol--;
                return solutions.get(currSol);
            }
            return solutions.get(currSol);
        }
    }
}