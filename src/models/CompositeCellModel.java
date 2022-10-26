package models;

public class CompositeCellModel extends AbstractCellModel {

    private CellModel child;

    public CompositeCellModel(int id){
        super(id);
        this.child = new SimpleCellModel(id);
    }

    @Override
    public CellModel getChild() {
        return this.child;
    }


    public CompositeCellModel clone(){
        CompositeCellModel clone = (CompositeCellModel) super.clone();
        clone.child = this.child.clone();
        return clone;
    }
}
