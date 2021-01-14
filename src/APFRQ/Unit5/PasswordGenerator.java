package APFRQ.Unit5;

import java.util.Random;

public class PasswordGenerator {

    private static int count = 0;
    private int digits;
    private String prefix;
    private Random random = new Random();

    public PasswordGenerator(int digits){
        this.digits = digits;
        this.prefix = "A";
    }

    public PasswordGenerator(int digits, String prefix){
        this.digits = digits;
        this.prefix = prefix;
    }


    public int pwCount(){
        return count;
    }

    public String pwGen(){
        String password = prefix+".";
        for(int i=0;i<4;i++){
            password += Integer.toString(random.nextInt(10));
        }
        count += 1;
        return password;
    }


    public static void main(String[] args) {

        PasswordGenerator pw1 = new
        PasswordGenerator(4, "chs");
        System.out.println(pw1.pwCount());
        System.out.println(pw1.pwGen());
        System.out.println(pw1.pwGen());
        System.out.println(pw1.pwCount());

        PasswordGenerator pw2 = new
        PasswordGenerator(6);
        System.out.println(pw2.pwCount());
        System.out.println(pw2.pwGen());
        System.out.println(pw2.pwCount());
        System.out.println(pw2.pwCount());



    }


}
