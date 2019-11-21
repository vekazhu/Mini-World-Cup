import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * The Team class stores information about the name and rank of the team, also two players that will play for the team.
 * The relatice methods to get and set values of four attributes.
 *
 * @Weijia ZHU 
 * @10/May/2018
 */
public class FileProcesser
{
    private String Filename;

    /**
     * 
     */
    public FileProcesser()
    {
        Filename = null;
    }

    public FileProcesser(String newFilename)
    {
        Filename = newFilename;
    }

    public ArrayList<String> readLinesFromFile(String fileName)
    {
        String newFilename = (fileName);
        ArrayList<String> lines = new ArrayList<String>();
        try
        {
            FileReader inputFile = new FileReader(newFilename);
            try
            {
                Scanner fileScanner = new Scanner(inputFile);
                int Line = 0;
                for (Line = 0; Line <4; Line ++)
                {
                    String Content = fileScanner.nextLine();
                    lines.add(Content);
                }
                fileScanner.close();
            }
            finally
            {
                inputFile.close();
            }
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(Filename + " not found.");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O exception occurs.");
        }
        return  lines;
    }
}


