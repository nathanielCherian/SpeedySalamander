package AWS;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AWS {


    public static void main(String args[]) throws SQLException, InterruptedException {
        //create();
        fetch();
    }



    public static void fetch() throws InterruptedException, SQLException{

        /*
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_WEST_1).build();
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("gameBoard");

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("playerID = :v_id")
                .withValueMap(new ValueMap()
                        .withString(":v_id", "Amazon DynamoDB#DynamoDB Thread 1"));

        ItemCollection<QueryOutcome> items = table.query(spec);

        Iterator<Item> iterator = items.iterator();
        Item item = null;
        while (iterator.hasNext()) {
            item = iterator.next();
            System.out.println(item.toJSONPretty());
        }*/


        ArrayList<Item> itemList = new ArrayList<Item>();
        ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try{
            credentialsProvider.getCredentials();
        }catch (Exception e){
            throw  new AmazonClientException("Cannot Load credentials!", e);
        }

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion("us-west-1")
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("gameBoard");


        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
        expressionAttributeValues.put(":val", new AttributeValue().withBOOL(true)); //Query for active=true

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("gameBoard")
                .withFilterExpression("active = :val")
                .withExpressionAttributeValues(expressionAttributeValues);

        ScanResult result = client.scan(scanRequest);

        for(Map<String, AttributeValue> item: result.getItems()){
            System.out.println("ID: " + item.get("playerID").getN());
            System.out.println("Name: " + item.get("playerName").getS());
            System.out.println("yPos: " + item.get("xPos").getN());
            System.out.println("yPos: " + item.get("yPos").getN());
            System.out.println();
        }
        //System.out.println(result.getItems().get(2));


        /*
        item = table.getItem("playerID", 0);
        itemList.add(item);
        System.out.println(item.get("playerID"));
        System.out.println(item.get("playerName"));
        System.out.println(item.get("xPos"));
        System.out.println(item.get("yPos"));
        */


        /*
        for(int i=0;i<result.getCount(); i++){
                item = table.getItem("playerID", 0);
                itemList.add(item);
        }
        */



    }


    public static void create() throws InterruptedException, SQLException{

        AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.standard().withRegion("us-west-1").build();
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("gameBoard");

        Item item = new Item()
                .withPrimaryKey("playerID", 2)
                .withString("playerName", "d")
                .withDouble("xPos", 1.0)
                .withDouble("yPos", 12.0);

        PutItemOutcome outcome = table.putItem(item);

    }

}
