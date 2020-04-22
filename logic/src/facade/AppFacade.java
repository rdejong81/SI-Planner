package facade;

import data.*;

import java.util.*;

import static data.DataEntityList.allCustomers;
import static data.DataEntityList.allEmployees;

public class AppFacade
{

    static public void showMain(IConsoleIO consoleIO,ISQLConnection sqlConnection)
    {
        String[] menuOptions = {"Manage Employees", "Manage Customers", "Exit SI-Planner"};
        int choice = consoleIO.chooseFromMenu(menuOptions, "Make your choice", "Main Menu");
        switch (choice)
        {
            case 1:
                manageEntities(consoleIO,sqlConnection,allEmployees);
                showMain(consoleIO,sqlConnection);
                break;
            case 2:
                manageEntities(consoleIO,sqlConnection,allCustomers);
                showMain(consoleIO,sqlConnection);
                break;
            case 3:
                break;
        }
    }

    static public <T extends DataEntity> void addEntity(DataEntityList<T> list,IConsoleIO consoleIO, ISQLConnection sqlConnection)
    {

        try
        {
            String newName = consoleIO.AskText("Enter new name", 2);

            if (list.getSingularEntityName().equals("Customer"))
            {
                list.addEntity((T)new Customer(sqlConnection,newName));
            }
            if (list.getSingularEntityName().equals("Employee"))
            {
                String newLogin = consoleIO.AskText("Enter new login name", 2);
                if (sqlConnection.canCreateUser())
                {
                    String newPassword = consoleIO.AskText("Enter new password", 2);
                    list.addEntity((T)new Employee(sqlConnection,newName, newLogin, newPassword));
                } else list.addEntity((T)new Employee(sqlConnection,newName, newLogin));
            }

            System.out.printf("%s added", list.getSingularEntityName());
        } catch (Exception e)
        {
            System.err.printf("Error: %s. Details: %s\n", e.toString(), e.getMessage());
        }

    }

    static public <T extends DataEntity> void showDataEntities(Collection<T> list)
    {
        boolean firstRow = true;

        for (T entity : list)
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

    static public <T extends DataEntity> DataEntity selectDataEntity(IConsoleIO consoleIO, DataEntityList<T> entities)
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

        int choice = consoleIO.chooseFromMenu(
                choices.toArray(String[]::new),
                "Choose item",
                "Select " + entityType);

        Iterator<T> it = entities.iterator();

        for (int i = 0; i < entities.size(); i++)
        {
            DataEntity entity = it.next();
            if (i + 1 == choice) return entity;
        }

        return null;
    }

    static public void editDataEntity(IConsoleIO consoleIO,DataEntity entity)
    {
        if (entity == null) return;

        try
        {
            if (entity instanceof Customer)
            {
                System.out.printf("Current name: %s\n", ((Customer) entity).getName());
                String newName = consoleIO.AskText("Enter new name (press enter to keep the same)", 0);
                if (newName.length() > 1) ((Customer) entity).setName(newName);

            }

            if (entity instanceof Employee)
            {
                System.out.printf("Current name: %s\n", ((Employee) entity).getName());
                String newName = consoleIO.AskText("Enter new name (press enter to keep the same)", 0);
                if (newName.length() > 1) ((Employee) entity).setName(newName);

                System.out.printf("Current SQL login name: %s\n", ((Employee) entity).getSqlLogin());
                String newLogin = consoleIO.AskText("Enter new SQL login name", 0);
                if (newLogin.length() > 1) ((Employee) entity).setSqlLogin(newLogin);
            }

            System.out.printf("%s ID %d modifications complete\n", entity.getClass().getSimpleName(), entity.getId());
        } catch (Exception e)
        {
            System.err.printf("Error: %s. Details: %s\n Modifications cancelled.\n", e.toString(), e.getMessage());
        }

    }

    static public <T extends DataEntity> void removeDataEntity(IConsoleIO consoleIO,DataEntityList<T> list)
    {
        showDataEntities(list.getEntities());
        int choice = consoleIO.askInt("Enter Id number to remove", 1, Integer.MAX_VALUE);
        list.removeEntity(choice);

        consoleIO.AskText("Enter key to return", 0);
    }

    static public void manageEntity(IConsoleIO consoleIO,DataEntity entity)
    {
        if (entity instanceof Employee)
        {
            String[] menuOptions = {"Show customers", "Add customers", "Remove customers", "Return"};
            String title = String.format("Manage %s : %s",
                    entity.getClass().getSimpleName().toLowerCase(),
                    ((Employee) entity).getName());
            int choice = consoleIO.chooseFromMenu(menuOptions,"Make your choice",title);

            switch (choice){
                case 1:
                    showDataEntities(((Employee) entity).getCustomers());
                    consoleIO.AskText("Enter key to return", 0);
                    manageEntity(consoleIO,entity);
                    break;
                case 2:
                    ((Employee) entity).addCustomer((Customer)selectDataEntity(consoleIO,allCustomers));
                    manageEntity(consoleIO,entity);
                    break;
            }
        }

    }

    static public <T extends DataEntity>void manageEntities(IConsoleIO consoleIO,ISQLConnection sqlConnection,DataEntityList<T> entities)
    {
        String[] menuOptions = {"Add " + entities.getSingularEntityName(),
                "Remove " + entities.getSingularEntityName(),
                "Show " + entities.getPluralEntityName(),
                "Manage " + entities.getSingularEntityName(),
                "Edit " + entities.getSingularEntityName(), "Return to main menu"};
        int choice = consoleIO.chooseFromMenu(menuOptions, "Make your choice", entities.getPluralEntityName());
        switch (choice)
        {
            case 1:
                addEntity(entities,consoleIO,sqlConnection);
                manageEntities(consoleIO,sqlConnection,entities);
                break;
            case 2:
                removeDataEntity(consoleIO,entities);
                manageEntities(consoleIO,sqlConnection,entities);
                break;
            case 3:
                showDataEntities(entities.getEntities());
                consoleIO.AskText("Enter key to return", 0);
                manageEntities(consoleIO,sqlConnection,entities);
                break;
            case 5:
                editDataEntity(consoleIO,selectDataEntity(consoleIO,entities));
                consoleIO.AskText("Enter key to return", 0);
                manageEntities(consoleIO,sqlConnection,entities);
                break;
            case 4:
                manageEntity(consoleIO,selectDataEntity(consoleIO,entities));
                manageEntities(consoleIO,sqlConnection,entities);
                break;
            case 6:
                break;
        }


    }
}
