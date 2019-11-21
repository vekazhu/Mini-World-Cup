
import java.util.Random;

/**
 * This class is a generator that will generate random goals for each team in the game.
 *
 * @Weijia ZHU
 * @04/May/2018
 */
public class RandomGoalGenerator
{
    private Random randomGoal;

    /**
     * The default constructor for class RandomGoalGenerator.
     */
    public RandomGoalGenerator()
    {
        randomGoal = new Random();
    }

    /**
     * 
     */
    public RandomGoalGenerator(Random newRandomGoal)
    {
        randomGoal = newRandomGoal;
    }

    public String displayRandomGoal()
    {
        return "randomGoal: " + randomGoal;
    }

    public int generateRandomGoal(int min, int max)
    {
        return randomGoal.nextInt ((max-min)+1);
    }

    public int generateRandomUpset()
    {
        return generateRandomGoal(0,2);
    }

    public Random getRandomGoal()
    {
        return randomGoal;
    }

    public void setRandomGoal(Random newRandomGoal)
    {
        randomGoal = newRandomGoal;
    }

    
}

