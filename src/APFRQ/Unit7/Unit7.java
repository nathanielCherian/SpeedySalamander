package APFRQ.Unit7;

import java.util.ArrayList;

public class Unit7 {

// The list of possible user names, based on a user’s first and last names and initialized by the constructor.

    private ArrayList<String> possibleNames;



    /** Constructs a UserName object as described in part (a).

     * Precondition: firstName and lastName have length greater than 0

     * and contain only uppercase and lowercase letters.

     */

    public Unit7(String firstName, String lastName) {
        for (int i = 1; i < firstName.length(); i++) {
            String name = lastName + firstName.substring(0, i);
            possibleNames.add(name);
        }
    }



    /** Returns true if arr contains name, and false otherwise. */

    public boolean isUsed(String name, String[] arr)

    { /* implementation not shown */
    return true;
    }



    /** Removes strings from possibleNames that are found in usedNames as described in part (b).

     */

    public void setAvailableUserNames(String[] usedNames) {
        for (String name: usedNames) {
            if (possibleNames.contains(name)) {
                possibleNames.remove(name);
            }
        }
    }

}