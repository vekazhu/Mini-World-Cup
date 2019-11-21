import java.awt.SystemTray;
import java.lang.reflect.Array;
import java.util.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.awt.PlatformFont;

/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game 
{
    private ArrayList<Team> teams;
    private Menu menu;
    private FileProcesser fileProcesser;
    private boolean preliminaryPlayed = false;
    private boolean finalPlayed = false;

    /**
    * 
    */
    public Game() 
    {
        teams = new ArrayList<Team>();
        menu = new Menu();
        fileProcesser = new FileProcesser();

    }

    public Game(ArrayList<Team> newTeams, Menu newMenu, FileProcesser newFileprocesser) 
    {
        teams = newTeams;
        menu = newMenu;
        fileProcesser = newFileprocesser;
    }

    public void setTeams(ArrayList<Team> newTeams) 
    {
        teams = newTeams;
    }

    public String retriveTeams(int index) 
    {
        return String.valueOf(teams.get(index));
    }

    public void readTeamsFromFile() 





    {
        ArrayList<String> lines = fileProcesser.readLinesFromFile("teams.txt");

        ArrayList<Team> teams = new ArrayList<Team>();
        for (String line : lines) 
        {
            String[] information = line.split(",");
            teams.add(new Team(information[0], Integer.parseInt(information[1])));
        }

        this.teams = teams;
    }

    public void displayWelcome() 
    {
        System.out.println("Welcome to the Football World Cup game!!");
        System.out.println("The game is between China, Australia, Ghana and Spain.");
        System.out.println("There are two stages of the game, the preliminary and the final.");
        System.out.println("Each team will have 2 players, and you will decide their names");
        System.out.println(" ");
        System.out.println("============================================================================");
        System.out.println(" ");
        System.out.println("Now please enter 8 names in total.");
        System.out.println("Also, there are some naming rules you need to follow:");
        System.out.println("1.The name can contain only alphabetical characters and at most one hyphen.");
        System.out.println("2.The name cannot begin or end with a hyphen.");
        System.out.println("3.The length of the name is between 2 and 30 inclusively.");
        System.out.println("4.A default name will be assigned if you enter invalid names twice.");
    }

    public void startGame() 
    {
        displayWelcome();
        readTeamsFromFile();
        scanTeamPlayerNames();

        playLoop();
    }

    public void scanTeamPlayerNames() 
    {
        for (Team team : teams) 
        {
            team.nameScanner();
            team.nameScanner();
        }
    }

    public void playLoop() 
    {
        menu.displayMenu();
        String input = menu.menuScanner();
        switch (input) {
        case "A":
        case "a":
            if (!preliminaryPlayed) {
                playPreliminary();
            }
            break;

        case "B":
        case "b":
            if (!finalPlayed && preliminaryPlayed) {
                playFinal();
            }
            break;

        case "C":
        case "c":
            displayTeams();
            break;

        case "D":
        case "d":
            displayPlayers();
            break;

        case "E":
        case "e":
            displayCupResult();
            break;

        case "X":
        case "x":

        default:
            break;
        }

            playLoop();
    }

    public void playPreliminary() 
    {
        teams.sort((a, b) -> Integer.compare(a.getRank(), b.getRank()));
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) 
            {
                playGame(teams.get(i), teams.get(j));
            }
        }

        teams.sort((a, b) -> Integer.compare(b.getPlays().get(5), a.getPlays().get(5)));
        displayPreliminaryResult();

        preliminaryPlayed = true;
    }

    public boolean playGame(Team team1, Team team2) 
    {
        int diff = Math.abs(team1.getRank() - team2.getRank());
        boolean isTeam1Higher = team1.getRank() < team2.getRank();
        int team1Goal = team1.getRandomGoals(diff, isTeam1Higher);
        int team2Goal = team2.getRandomGoals(diff, !isTeam1Higher);
        ArrayList<Integer> team1Cards = team1.giveCard();
        ArrayList<Integer> team2Cards = team2.giveCard();
        int team1YellowCard = team1Cards.get(0);
        int team1RedCard = team1Cards.get(1);
        int team2YellowCard = team2Cards.get(0);
        int team2RedCard = team2Cards.get(1);

        // played
        team1.changePlay(0, 1);
        team2.changePlay(0, 1);

        // won and lost and drown and point
        if (team1Goal > team2Goal) 
        {
            team1.changePlay(1, 1);
            team1.changePlay(5, 3);

            team2.changePlay(2, 1);
        } else if (team1Goal < team2Goal) 
        {
            team2.changePlay(1, 1);
            team2.changePlay(5, 3);

            team1.changePlay(2, 1);
        } else 
        {
            team1.changePlay(3, 1);
            team1.changePlay(5, 1);

            team2.changePlay(5, 1);
            team2.changePlay(3, 1);
        }

        // goal

        team1.changePlay(4, team1Goal);
        team2.changePlay(4, team2Goal);

        team1.distributeGoal(team1Goal);
        team2.distributeGoal(team2Goal);

        displayGameResult(team1.getTeamName(), team1Goal, team1YellowCard, team1RedCard, team2.getTeamName(), team2Goal,
                team2YellowCard, team2RedCard);

        return team1Goal == team2Goal;
    }

    public void playFinal() 
    {
        playGame(teams.get(0), teams.get(1));
        if (playGame(teams.get(0), teams.get(1)) == true)
            penaltyShootOut(teams.get(0), teams.get(1));

        teams.sort((a, b) -> Integer.compare(a.getPlays().get(5), b.getPlays().get(5)));

        finalPlayed = true;
    }

    public void penaltyShootOut(Team team1, Team team2) 
    {
        int team1PenaltyShoot = team1.getPenalityShootOut();
        int team2PenaltyShoot = team2.getPenalityShootOut();

        while (team1PenaltyShoot == team2PenaltyShoot) 
        {
            int team1ExtraShoot = team1.getPenalityExtras();
            int team2ExtraShoot = team2.getPenalityExtras();
            team1PenaltyShoot += team1ExtraShoot;
            team2PenaltyShoot += team2ExtraShoot;
        }

    }

    public void displayGameResult(String team1Name, int team1Goal, int team1YellowCard, int team1RedCard,
            String team2Name, int team2Goal, int team2YellowCard, int team2RedCard) 
            {
        System.out.println("Game Result: " + team1Name + team1Goal + "  vs.  " + team2Name + team2Goal);
        System.out.println(
                "Cards awarded: " + team1Name + team1YellowCard + "-yellow card " + team1RedCard + "-red card.");
        System.out.println(
                "Cards awarded: " + team2Name + team2YellowCard + "-yellow card " + team2RedCard + "-red card.");
    }

    public void displayPreliminaryResult() 
    {
        System.out.println("Preliminary results: ***********************");

        for (int i = 0; i < 4; i++) {
            Team team = teams.get(i);
            System.out.println("#" + (i + 1) + " : " + team.getTeamName() + " point: " + team.getPlays().get(5) + "goal: " + team.getPlays().get(4));
        }
    }

    public void displayTeams()
    {
        System.out.println("team information: ***********************");
        for (Team team: teams)
            {
                System.out.println("team: " + team.getTeamName() + team.getPlaysInformation());
            }
    }

    public void displayPlayers() 


    {
        System.out.println("player infomation: ***********************");
        for (Team team : teams) 
        {
            for (String info : team.getPlayersInfomation()) 
            {
                System.out.println(info);
            }
        }
    }

    public void displayCupResult() 
    {
        System.out.println("Cup infomation: ***********************");
        System.out.println("World cup winner: " + teams.get(0).getTeamName());
        System.out.println("Golden boot award: " + teams.get(0).getTeamName());
        System.out.println("Fair play award: " + teams.get(0).getTeamName());
    }
}
