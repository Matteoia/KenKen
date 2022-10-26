package models;

import java.io.Serializable;

public interface CellModel extends Cloneable, Serializable {

    void set(String x);
    void remove();
    CellModel getChild();
    String getValue();
    int getId();
    CellModel clone();
}
