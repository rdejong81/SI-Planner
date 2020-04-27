package facade;

public class DuplicateRequestException extends Exception
{
    public DuplicateRequestException(String message)
    {
        super(message);
    }
}
