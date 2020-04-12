import db.MySQLConnection;
import db.Query;
import gui.LoginController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class AppFacade
{
    MySQLConnection db;

    public boolean LoginProcedure() throws IOException, SQLException, ClassNotFoundException
    {

        while (true)
        {
            LoginController login = new LoginController();
            login.showAndWait();
            if (login.getCancelled()) return false;

            try
            {
                db = new MySQLConnection(login.getServer(), login.getDatabase(), login.getUserName(), login.getPassword());
                break;

            } catch (Exception e)
            {
                continue;
            }

        }
        Query q = db.selectAllRows("employees");
        for (HashMap<String, Object> row : q.getRows())
        {
            System.out.printf("%d %s\n", row.get("id"), row.get("sqlLogin"));
        }

        return false;
    }
}
