package Facade;


// Temporary interface until proper UI is implemented
public interface IConsoleIO
{
    int chooseFromMenu(String[] options, String prompt, String menuName);
    String AskText(String question, int minimumLength);
    int askInt(String question, int minimum, int maximum);

}
