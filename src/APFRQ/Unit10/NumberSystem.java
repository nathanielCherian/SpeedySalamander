package APFRQ.Unit10;

public class NumberSystem {

    /** Precondition: a and b are positive integers.
     * Returns the greatest common factor of a and b, as described in part (a).
     */

    public static int gcf(int a, int b){
        int modResult = a%b;
        if (modResult == 0) return b;
        return gcf(b, modResult);
    }

    public static void reduceFraction(int numerator, int denominator){
        int gcf = gcf(numerator, denominator);
        String reduced = (((((float)numerator/denominator)%1)==0)? ""+numerator/gcf:""+numerator/gcf+"/"+denominator/gcf);
        System.out.format("%d/%d reduces to %s", numerator, denominator, reduced);

    }

    public static void main(String[] args) {
        System.out.println(gcf(24,6));
        reduceFraction(7, 3);
    }

}
