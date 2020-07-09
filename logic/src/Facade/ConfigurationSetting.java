package Facade;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class ConfigurationSetting <T>
{
    private final String name,category,group;
    private String label;
    private IObjectStringConverter<T> objectStringConverter;
    private IListRetriever<T> listRetriever;
    private T value;
    private final ArrayList<String> items;
    private final ArrayList<IChangedEvent<T>> changedEvents;
    private final ArrayList<BiConsumer<String,String>> changedLabelEvents;


    public ConfigurationSetting(String name, String label, String category,String group, T value)
    {
        this.name = name;
        this.label = label;
        this.category = category;
        this.group = group;
        this.value = value;
        this.listRetriever = null;
        this.items = new ArrayList<>();
        this.changedEvents = new ArrayList<>();
        this.changedLabelEvents = new ArrayList<>();
        AppFacade.appFacade.addConfigurationSetting(this);
    }
    public ConfigurationSetting(String name, String label, String category, String group, IListRetriever<T> retriever, IObjectStringConverter<T> objectStringConverter)
    {
        this(name,label,category,group, null);
        // This is a list based Config Setting.
        this.objectStringConverter = objectStringConverter;
        this.listRetriever = retriever;
    }
    public ConfigurationSetting(String name, String label, String category, String group)
    {
        this(name,label,category,group,null);
        // setting without value - a caption
    }

    public void subscribeChanged(IChangedEvent<T> changedEvent)
    {
        changedEvents.add(changedEvent);
    }

    public String getName()
    {
        return name;
    }

    public String getGroup()
    {
        return group;
    }

    public String getCategory()
    {
        return category;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        if(this.label.equals(label)) return;
        for(BiConsumer<String,String> labelEvent : changedLabelEvents)
        {
            labelEvent.accept(this.label,label);
        }
        this.label = label;
    }

    public void subscribeLabelChanged(BiConsumer<String,String> labelEvent)
    {
        changedLabelEvents.add(labelEvent);
    }

    public T getValue()
    {
        return value;
    }

    public List<String> getItems()
    {
        if(listRetriever == null) return null;
        items.clear();
        if(listRetriever.retrieveList() != null)
        for(T item : listRetriever.retrieveList())
        {
            items.add(objectStringConverter.objectToString(item));
        }
        return items;
    }

    public void setSelected(String value)
    {
        if(listRetriever.retrieveList() != null)
        for(T item : listRetriever.retrieveList())
        {
            if(objectStringConverter.objectToString(item).equals(value))
            {
                if(this.value != null && !this.value.equals(item))
                    for(IChangedEvent<T> changedEvent : changedEvents)
                        changedEvent.notifyChanged(this.value,item);
                this.value = item;
            }
        }
    }

    public void setValue(T value)
    {
        if(this.value != null && !this.value.equals(value))
            for(IChangedEvent<T> changedEvent : changedEvents)
                changedEvent.notifyChanged(this.value,value);
        this.value = value;
    }

    public String convertObject(T object)
    {
        if(objectStringConverter == null) return null;
        return objectStringConverter.objectToString(object);
    }

}
