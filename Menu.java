import java.util.*;

/**
 * Write a description of class Menu here.
 *
 * @Weijia ZHU 
 * @13/May/2018
 */
public class Menu
{
  private String Option;

    /**
     * 
     */
    public Menu()
    {
        Option = " ";
    }   
    
    public Menu(String newOption)
    {
        Option = newOption;
    }

    public void displayMenu()
    {
        System.out.println(" ");
        System.out.println("================================================================================");
        System.out.println("Please select from options below by entering A, B, C, D, E or X.");
        System.out.println("A. Play Preliminary Stage");
        System.out.println("B. Play Final");
        System.out.println("C. Display Teams");
        System.out.println("D. Display Players");
        System.out.println("E. Display Cup Result");
        System.out.println("X. Close");  
    }

    public String menuScanner()
    {
        Scanner menuScanner = new Scanner(System.in);
        String menuInput = "";
        if (!menuScanner.hasNextInt())
            {
                menuInput = menuScanner.next().trim();
            }
        menuScanner.close();
        return menuInput;
    }








}

