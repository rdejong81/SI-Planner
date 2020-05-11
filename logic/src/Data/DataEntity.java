package Data;

public abstract class DataEntity
{
    private int id;

    protected DataEntity(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }

}
