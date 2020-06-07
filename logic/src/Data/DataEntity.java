package Data;

import Facade.AppFacade;
import Planning.Planning;
import Projects.Project;
import Timeregistration.Timeregistration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DataEntity
{
    private int id;
    private final ArrayList<Attribute> attributes;

    protected DataEntity(int id)
    {
        this.id = id;
        this.attributes = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }

    public boolean addAttribute(Attribute attribute)
    {
        if(attributes.contains(attribute)) return false;
        return attributes.add(attribute);
    }

    public boolean removeAttribute(Attribute attribute)
    {
        return attributes.remove(attribute);
    }

    public List<Attribute> getAttributes()
    {
        Customer customer=null;
        // Copy from customer definitions when attribute is not filled in yet.
        // only needed for supported entities and can be easily extended.
        switch (EntityType.fromEntity(this))
        {
            case CUSTOMER -> {
                customer = (Customer) this;
            }
            case PROJECT -> customer = ((Project)this).getCustomer();
            case PLANNING -> customer = ((Planning)this).getProjectTask().getProject().getCustomer();
            case TIMEREGISTRATION -> customer = ((Timeregistration)this).getProjectTask().getProject().getCustomer();
        }

        if(customer != null)
            for(Attribute attributeDefinition : customer.getAttributeDefinitions())
            {
                boolean found=false;
                if(attributeDefinition.getEntityType() != EntityType.fromEntity(this)) continue;

                for(Attribute attribute : attributes)
                {
                    if(attribute.getParentDefinition() == attributeDefinition)
                    {
                        found = true;
                    }
                }
                if(!found)
                {
                    // create the missing attribute.
                    Attribute attribute = new Attribute(
                            AppFacade.appFacade.getDataSource().attributeDao(),
                            -1,attributeDefinition.getName(),
                            attributeDefinition.getValue(),this,attributeDefinition,EntityType.fromEntity(this));
                    AppFacade.appFacade.getDataSource().attributeDao().insertAttribute(attribute,attributeDefinition);
                    attributes.add(attribute);

                }
            }

        return Collections.unmodifiableList(attributes);
    }

}
