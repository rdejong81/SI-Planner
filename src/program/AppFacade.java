package program;

import data.EmployeeList;
import db.MySQLConnection;
import db.Query;
import db.SQLConnection;
import gui.LoginController;
import gui.TempMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class AppFacade
{
    static public SQLConnection db;
    static public EmployeeList employees;

    static public boolean ShowLogin() throws IOException, SQLException
    {
        LoginController login = new LoginController();
        login.showAndWait();
        if (login.getCancelled()) return false;

        employees = new EmployeeList();

        return true;
    }

    static public boolean tryLogin(LoginController loginController)
    {
        try
        {
            switch (loginController.getDBType())
            {
                case MYSQL:
                    db = new MySQLConnection(loginController.getServer(), loginController.getDatabase(), loginController.getUserName(), loginController.getPassword());
            }

            Query q = db.selectAllRowsLike("employees", "sqlLogin", loginController.getUserName());
            for (HashMap<String, Object> row : q.getRows())
            {
                System.out.printf("Login successful: %d %s\n", (int) row.get("id"), (String) row.get("sqlLogin"));
                return true;
            }
            loginController.setError("Unknown application user - sql login succeeded but not allowed to use this application.");
            return false;

        } catch (Exception e)
        {
            loginController.setError(e.getMessage());
            return false;
        }

    }

    static public void manageEmployees()
    {
        //TempMenu menu = new TempMenu(["t","t"])
    }
}
