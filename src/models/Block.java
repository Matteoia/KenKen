package models;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Block implements Iterable<CellModel> {
    private List<CellModel> cells;
    private Color color;

    public Block(Color color){
        this.color = color;
        this.cells = new LinkedList<>();
    }
    public Block(){
        this(Color.white);
    }

    public void add(CellModel c){
        cells.add(c);
    }

    public CellModel get(int index){
        return cells.get(index);
    }

    public void remove(CellModel c){
        for(int i=0; i<cells.size(); i++)
            if(cells.get(i).getId() == c.getId()){
                cells.remove(i);
                break;
            }
    }

    public int size(){
        return cells.size();
    }

    @Override
    public Iterator<CellModel> iterator() {
        return cells.iterator();
    }

    public boolean contains(CellModel c) {
        for(CellModel cell: cells)
            if(cell.getId() == c.getId())
                return true;
        return false;
    }

    public Color getColor(){
        return this.color;
    }

    @Override
    public String toString(){
        return cells.toString()+"\n";
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
