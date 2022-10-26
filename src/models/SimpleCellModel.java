package models;

public class SimpleCellModel extends AbstractCellModel {
    public SimpleCellModel(int id){
        super(id);
    }

    @Override
    public CellModel getChild(){
        throw new UnsupportedOperationException();
    }
}
