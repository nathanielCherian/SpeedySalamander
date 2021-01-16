package APFRQ.Unit3;

class Unit3 {
    public static void main(String[] args) {
        boolean rsvp = true;
        String option1 = "";
        String option2 = "";
        int selection = 1;
        if (rsvp) {
            System.out.println("attending");
        } else {
            System.out.println("not attending");
        }

        switch (selection) {
            case 1:
                System.out.println("beef");
                break;
            case 2:
                System.out.println("chicken");
                break;
            case 3:
                System.out.println("pasta");
                break;
        }

        if (rsvp) {
            option1 = "Thanks for attending. You will be served ";
            switch (selection) {
                case 1:
                    option1 += "beef.";
                    break;
                case 2:
                    option1 += "chicken.";
                    break;
                case 3:
                    option1 += "pasta.";
                    break;
            }
        }
        else {
            option1 = "Sorry you can't make it.";
        }
    }
}