import java.util.*;
import java.util.regex.*;

/**
 * The player class stores information about players' names and goals they scored. Also the ralative method to get and
 * set their names and goals.
 *
 * @Weijia ZHU
 * @03/May/2018
 */
public class Player
{
    //field to store information of players' names
    private String playerName;
    //field to store information of players' scored goals
    private int playerGoal;

    /**
     * Default constructor to initialize the objects, and to assign default values to attributes.
     */
    public Player()
    {
        //initialize instance variables
        playerName = " ";
        playerGoal = 0;
    }

    /**
     * Non-default constructor to accept values for attributes of objects.
     */
    public Player(String newPlayerName, int newPlayerGoal)
    {
        //accepts values for attributes playerName and playerGoal
        playerName = newPlayerName;
        playerGoal = newPlayerGoal;
    }

    public String displayPlayer()
    {
        return "The name of the player is " + playerName + ", and he goaled " + playerGoal + ".";
    }
        

    public int getGoal()
    {
        return playerGoal;
    }

    public String getName()
    {
        return playerName;
    }

    public void setGoal(int newPlayerGoal)
    {
        playerGoal = newPlayerGoal;
    }

    public void setName(String newPlayerName)
    {
        playerName = newPlayerName;
    }
    
    
}

