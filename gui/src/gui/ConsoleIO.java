/*
 * This is temporary console UI until GUI is implemented.
 */

package gui;

import facade.IConsoleIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleIO implements IConsoleIO
{

    public int chooseFromMenu(String[] options, String prompt, String menuName) {

        System.out.println("\u001b[2J\u001B[46m=======================================================\u001B[0m");
        System.out.printf("\u001B[46m%32s%23s\u001B[0m\n",menuName,"");
        System.out.println("\u001B[46m=======================================================\u001B[0m");
        System.out.println();
        for(int i=1;i-1<options.length;i++)
        {
            System.out.printf("\u001B[32m%3d\u001B[0m. %s\n",i,options[i-1]);
        }

        System.out.println();
        return askInt(prompt,1,options.length);
    }

    public String AskText(String question, int minimumLength)
    {
        boolean isDone = false;
        String answer = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!isDone)
        {
            System.out.println(question);

            try
            {
                answer = reader.readLine();
                if (answer.length() >= minimumLength)
                {
                    isDone = true;
                }
            } catch (Exception e)
            {
            }

            if (!isDone)
            {
                System.out.println("Wrong input, please try again.");
            }
        }

        return answer;
    }

    public int askInt(String question, int minimum, int maximum)
    {
        boolean isDone = false;
        int answer = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!isDone)
        {
            System.out.printf("%s  : ",question);

            try
            {
                answer = Integer.parseInt(reader.readLine());
                if (answer >= minimum && answer <= maximum)
                {
                    isDone = true;
                }
            } catch (Exception e)
            {
            }

            if (!isDone)
            {
                System.out.println("Wrong input. please try again.");
            }
        }

        return answer;
    }




}
