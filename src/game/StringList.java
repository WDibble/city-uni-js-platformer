package game;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.12
 * This class is StringList.
 * This class enables the ability to retrieve a string using a line number for the array/.txt file.
 * It also allows for retrieval of the size of the array/.txt file.
 */

public interface StringList {

    int size();

    String get(int i);
}