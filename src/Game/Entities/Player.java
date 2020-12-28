package Game.Entities;

import Game.Game;
import Game.Listeners.CoinCollectListener;
import Game.Listeners.CollisionListener;
import Game.Listeners.DamageTakenListener;
import Game.Objects.Coin;
import Game.Paintable;
import com.amazonaws.services.dynamodbv2.xspec.S;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;

public class Player extends Paintable{


    int playerID;

    public BufferedImage[][][] avatarAnimations = new BufferedImage[3][8][8];
    int avatarMotionState = 0; // 0=idle, 1=walking 2=running
    int avatarAnimationState = 0; // avatar state of animation 0-7
    int avatarFacing = 0; // 0 North
    int r = 20;
    int speed = 5;

    //Stored data about player
    int totalCoins = 0;

    public Player(int id_){
        super(200,200);
        playerID = id_;
        this.ID = "PLAYER";

        createAvatarImage();


        //Creating Listeners
        addCollisionListener(new PlayerCollisionListener());
        addCoinCollectListener(new PlayerCollectCoinListener());

    }


    void createAvatarImage(){
        // Creating images
        try{

            String base_path = new File("").getAbsolutePath();
            avatarAnimations[0] = _splitAvatarSheet(base_path+"\\src\\Game\\Resources\\Avatar\\IdleSheet.png"); //Idle
            avatarAnimations[1] = _splitAvatarSheet(base_path+"\\src\\Game\\Resources\\Avatar\\StrafeSheet.png"); //Walking
            avatarAnimations[2] = _splitAvatarSheet(base_path+"\\src\\Game\\Resources\\Avatar\\RunSheet.png"); //Running

            this.width = avatarAnimations[0][0][0].getWidth();
            this.height = avatarAnimations[0][0][0].getHeight();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    BufferedImage[][] _splitAvatarSheet(String filePath) throws IOException {
        //splits 16 by 16 character sheet into induvidual BufferedImages

        BufferedImage[][] avatarDirections = new BufferedImage[8][8];

        final BufferedImage sheet = ImageIO.read(new File(filePath));

        for(int y=0;y<8;y++){
            BufferedImage[] row = new BufferedImage[8];
            for(int x=0;x<8;x++){
                row[x] = sheet.getSubimage(x*32, y*32, 32, 32);
            }
            avatarDirections[y] = row;
        }

        return avatarDirections;
    }



    ArrayList<DamageTakenListener> damageTakenListeners = new ArrayList<>();
    public void addDamageTakenListener(DamageTakenListener damageTakenListener){damageTakenListeners.add(damageTakenListener);}
    void executeDamageTakenListener(int damageTaken){
        damageTakenListeners.forEach(damageTakenListener -> {
            damageTakenListener.onDamageTaken(damageTaken);
        });
    }



    //When user collides with another object
    ArrayList<CollisionListener> collisionListeners = new ArrayList<>();
    public void addCollisionListener(CollisionListener collisionListener){
        collisionListeners.add(collisionListener);
    }
    void executeCollisionListener(Paintable collidedObject){
        collisionListeners.forEach(collisionListener -> {
            collisionListener.onCollide(this, collidedObject);
        });
    }
    private class PlayerCollisionListener implements CollisionListener{
        @Override
        public void onCollide(Paintable obj1, Paintable obj2) {

            if(obj2.isSolid){
                speed *= -1;
                for (int code: pressed_keys){
                    key_map.getOrDefault(code, ()->{}).run();
                }
                speed *= -1;
            }else if(obj2.isCollectable){
                obj2.toDelete = true;

                if(obj2.ID == Game.COIN){
                    executeCoinCollectListener((Coin) obj2);
                }
            }

            if(obj2.ID == Game.THORN_BUSH){
                executeDamageTakenListener(1);
            }



        }
    }


    //When user picks up a coin
    ArrayList<CoinCollectListener> coinCollectListeners = new ArrayList<>();
    public void addCoinCollectListener(CoinCollectListener coinCollectListener){
        coinCollectListeners.add(coinCollectListener);
    }
    void executeCoinCollectListener(Coin coin){

        coinCollectListeners.forEach(coinCollectListener -> {
            coinCollectListener.onCollectCoin(coin);
        });
    }
    private class PlayerCollectCoinListener implements CoinCollectListener{
        @Override
        public void onCollectCoin(Coin coin) {
            System.out.println("Coin Collect Event Triggered");
            totalCoins++;
        }
    }



    public void paint(Graphics2D g2d) {

        g2d.drawImage(avatarAnimations[avatarMotionState][avatarFacing][avatarAnimationState], x, y, null);
        avatarAnimationState += 1;
        avatarAnimationState %= 8;


        /*
        Ellipse2D shape = new Ellipse2D.Double(x,y,r,r);
        g2d.setColor(Color.red);
        g2d.fill(shape);
        */

    }



    Map<Integer, Runnable> key_map = Map.ofEntries(
            Map.entry(87, () -> move_forward()),
            Map.entry(83, () -> move_backward()),
            Map.entry(68, () -> move_right()),
            Map.entry(65, () -> move_left())

    );

    Set<Integer> pressed_keys;
    public void digest_keys(Set<Integer> pressed_keys){
        this.pressed_keys = pressed_keys;
        if(pressed_keys.size() == 0){
            avatarMotionState = 0;
            return;
        }else if(pressed_keys.contains(16)){ //SPRINT
            speed = 10;
            avatarMotionState = 2;
        }else{
            speed = 5;
            avatarMotionState = 1;
        }

        for (int code: pressed_keys){
            key_map.getOrDefault(code, ()->{}).run();
        }


    }

    public void checkCollisions(ArrayList<Paintable> objects){
        for(Paintable object: objects){

            if(this.getBoundingBox().intersects(object.getBoundingBox())){
                executeCollisionListener(object);
            }
        }
    }


    public void move_forward(){
        if (y - 2 - speed > 0) {
            y -= speed;
        }

        avatarFacing = 0;
    }

    public void move_backward(){
        if (y + 2 + speed < 512) {
            y += speed;
        }

        avatarFacing = 4;
    }

    public void move_left(){
        if (x - 2 - speed > 0) {
            x -= speed;
        }

        avatarFacing = 6;
    }

    public void move_right(){
        if (x + 2 + speed < 512) {
            x += speed;
        }

        avatarFacing = 2;
    }


    public JSONObject getJSON(){

        JSONObject object = new JSONObject();
        object.put("playerID", playerID);
        object.put("xPos", x);
        object.put("yPos", y);
        return object;
    }

}
