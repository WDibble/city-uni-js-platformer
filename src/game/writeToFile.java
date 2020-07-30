package game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.12
 * This class is writeToFile.
 * This class has all the variables and methods needed to save the players score and username to a .txt file.
 * This is the file that is read when the high score is shown on screen.
 */

public class writeToFile {
    public static void write(String name, int score) {
        try {
            File myObj = new File("filename.txt");

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter("filename.txt", true);
            myWriter.write(score / 100 + " Seconds: " + name);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            new FileSorter("filename.txt");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Reads the Scoreboard file.
     *
     * @param i the line number.
     * @return the String at that line number.
     * @throws IOException N/A.
     */
    public static String read(int i) throws IOException {
        FileStrings strings = new FileStrings("filename.txt");

        return strings.get(i);
    }
}