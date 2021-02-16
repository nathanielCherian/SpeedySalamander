package APFRQ.Unit10;

public class NumberSystem {

    /** Precondition: a and b are positive integers.
     * Returns the greatest common factor of a and b, as described in part (a).
     */

    public static int gcf(int a, int b){
        if(a%b == 0){
            return b;
        }
        return  1;
    }

}
