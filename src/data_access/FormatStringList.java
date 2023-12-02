package data_access;

import java.util.ArrayList;

/**
 * Formats a list of strings to one string.
 */
public class FormatStringList {

    /**
     * Formats all the strings in a given list into one string.
     * @param stringList The list containing all the strings that will be formatted.
     * @param elementSeperator The string that seperates each element from stringList.
     * @return
     */
    public static String formatStringList(ArrayList<String> stringList, String elementSeperator){
        String currentString = "";
        for (int i = 0; i < stringList.size(); i++){
            if (i == stringList.size() - 1){
                currentString = currentString + stringList.get(i);
            } else {
                currentString = currentString + stringList.get(i) + elementSeperator;
            }
        }
        return currentString;
    }
}
