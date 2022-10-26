
package graphics.cells;


public class SimpleCellView extends AbstractCellView {
    public SimpleCellView(int id, int parentSize, int level){
        super(id, parentSize, level);
    }

    public SimpleCellView(AbstractCellView cella) {
        this(cella.id, cella.parentSize, cella.level);
        this.set(cella.getValue());
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
        throw new UnsupportedOperationException();
    }

}
