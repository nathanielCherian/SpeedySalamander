package APFRQ.Unit7;

import java.util.ArrayList;
import java.util.Arrays;

public class UserName {

    private ArrayList<String> possibleNames = new ArrayList<>();

    public UserName(String firstName, String lastName){

        for(int i=1;i<=firstName.length();i++){
            possibleNames.add(lastName+firstName.substring(0,i));
        }

    }

    public boolean isUsed(String name, String[] arr){return true;}

    public void setAvailableUserNames(String[] usedNames){
        possibleNames.removeIf(name->(
            Arrays.asList(usedNames).contains(name)
        ));
    }

    @Override
    public String toString() {
        return possibleNames.toString();
    }

    public static void main(String[] args) {
        UserName person = new UserName("john", "smith");
        System.out.println(person);
        UserName person2 = new UserName("mary", "hart");
        System.out.println(person2);
        String[] used = {"harta", "hartm", "harty"};
        person2.setAvailableUserNames(used);
        System.out.println(person2);

    }

}
