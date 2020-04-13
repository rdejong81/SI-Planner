package program;

import data.Employee;
import data.EmployeeList;
import db.MySQLConnection;
import db.Query;
import db.SQLConnection;
import gui.LoginController;
import gui.TempInput;
import gui.TempMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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


            Query q = db.selectAllRows("employees");
            ArrayList<HashMap<String,Object>> rows = q.getRows();
            // If first logged on SQL user, add as employee.
            if(rows.isEmpty()) {
                new Employee(loginController.getUserName(),loginController.getUserName());
                System.out.printf("First application user %s created as employee.",loginController.getUserName());
                return true;
            }

            for (HashMap<String, Object> row : rows)
            {
                if(loginController.getUserName().equals(row.get("sqlLogin")))
                {
                    System.out.printf("Login successful: %d %s\n", (int) row.get("id"), (String) row.get("sqlLogin"));
                    return true;
                }
            }
            loginController.setError("Unknown application user - sql login succeeded but not allowed to use this application.");
            return false;

        } catch (Exception e)
        {
            loginController.setError(e.getMessage());
            return false;
        }

    }

    static public void showMain(){
        String[] menuOptions = {"Manage Employees", "Exit SI-Planner"};
        TempMenu menu = new TempMenu(menuOptions,"Make your choice","Main Menu");
        switch(menu.getChoice()){
           case 1 : manageEmployees(); showMain(); break;
           case 2 : return;
        }
    }

    static public void manageEmployees()
    {
        String[] menuOptions = {"Add employee", "Remove employee", "Show employees", "Return to main menu"};
        TempMenu menu = new TempMenu(menuOptions,"Make your choice","Employees");
        switch(menu.getChoice()){
            case 1 : addEmployee(); manageEmployees(); break;
            case 2 : removeEmployee(); manageEmployees(); break;
            case 3 : showEmployees(); manageEmployees(); break;
            case 4 : return;
        }
    }

    static public void addEmployee()
    {
        String password=null;
        String sqlLogin = TempInput.AskText("SQL Login name",2);
        String name = TempInput.AskText("Full name",2);
        if(db.canCreateUser()) password = TempInput.AskText("Password",8);

        try
        {
            if(password != null) {
                employees.addEmployee(new Employee(name, sqlLogin, password));
            } else {
                employees.addEmployee(new Employee(name, sqlLogin));
            }

            System.out.println("Employee added");
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    static public void showEmployees(){
        System.out.printf("\u001B[46m%-4s | %-20s | %-20s\u001B[0m\n","Id","Name","SQL Login");

        for(Employee employee : employees.getEmployees())
        {
            System.out.printf("%-4d | %-20s | %-20s\n",employee.getId(),employee.getName(),employee.getSqlLogin());
        }
        gui.TempInput.AskText("Enter key to return",0);
    }

    static public void removeEmployee(){
        showEmployees();
        int choice = TempInput.askInt("Enter employee Id number to remove",1,Integer.MAX_VALUE);
        employees.removeEmployee(choice);
    }
}
