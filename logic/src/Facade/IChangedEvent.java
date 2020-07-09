package Facade;

public interface IChangedEvent<T>
{
    void notifyChanged(T oldValue, T newValue);
}
