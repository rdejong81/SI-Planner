package program;

import data.*;
import db.DBType;
import gui.Controller;
import gui.IGuiRunUseCase;
import javafx.scene.chart.PieChart;
import mysql.MySQLConnection;
import db.QueryResult;
import db.SQLConnection;
import login.LoginController;
import gui.TempInput;
import gui.TempMenu;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;

import static data.Customer.TABLE_CUSTOMERS_ROW_NAME;
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
                    employees.addEntity(new Employee(loginController.getUserName(), loginController.getUserName()));
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
        String[] menuOptions = {"Manage Employees", "Manage Customers", "Exit SI-Planner"};
        TempMenu menu = new TempMenu(menuOptions, "Make your choice", "Main Menu");
        switch (menu.getChoice())
        {
            case 1:
                manageEntities(employees.getEntities(),"Employee");
                showMain();
                break;
            case 2:
                manageEntities(customers.getEntities(),"Customer");
                showMain();
                break;
            case 3:
                return;
        }
    }

    static public void addEntity(Collection<DataEntity> list,String entityType)
    {

        try
        {
            String newName = TempInput.AskText("Enter new name", 2);

            if (entityType.equals("Customer"))
            {
                customers.addEntity(new Customer(newName));
            }
            if (entityType.equals("Employee"))
            {
                String newLogin = TempInput.AskText("Enter new login name", 2);
                if (db.canCreateUser())
                {
                    String newPassword = TempInput.AskText("Enter new password", 2);
                    employees.addEntity(new Employee(newName, newLogin, newPassword));
                } else employees.addEntity(new Employee(newName, newLogin));
            }

            System.out.printf("%s added", entityType);
        } catch (Exception e)
        {
            System.err.printf("Error: %s. Details: %s\n", e.toString(), e.getMessage());
        }

    }

    static public <T extends DataEntity> void showDataEntities(Collection<T> list)
    {
        boolean firstRow = true;

        for (DataEntity entity : list)
        {
            if (firstRow)
            {
                if (entity instanceof Customer)
                    System.out.printf("\u001B[46m %-4s | %-20s | %-40s", "ID", "Name", "Employees");
                if (entity instanceof Employee)
                    System.out.printf("\u001B[46m %-4s | %-20s | %-20s | %-40s", "ID", "Name", "Sql login", "Customers");
                System.out.printf("\u001B[0m\n");
            }

            if (entity instanceof Customer)
            {
                ArrayList<String> names = new ArrayList<>();
                for (DataEntity employee : ((Customer) entity).getEmployees())
                    names.add(((Employee) employee).getName());
                System.out.printf(" %-4s | %-20s | %-40s\n", entity.getId(), ((Customer) entity).getName(), String.join(",", names));
            }
            if (entity instanceof Employee)
            {
                ArrayList<String> names = new ArrayList<>();
                for (DataEntity customer : ((Employee) entity).getCustomers())
                    names.add(((Customer) customer).getName());
                System.out.printf(" %-4s | %-20s | %-20s | %-40s\n",
                        entity.getId(),
                        ((Employee) entity).getName(),
                        ((Employee) entity).getSqlLogin(),
                        String.join(",", names));
            }

            firstRow = false;
        }

    }

    static public DataEntity selectDataEntity(Collection<DataEntity> entities)
    {
        if (entities.isEmpty()) return null;
        String entityType="";

        ArrayList<String> choices = new ArrayList<>();

        for (DataEntity entity : entities)
        {
            if(entityType.length()==0) entityType = entity.getClass().getSimpleName();

            switch (entity.getClass().getSimpleName())
            {
                case "Employee":
                    choices.add(String.format("%s (%d)", ((Employee) entity).getName(), entity.getId()));
                    break;
                case "Customer":
                    choices.add(String.format("%s (%d)", ((Customer) entity).getName(), entity.getId()));
                    break;
            }

        }

        TempMenu choice = new TempMenu(
                choices.toArray(String[]::new),
                "Choose item",
                "Select " + entityType);

        Iterator<DataEntity> it = entities.iterator();

        for (int i = 0; i < entities.size(); i++)
        {
            DataEntity entity = it.next();
            if (i + 1 == choice.getChoice()) return entity;
        }

        return null;
    }

    static public void editDataEntity(DataEntity entity)
    {
        if (entity == null) return;

        try
        {
            if (entity instanceof Customer)
            {
                System.out.printf("Current name: %s\n", ((Customer) entity).getName());
                String newName = TempInput.AskText("Enter new name (press enter to keep the same)", 0);
                if (newName.length() > 1) ((Customer) entity).setName(newName);

            }

            if (entity instanceof Employee)
            {
                System.out.printf("Current name: %s\n", ((Employee) entity).getName());
                String newName = TempInput.AskText("Enter new name (press enter to keep the same)", 0);
                if (newName.length() > 1) ((Employee) entity).setName(newName);

                System.out.printf("Current SQL login name: %s\n", ((Employee) entity).getSqlLogin());
                String newLogin = TempInput.AskText("Enter new SQL login name", 0);
                if (newLogin.length() > 1) ((Employee) entity).setSqlLogin(newLogin);
            }

            System.out.printf("%s ID %d modifications complete\n", entity.getClass().getSimpleName(), entity.getId());
        } catch (Exception e)
        {
            System.err.printf("Error: %s. Details: %s\n Modifications cancelled.\n", e.toString(), e.getMessage());
        }

    }

    static public void removeDataEntity(Collection<DataEntity> list,String entityType)
    {
        showDataEntities(list);
        int choice = TempInput.askInt("Enter Id number to remove", 1, Integer.MAX_VALUE);
        switch (entityType){
            case "Employee": employees.removeEntity(choice);
            case "Customers": employees.removeEntity(choice);
        }

        gui.TempInput.AskText("Enter key to return", 0);
    }

    static public void manageEntity(DataEntity entity)
    {
        if (entity instanceof Employee)
        {
            String[] menuOptions = {"Show customers", "Add customers", "Remove customers", "Return"};
            String title = String.format("Manage %s : %s",
                    entity.getClass().getSimpleName().toLowerCase(),
                    ((Employee) entity).getName());
            TempMenu choice = new TempMenu(menuOptions,"Make your choice",title);

            switch (choice.getChoice()){
                case 1:
                    showDataEntities(((Employee) entity).getCustomers());
                    gui.TempInput.AskText("Enter key to return", 0);
                    manageEntity(entity);
                    break;
                case 2:
                    ((Employee) entity).addCustomer((Customer)selectDataEntity(customers.getEntities()));
                    manageEntity(entity);
                    break;
            }
        }

    }

    static public void manageEntities(Collection<DataEntity> entities,String entityType)
    {
        String entityTypePlural = entityType + "s";
        String[] menuOptions = {"Add " + entityType,
                "Remove " + entityType,
                "Show " + entityTypePlural,
                "Manage " + entityType,
                "Edit " + entityType, "Return to main menu"};
        TempMenu menu = new TempMenu(menuOptions, "Make your choice", entityTypePlural);
        switch (menu.getChoice())
        {
            case 1:
                addEntity(entities,entityType);
                manageEntities(entities,entityType);
                break;
            case 2:
                removeDataEntity(entities,entityType);
                manageEntities(entities,entityType);
                break;
            case 3:
                showDataEntities(entities);
                gui.TempInput.AskText("Enter key to return", 0);
                manageEntities(entities,entityType);
                break;
            case 5:
                editDataEntity(selectDataEntity(entities));
                gui.TempInput.AskText("Enter key to return", 0);
                manageEntities(entities,entityType);
                break;
            case 4:
                manageEntity(selectDataEntity(entities));
                manageEntities(entities,entityType);
                break;
            case 6:
                return;
        }


    }
}
