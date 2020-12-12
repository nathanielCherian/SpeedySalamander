package AWS;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.sql.SQLException;

public class Main {


    public static void main(String args[]) throws SQLException, InterruptedException {
        create();
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
