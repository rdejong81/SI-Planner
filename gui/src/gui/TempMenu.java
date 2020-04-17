/*
 * This is temporary console UI menu until GUI is implemented.
 */

package gui;

public class TempMenu
{
    private int choice;

    public TempMenu(String[] options, String prompt, String menuName) {

        System.out.println("\u001b[2J\u001B[46m=======================================================\u001B[0m");
        System.out.printf("\u001B[46m%32s%23s\u001B[0m\n",menuName,"");
        System.out.println("\u001B[46m=======================================================\u001B[0m");
        System.out.println();
        for(int i=1;i-1<options.length;i++)
        {
            System.out.printf("\u001B[32m%3d\u001B[0m. %s\n",i,options[i-1]);
        }

        System.out.println();
        choice = TempInput.askInt(prompt,1,options.length);


    }

    public int getChoice(){
        return choice;
    }


}
