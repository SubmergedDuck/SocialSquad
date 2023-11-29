package data_access;

import java.util.ArrayList;

public class FormatStringList {
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
