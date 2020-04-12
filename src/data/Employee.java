package data;

public class Employee
{
    private String name;
    private String sqlLogin;
    private int id;

    Employee(int id,String name, String sqlLogin)
    {
        this.id = id;
        this.name = name;
        this.sqlLogin = sqlLogin;
    }

    public String getName()
    {
        return name;
    }

    public String getSqlLogin()
    {
        return sqlLogin;
    }

    public int getId()
    {
        return id;
    }
}
