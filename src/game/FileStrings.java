/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.11
 * This class is FileStrings.
 * This class reads all the information from a .txt file.
 * Organises the information into an array that you can remove and add elements to.
 */

public class FileStrings implements StringList {

    private final ArrayList<String> elements;

    private int count;

    public FileStrings(String fileName) throws IOException {
        elements = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = input.readLine()) != null) {
                elements.add(line);
            }
        }
        count = 0;
    }

    /**
     * Retrieves the size of the array.
     *
     * @return size of array.
     */
    @Override
    public int size() {
        return elements.size();
    }

    /**
     * Retrieves a string from the array using a line number.
     *
     * @param i the line number.
     * @return the string at that line number.
     */
    @Override
    public String get(int i) {
        count++;
        return elements.get(i);
    }

    public void resetCount() {
        count = 0;
    }

    public int getCount() {
        return count;
    }
}
