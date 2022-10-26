package graphics.cells;

import javax.swing.*;
import java.awt.*;

public class CompositeCellView extends AbstractCellView {
    private SimpleCellView constraint;

    public CompositeCellView(int id, int parentSize, int level){
        super(id, parentSize, level);
        this.constraint = new SimpleCellView(0, (parentSize/level), 3);
        this.constraint.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(constraint);
    }

    public CompositeCellView(AbstractCellView cella){
        this(cella.id, cella.parentSize, cella.level);
        this.set(cella.getValue());
        if(cella instanceof CompositeCellView)
            if(cella.getChild() != null){
                this.constraint.set(cella.getChild().getValue());
                this.constraint.setBackground(((AbstractCellView)cella.getChild()).getBackground());
            }
    }

    @Override
    public void set(String x) {
        this.setText(x);
    }


    @Override
    public void remove() {
        this.setText("");
    }

    @Override
    public String getValue() {
        return this.getText();
    }

    @Override
    public CellView getChild() {
        return constraint;
    }


    @Override
    public String toString(){
        return super.toString()+" composita";
    }

}
