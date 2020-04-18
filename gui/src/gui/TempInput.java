
package gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TempInput
{
    public static String AskText(String question, int minimumLength)
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

    public static int askInt(String question, int minimum, int maximum)
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
