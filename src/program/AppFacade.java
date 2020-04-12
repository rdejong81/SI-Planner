package program;

import db.MySQLConnection;
import db.Query;
import gui.LoginController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class AppFacade
{
    static MySQLConnection db;

    static public boolean ShowLogin() throws IOException, SQLException
    {
        LoginController login = new LoginController();
        login.showAndWait();
        if (login.getCancelled()) return false;

        /* todo start the rest of the program */

            return true;
        }

        static public boolean tryLogin(LoginController loginController)
        {
            try
            {
                db = new MySQLConnection(loginController.getServer(), loginController.getDatabase(), loginController.getUserName(), loginController.getPassword());

                Query q = db.selectAllRowsLike("employees","sqlLogin",loginController.getUserName());
                for (HashMap<String, Object> row : q.getRows())
                {
                    System.out.printf("Login successful: %d %s\n", row.get("id"), row.get("sqlLogin"));
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
}
