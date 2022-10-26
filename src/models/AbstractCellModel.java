package models;

public abstract class AbstractCellModel implements CellModel {
    private String value;
    private int id;

    public AbstractCellModel(int id){
        this.id = id;
        this.value = "";
    }

    @Override
    public int getId(){
        return this.id;
    }

    public AbstractCellModel clone(){
        AbstractCellModel clone = null;
        try{
            clone = (AbstractCellModel) super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }

    @Override
    public void set(String x) {
        this.value = x;
    }

    @Override
    public void remove() {
        this.value = "";
    }


    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public abstract CellModel getChild();

    @Override
    public String toString(){
        return getValue();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof AbstractCellModel model))
            return false;
        if(this.value == null && model.value == null)
            return true;
        if(this.value != null && model.value != null)
            return this.value.equals(model.value);
        return false;
    }
}
