
package graphics.cells;

public interface CellView {
    int getId();
    void set(String x);
    void remove();
    String getValue();
    CellView getChild();
}
