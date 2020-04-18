package program;

import data.*;
import db.DBType;
import gui.Controller;
import gui.IGuiRunUseCase;
import mysql.MySQLConnection;
import db.QueryResult;
import db.SQLConnection;
import login.LoginController;
import gui.TempInput;
import gui.TempMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static data.Employee.*;
import static db.QueryResult.*;

public class AppFacade
{
    static public SQLConnection db;
    static public EmployeeList employees;
    static public CustomerList customers;

    static public boolean ShowLogin() throws IOException, SQLException
    {
        HashMap<String, Enum> dbTypes = new HashMap<>();
        dbTypes.put("MySQL", DBType.MYSQL);
        LoginController login = new LoginController(new TryLoginCallback(), dbTypes);
        login.showAndWait();
        if (login.getCancelled()) return false;

        // load data
        customers = new CustomerList();

        return true;
    }

    static class TryLoginCallback implements IGuiRunUseCase
    {

        @Override
        public Boolean runUseCase(Controller window)
        {
            LoginController loginController = (LoginController) window;

            try
            {
                try
                {
                    switch ((DBType) loginController.getDBType())
                    {
                        case MYSQL:
                            db = new MySQLConnection(loginController.getServer(), loginController.getDatabase(), loginController.getUserName(), loginController.getPassword());
                            break;

                    }
                } catch (java.lang.NullPointerException e)
                {
                    throw new Exception("No database type chosen.");
                }

                employees = new EmployeeList(); // load employees
                // If first logged on SQL user, add as employee.
                if (employees.getEntities().isEmpty())
                {
                    HashMap<String, Object> newData = new HashMap<>();
                    newData.put(TABLE_EMPLOYEES_ROW_SQLLOGIN, loginController.getUserName());
                    newData.put(TABLE_EMPLOYEES_ROW_NAME, loginController.getUserName());

                    employees.addEntity(new Employee(newData));
                    System.out.printf("First application user %s created as employee.\n", loginController.getUserName());
                    return true;
                }

                for (DataEntity employee : employees.getEntities())
                {
                    if (loginController.getUserName().equals(((Employee) employee).getSqlLogin()))
                    {
                        System.out.printf("Login successful: %d %s\n", employee.getId(), ((Employee) employee).getSqlLogin());
                        return true;
                    }
                }
                loginController.setError("Unknown application user - sql login succeeded but not known as employee to use this application.");
                return false;

            } catch (Exception e)
            {
                loginController.setError(e.getMessage());
                return false;
            }
        }
    }

    static public void showMain()
    {
        String[] menuOptions = {"Manage Employees", "Exit SI-Planner"};
        TempMenu menu = new TempMenu(menuOptions, "Make your choice", "Main Menu");
        switch (menu.getChoice())
        {
            case 1:
                manageEntities(employees);
                showMain();
                break;
            case 2:
                manageEntities(customers);
                showMain();
                break;
            case 3:
                return;
        }
    }

    static public void addEntity(DataEntityList list)
    {
        HashMap<String, Object> newEntityData;
        Map<String, Integer> columns = list.getColumns();
        newEntityData = new HashMap<>();

        for (String column : columns.keySet())
        {

            switch (columns.get(column))
            {
                case COL_INTEGER:
                case COL_BIGINT:
                    if (column.equals("id")) break;
                    Integer intVal = TempInput.askInt(String.format("Fill in number %s", column), 0, Integer.MAX_VALUE);
                    newEntityData.put(column, intVal);
                    break;
                default:
                    String strVal = TempInput.AskText(String.format("Fill in %s", column), 2);
                    newEntityData.put(column, strVal);
                    break;
            }


        }

        try
        {
            if (list instanceof CustomerList)
            {
                list.addEntity(new Customer(newEntityData));
            }
            if (list instanceof EmployeeList)
            {
                list.addEntity(new Employee(newEntityData));
            }

            System.out.printf("%s added", list.getSingularEntityName());
        } catch (Exception e)
        {
            System.err.printf("Error: %s. Details: %s\n", e.toString(), e.getMessage());
        }

    }

    static public void showDataEntities(DataEntityList list)
    {
        Collection<DataEntity> entities = list.getEntities();
        boolean firstRow = true;

        for (DataEntity entity : entities)
        {
            boolean firstValue = true;
            boolean firstRowValue = true;

            if (firstRow)
            {
                System.out.printf("\u001B[46m");
                for (String columnName : list.getColumns().keySet())
                {
                    if (!firstRowValue) System.out.printf(" | ");
                    switch (list.getColumns().get(columnName))
                    {
                        case COL_INTEGER:
                            if (columnName.length() < 5)
                            {
                                System.out.printf("%-4s", columnName);
                                break;
                            }
                        default:
                            System.out.printf("%-20s", columnName);
                            break;
                    }
                    firstRowValue = false;
                }
                System.out.printf("\u001B[0m\n");
            }

            for (String columnName : entity.getColumns().keySet())
            {
                Object columnValue = entity.getValues().get(columnName);
                int columnType = entity.getColumns().get(columnName);

                if (!firstValue) System.out.printf(" | ");
                switch (columnType)
                {
                    case COL_INTEGER:
                    case COL_BIGINT:
                        if (columnName.length() < 5)
                        {
                            System.out.printf("%-4d", columnValue);
                            break;
                        }
                    case COL_VARCHAR:
                    case COL_MEDIUMTEXT:
                        System.out.printf("%-20s", columnValue);
                        break;
                }
                firstValue = false;

            }

            System.out.println();
            firstRow = false;
        }

        //gui.TempInput.AskText("Enter key to return",0);
    }

    static public void removeDataEntity(DataEntityList list)
    {
        showDataEntities(list);
        int choice = TempInput.askInt("Enter Id number to remove", 1, Integer.MAX_VALUE);
        list.removeEntity(choice);

        gui.TempInput.AskText("Enter key to return", 0);
    }

    static public void manageEntities(DataEntityList entities)
    {
        String[] menuOptions = {"Add " + entities.getSingularEntityName().toLowerCase(),
                "Remove " + entities.getSingularEntityName().toLowerCase(),
                "Show " + entities.getSingularEntityName().toLowerCase(), "Return to main menu"};
        TempMenu menu = new TempMenu(menuOptions, "Make your choice", entities.getPluralEntityName());
        switch (menu.getChoice())
        {
            case 1:
                addEntity(entities);
                manageEntities(entities);
                break;
            case 2:
                removeDataEntity(entities);
                manageEntities(entities);
                break;
            case 3:
                showDataEntities(entities);
                gui.TempInput.AskText("Enter key to return", 0);
                manageEntities(entities);
                break;
            case 4:
                return;
        }


    }
}
