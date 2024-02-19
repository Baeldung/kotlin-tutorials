package com.baeldung.comparison;

public class SwitchExample {
    public static void main(String[] args) {
        int number = 3;
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
        System.out.println(description);
    }
}
