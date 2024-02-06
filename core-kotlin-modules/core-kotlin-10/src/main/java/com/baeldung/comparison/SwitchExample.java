package com.baeldung.comparison;

public class SwitchExample {
    public static void main(String[] args) {
        int dayOfWeek = 3;

        switch (dayOfWeek) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            default:
                System.out.println("Weekend");
        }
    }

    public String describeNumberJava(int number) {
        String description;
        switch (number) {
            case 0:
                description = "Zero";
                break;
            case 1:
            case 2:
                description = "One or Two";
                break;
            case 3:
            case 4:
            case 5:
                description = "Between Three and Five";
                break;
            default:
                description = "Other";
        }
        return description;
    }

}


