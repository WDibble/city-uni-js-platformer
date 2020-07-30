package game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.12
 * This class is saveGame.
 * This class has all the variables and methods needed to save the players progress and write it to a .txt file
 * This class checks whether there's already a text file to write to and if there is it wont create a new one.
 * It then writes the players Username, Level, Score, Time, Health and X and Y positions.
 * It then allows a method to be called to read this information back from the .txt file to setup the game.
 * It also has a method to see if the user has an entry in the text file or not, if not the player continues the game as normal.
 * When called, the game only allows the player to save their progress once to add more difficulty to the game.
 */

public class saveGame {

    public static int playerLevel = 0;
    public static int playerScore = 0;
    public static int playerTime = 0;
    public static int playerHealth = 0;

    /**
     * Write the players game information to a saves file.
     *
     * @param name Username of Player.
     * @param level Level player is currently on.
     * @param score Players current score.
     * @param time Players current time.
     * @param health Players current health.
     * @param posX Players X Position.
     * @param posY Players Y Position.
     */
    public static void write(String name, int level, int score, int time, int health, float posX, float posY) {
        try {
            File myObj = new File("saves.txt");

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter("saves.txt", true);
            //myWriter.write("\n");
            myWriter.write("\n" + name + "\n" + level + "\n" + score + "\n" + time + "\n" + health + "\n" + posX + "\n" + posY);
            System.out.println("Username: " + name + ", Level: " + level + ", Score: " + score + ", Time: " + time + ", Health: " + health + ", posX: " + posX + ", posY " + posY);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Read players game information from the text file.
     *
     * @throws IOException N/A.
     */
    public static void read() throws IOException {
        FileStrings strings = new FileStrings("saves.txt");

        for (int i = 0; (i < strings.size()); i++) {
            if (strings.get(i).equals(Background.username)) {
                playerLevel = Integer.parseInt(strings.get(i + 1));
                playerScore = Integer.parseInt(strings.get(i + 2));
                playerTime = Integer.parseInt(strings.get(i + 3));
                playerHealth = Integer.parseInt(strings.get(i + 4));
            }
        }
    }

    /**
     * Checks if the user already has a save file.
     *
     * @return The line number the username can be found at in the .txt file.
     * @throws IOException N/A.
     */
    public static int findUser() throws IOException {
        //Find user via username.
        FileStrings strings = new FileStrings("saves.txt");
        int count = -1;
        //System.out.println("Found at: " + count);

        for (int i = 0; (i < strings.size()) && (count == -1); i++) {
            if (strings.get(i).equals(Background.username)) {
                //System.out.println("Found at: " + count);
                count = i;
            }
        }

        return count;
    }
}