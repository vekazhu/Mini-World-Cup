import java.lang.reflect.GenericArrayType;
import java.security.acl.Acl;
import java.util.*;
import javax.annotation.Generated;

/**
 * The Team class stores information about the name and rank of the team, also two players that will play for the team.
 * The relatice methods to get and set values of four attributes.
 *
 * @Weijia ZHU 
 * @04/May/2018
 */
public class Team
{
    private String teamName;
    private int teamRank;
    private ArrayList<Player> players;
    private ArrayList<Integer> plays;
    private RandomGoalGenerator randomGoalGenerator = new RandomGoalGenerator();

    /**
     * 
     */
    public Team()
    {
        teamName = " ";
        teamRank = 0;
        players = new ArrayList<Player>();
        plays = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0));
    }

    public Team(String newTeamName, int newTeamRank)
    {
        teamName = newTeamName;
        teamRank = newTeamRank;
        players = new ArrayList<Player>();
        plays = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0));
    }

    public Team(String newTeamName, int newTeamRank, ArrayList<Player> newPlayers, ArrayList<Integer> newPlays)
    {
        teamName = newTeamName;
        teamRank = newTeamRank;
        players = newPlayers;
        plays = newPlays;
    }

    /**
     * 
     */

    /** 
    public void setPlayers(ArrayList<Player> newPlayers)
    {
        int playerCount = 0;
        for (playerCount = 0; playerCount < 2; playerCount ++)
        {
            players.add(nameScanner());
            playerCount ++;
        }
    }
    **/
    
    public ArrayList<Integer> getPlays()
    {
        return plays;
    }

    public String getPlaysInformation() 
    {
        String result = "";
        for (int play: plays)
            {
                result += play + " ";
            }
        return result;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public ArrayList<String> getPlayersInfomation()
    {
        ArrayList<String> result = new ArrayList<String>();
        for (Player player: players)
            {
                result.add(player.getName() + "-" + teamName + "-" + player.getGoal());
                System.out.println(result);
            }
        return result;
    }

    public void distributeGoal(int totalGoal)
    {
        int firstGoal = randomGoalGenerator.generateRandomGoal(0, totalGoal);
        int secondGoal = totalGoal - firstGoal;
        players.get(0).setGoal(firstGoal);
        players.get(1).setGoal(secondGoal);
    }

    public int getRandomGoals(int diff, boolean isHigher)
    {
        int randomUpset = randomGoalGenerator.generateRandomUpset();
        int max = 5 + randomUpset - (isHigher ? diff : 0);
        return randomGoalGenerator.generateRandomGoal(0, max);
    }

    public int getPenalityShootOut()
    {
        return randomGoalGenerator.generateRandomGoal(0, 5);
    }

    public int getPenalityExtras()
    {
        return randomGoalGenerator.generateRandomGoal(0, 1);
    }

    public String retrivePlays(int index)
    {
        return Integer.toString(plays.get(index));
    }

    public void setPlays(ArrayList<Integer> newPlays)
    {
        plays = newPlays;
    }

    public void changePlay(Integer position, Integer value)
    {
        plays.set(position, plays.get(position) + value);
    }

    public void nameScanner()
    {
        System.out.println("Please enter names of 2 Players one by one for this team.");
        int inputCount = 0;
        Player player = new Player();
        for (inputCount = 0; inputCount < 2; inputCount ++)
            { 
                Scanner nameScanner = new Scanner(System.in);
                String userInput = nameScanner.next().trim();
                int notLetter = 0;
                for (int x = 0; x < userInput.length(); x ++)
                    {
                        char c = userInput.charAt(x);
                        if (Character.isLetter(c) == false)
                            {
                                notLetter ++;
                                if (userInput.charAt(x) == '-')
                                    {
                                        notLetter --;
                                    }
                            }
                    }
                int hyphenCount = 0;
                for (int i = 0; i < userInput.length(); i++)
                    {
                    if (userInput.charAt(i) == '-')
                        {
                            hyphenCount ++;
                        }
                    }
                if (notLetter > 0 ||
                    userInput.startsWith("-") == true ||
                    userInput.endsWith("-") == true ||
                    userInput.contains(" ") == true ||
                    userInput.length() < 2 ||
                    userInput.length() > 31 ||
                    hyphenCount > 1)
                    { 
                        if (inputCount < 1)
                            {
                                System.out.println ("Please renter the name.");
                            }
                        else
                            {
                                inputCount ++;
                            }
                        nameScanner.close();
                    }
                else
                    {
                        player.setName(userInput);
                        player = new Player(userInput, 0);
                        System.out.println("The name of this player is " + player.getName() + ".");
                        inputCount = 2;
                        nameScanner.close();
                    }
                if (inputCount == 2 && player.getName() == " ")
                    {
                        String defaultName = "Jack";
                        for (Player singlePlayer: players)
                        {
                            if (singlePlayer.getName() == defaultName)
                            {
                                defaultName = "Zac";
                            }
                        }
                        System.out.println("System will assign a default name.");
                        player.setName(defaultName);
                        System.out.println("Player name is " + defaultName + ".");
                        nameScanner.close();
                        player = new Player(defaultName, 0);
                    }
            }  
        addPlayer(player);
    }
  
    public String getTeamName()
    {
        return teamName;
    }

    public int getRank()
    {
        return teamRank;
    }

    public void setTeamName(String newTeamName)
    {
        teamName = newTeamName;
    }

    public void setRank(int newTeamRank)
    {
        teamRank = newTeamRank;
    }

    public ArrayList<Integer> giveCard()
    {
        int yellowCard = 0;
        int redCard = 0;
        int giveCardIndicator = 5;
        RandomGoalGenerator cards = new RandomGoalGenerator();
        if (cards.generateRandomGoal(0,19) == giveCardIndicator)
            {
                if (cards.generateRandomGoal(0,19) == giveCardIndicator)
                    {
                        yellowCard ++;
                    }
                if (cards.generateRandomGoal(0,79) == giveCardIndicator)
                    {
                        redCard ++;
                    }
            }
        changePlay(7,yellowCard);
        changePlay(8,redCard);
        int fairPlay = yellowCard + (2 * redCard);
        changePlay(6, fairPlay); 
        ArrayList<Integer> results = new ArrayList<Integer>();
        results.add(yellowCard);
        results.add(redCard);
        
        return results;
    }


}


