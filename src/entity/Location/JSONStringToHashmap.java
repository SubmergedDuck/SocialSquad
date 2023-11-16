package entity.Location;
import org.json.JSONObject;

import java.util.HashMap;

public class JSONStringToHashmap {
    public static HashMap<String, Object> convert(String jsonString){
        JSONObject jsonObject = new JSONObject(jsonString);
        HashMap<String, Object> newMap = new HashMap<>();
        for (String key : jsonObject.keySet()){
            Object value = jsonObject.get(key);
            newMap.put(key, value);
        }
        return newMap;
    }
}
