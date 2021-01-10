package Game.Multiplayer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerScene {

    String SCENE_JSON = "{\"children\":[{\"G_ID\":\"TREE\",\"yPos\":150,\"M_ID\":\"jm0b4vz\",\"xPos\":100},{\"G_ID\":\"TREE\",\"yPos\":300,\"M_ID\":\"c2cl8vp\",\"xPos\":300},{\"G_ID\":\"SMALLROCKS\",\"yPos\":400,\"M_ID\":\"15qwxr5\",\"xPos\":400},{\"G_ID\":\"THORNBUSH\",\"yPos\":50,\"M_ID\":\"1996bl0\",\"xPos\":50},{\"G_ID\":\"COIN\",\"yPos\":150,\"M_ID\":\"jo6rald\",\"xPos\":300}],\"background\":\"BACKGROUND\"}\n";

    private String background;
    private JSONArray children;

    public ServerScene(){

        try{
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(SCENE_JSON);
            children = (JSONArray) object.get("children");
            background = (String) object.get("background");

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getSceneAsJSON(){
        JSONObject object = new JSONObject();
        object.put("background", background);
        object.put("children", children);
        return object.toJSONString();
    }

}
