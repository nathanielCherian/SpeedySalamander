package APFRQ.Unit6;

import javax.annotation.processing.SupportedSourceVersion;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Unit6 {

    public static void main(String[] args) {

        question1(new String[]{"ten", "fading", "post", "card", "thunder", "hinge", "trailing", "batting"});

        Payroll payroll = new Payroll();
        System.out.println(payroll.computeBonusThreshold());
        payroll.computeWages(10, 1.5);

    }

    static void question1(String[] strings){

        for(String string: strings){
            int length;
            if((length=string.length()) < 3) continue;
            String substring = string.substring(length-3);
            if(substring.equals("ing")){
                System.out.println(string);
            }
        }

    }


    private static class Payroll{
        private int[] itemsSold = new int[]{48,50,37,62,38,70,55,37,64,60};
        private double[] wages;

        public double computeBonusThreshold(){

            int sum =0;
            int min = itemsSold[0];
            int max = itemsSold[0];

            for(int item: itemsSold){
                sum += item;
                if(item < min){
                    min = item;
                    continue;
                }

                if(item > max){
                    max = item;
                    continue;
                }
            }

            sum -= min+max;

            return (double) sum/(itemsSold.length-2);
        }

        public void computeWages(double fixedWage, double perItemWage){

            wages = new double[itemsSold.length];
            double thresh = computeBonusThreshold();

            for(int i=0; i<itemsSold.length; i++){
                double wage = itemsSold[i]*perItemWage + fixedWage;
                if(itemsSold[i] > thresh) wage*=1.1;
                wages[i] = wage;
            }

            System.out.println(Arrays.toString(wages));
        }

    }



}
