package ru.aston.hometask2;

public class Main {
    private static boolean firstTurn = true;
    private static final String NUMBER_ONE_STRING = "1";
    private static final String NUMBER_TWO_STRING = "2";

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (firstTurn) {
                    System.out.println(NUMBER_ONE_STRING);
                    firstTurn = false;
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (!firstTurn) {
                    System.out.println(NUMBER_TWO_STRING);
                    firstTurn = true;
                }
            }
        }).start();
    }
}