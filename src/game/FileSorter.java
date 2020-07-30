package game;

import java.io.*;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.11
 * This class is FileSorter.
 * This class reads all the information from a .txt file.
 * Organises the information through a bubble sort.
 * And writes it all back to the file.
 */

public class FileSorter {
    public FileSorter(String fileName) {
        String[] list = new String[1000];
        int count = 0;

        try {
            // Read all the Strings from the file into list[]
            BufferedReader info =
                    new BufferedReader(new FileReader(fileName));

            while (info.ready()) {
                String item = info.readLine();
                list[count] = item;
                count = count + 1;
            }
            info.close();

            sort(list, count);

            // Write sorted Strings back into the file
            PrintWriter outfo =
                    new PrintWriter(new FileWriter(fileName));
            for (int x = 0; x < count; x = x + 1) {
                outfo.println(list[x]);
            }
            outfo.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * A simple bubble sort.
     *
     * @param list contains the Strings to be sorted
     * @param count tells how many Strings are in the array
     */
    public void sort(String[] list, int count)
    {
        for (int pass = 0; pass < count; pass = pass + 1) {
            for (int p = 0; p < count - 1; p = p + 1) {
                if (list[p].compareTo(list[p + 1]) > 0) {
                    String temp = list[p];
                    list[p] = list[p + 1];
                    list[p + 1] = temp;
                }
            }
        }
    }
}
