package graphics.gameBoards;


import graphics.cells.AbstractCellView;
import graphics.cells.CompositeCellView;
import graphics.cells.SimpleCellView;
import models.Block;
import models.CellModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameBoardView extends JPanel {
    private JPanel grid;
    private AbstractCellView prevCell;

    public GameBoardView(int parentSize, int level){
        super(new BorderLayout());
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.grid = new JPanel(new GridLayout(level, level));
        for(int i=0; i<(level*level); i++){
            this.grid.add(new SimpleCellView(i, parentSize, level));
        }
        this.add(grid, BorderLayout.CENTER);
        prevCell = new SimpleCellView(0, parentSize, level);
        setPointedCell(prevCell.getId(), false);
    }



    public void startBlock(Block block, CellModel cellModel){
        AbstractCellView cellView = new CompositeCellView((AbstractCellView) this.grid.getComponents()[cellModel.getId()]);
        cellView.getDocument().putProperty("id", String.valueOf(cellModel.getId()));
        ((AbstractCellView)cellView.getChild()).getDocument().putProperty("id", "child:"+cellModel.getId());
        this.grid.remove(cellModel.getId());
        this.grid.add(cellView, cellModel.getId());
        this.prevCell = cellView;
        ((AbstractCellView)this.grid.getComponents()[cellModel.getId()]).setBorder(BorderFactory.createLineBorder(block.getColor(), 3));
        ((AbstractCellView)this.grid.getComponents()[cellModel.getId()]).set(cellModel.getValue());
        ((AbstractCellView)this.grid.getComponents()[cellModel.getId()]).getChild().set(cellModel.getChild().getValue());
        this.revalidate();this.repaint();
    }


    public void setConstraint(CellModel pointedCell) {
        ((AbstractCellView)this.grid.getComponents()[pointedCell.getId()]).getChild().set(pointedCell.getChild().getValue());
        this.prevCell.getChild().set(pointedCell.getChild().getValue());
    }

    public void addToBlock(Block block, CellModel cellModel) {
        ((AbstractCellView)this.grid.getComponents()[cellModel.getId()]).setBorder(BorderFactory.createLineBorder(block.getColor(), 3));
        ((AbstractCellView)this.grid.getComponents()[cellModel.getId()]).set(cellModel.getValue());
        this.revalidate();this.repaint();
        this.prevCell = ((AbstractCellView)this.grid.getComponents()[cellModel.getId()]);
    }


    public void constraintError(Block block) {
        int id = block.get(0).getId();
        ((AbstractCellView)((AbstractCellView)this.grid.getComponents()[id]).getChild()).setBackground(Color.red);
    }

    public void constraintOk(Block block) {
        int id = block.get(0).getId();
        ((AbstractCellView)((AbstractCellView)this.grid.getComponents()[id]).getChild()).setBackground(Color.green);
    }

    public void resetConstraint(Block block) {
        int id = block.get(0).getId();
        ((AbstractCellView)((AbstractCellView)this.grid.getComponents()[id]).getChild()).setBackground(Color.white);
    }

    public void setPointedCell(int id, boolean composite) {
        //Rimuovo lo stile imposto nella vecchia cella
        this.grid.remove(prevCell.getId());
        //Ripristino lo stile originale
        this.grid.add(prevCell, prevCell.getId());
        //Aggiorno la cella precedente
        prevCell = ((AbstractCellView)this.grid.getComponents()[id]);
        //Creo la nuova grafica
        AbstractCellView newCell;
        if(composite)
            newCell = new CompositeCellView(((AbstractCellView)this.grid.getComponents()[id]));
        else
            newCell = new SimpleCellView(((AbstractCellView)this.grid.getComponents()[id]));
        this.grid.remove(id);
        this.grid.add(newCell, id);
        ((AbstractCellView)this.grid.getComponents()[id]).setBorder(BorderFactory.createLineBorder(Color.black, 3));
        this.revalidate();this.repaint();
    }

    public void deleteBlock(Block b) {
        for(CellModel c: b){
            deleteFromBlock(c);
        }
    }

    public void deleteFromBlock(CellModel pointedCell) {
        AbstractCellView oldCell = (AbstractCellView) this.grid.getComponents()[pointedCell.getId()];
        this.grid.remove(oldCell.getId());
        AbstractCellView newCell = new SimpleCellView(oldCell);
        newCell.set("");
        this.grid.add(newCell, newCell.getId());
        this.prevCell = newCell;
        this.revalidate();this.repaint();
    }

    public void setValue(CellModel selectedCell) {
        ((AbstractCellView)this.grid.getComponents()[selectedCell.getId()]).set(selectedCell.getValue());
        this.prevCell.set(selectedCell.getValue());
        this.revalidate();this.repaint();
    }

    public void update(List<Block> blocks){
        for(Block b: blocks){
            startBlock(b, b.get(0));
            for(int i=1; i<b.size(); i++)
                addToBlock(b, b.get(i));
        }
    }
}
