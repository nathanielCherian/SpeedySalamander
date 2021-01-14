package APFRQ.Unit4;
import java.util.Scanner;

public class unit4 {

    int coins1 = 30;
    int coins2 = coins1;


    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        unit4 myUnit = new unit4();
        myUnit.nextTurn();
    }

    public void nextTurn() {

        System.out.printf("Player 1, you have %d coins. How much do you want to spend (1, 2, or 3)?\n", coins1);
        int spend1 = Integer.parseInt(scanner.nextLine());

        if (!(spend1 == 1 || spend1 == 2 ||spend1 == 3)) {
            return;
        }

        coins1 -= spend1;

        System.out.printf("Player 2, you have %d coins. How much do you want to spend (1, 2, or 3)?\n", coins2);
        int spend2 = Integer.parseInt(scanner.nextLine());

        if (!(spend2 == 1 || spend2 == 2 ||spend2 == 3)) {
            return;
        }

        coins2 -= spend2;

        if (spend1 == spend2) {
            coins2 += 1;
        } else if (Math.abs((spend1 - spend2)) == 1) {
            coins2 += 1;
        } else if (Math.abs((spend1 - spend2)) == 2) {
            coins1 += 2;
        }

        nextTurn();
    }

    public int getPlayer2Move(int round) {
        if(round%3 == 0) {
            return 3;
        } else if(round%2 == 0) {
            return 2;
        } else {
            return 1;
        }
    }

}
