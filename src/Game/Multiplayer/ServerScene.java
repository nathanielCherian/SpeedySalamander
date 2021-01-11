package Game.Multiplayer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerScene {

    String SCENE_JSON = "{\"children\":{\"zyn4vnx\":{\"G_ID\":\"COIN\",\"yPos\":300,\"M_ID\":\"zyn4vnx\",\"xPos\":300},\"kiqxydk\":{\"G_ID\":\"SMALLROCKS\",\"yPos\":200,\"M_ID\":\"kiqxydk\",\"xPos\":250},\"uemj1pc\":{\"G_ID\":\"THORNBUSH\",\"yPos\":50,\"M_ID\":\"uemj1pc\",\"xPos\":50},\"njg0808\":{\"G_ID\":\"TREE\",\"yPos\":100,\"M_ID\":\"njg0808\",\"xPos\":100},\"x96c6w9\":{\"G_ID\":\"TREE\",\"yPos\":100,\"M_ID\":\"x96c6w9\",\"xPos\":140}},\"background\":\"BACKGROUND\"}\n";
    //String SCENE_JSON = "{\"children\":[{\"G_ID\":\"TREE\",\"yPos\":150,\"M_ID\":\"jm0b4vz\",\"xPos\":100},{\"G_ID\":\"TREE\",\"yPos\":300,\"M_ID\":\"c2cl8vp\",\"xPos\":300},{\"G_ID\":\"SMALLROCKS\",\"yPos\":400,\"M_ID\":\"15qwxr5\",\"xPos\":400},{\"G_ID\":\"THORNBUSH\",\"yPos\":50,\"M_ID\":\"1996bl0\",\"xPos\":50},{\"G_ID\":\"COIN\",\"yPos\":150,\"M_ID\":\"jo6rald\",\"xPos\":300}],\"background\":\"BACKGROUND\"}\n";

    private JSONObject sceneObject;

    public ServerScene(){

        try{
            JSONParser parser = new JSONParser();
            sceneObject = (JSONObject) parser.parse(SCENE_JSON);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getSceneAsJSON(){
        return sceneObject.toJSONString();
    }

    public void updateFromJSON(JSONObject object){

        JSONObject children = (JSONObject) sceneObject.get("children");

        String code = (String) object.get("stateCode");
        JSONObject paintableObject = (JSONObject) object.get("object");
        String MID = (String) paintableObject.get("M_ID");

        switch (code){

            case "CREATE":
            case "CHANGE":
                children.put(MID, paintableObject);
                break;
            case "DELETE":
                children.remove(MID);
                break;
            default:
                break;
        }

    }

}
