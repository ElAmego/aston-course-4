package ru.aston.deadlock;

public class Main {
    private static final String NUMBER_ONE_STRING = "1";
    private static final String NUMBER_TWO_STRING = "2";

    public static void main(String[] args) {
        Thread firstThread = new ThreadGenerator().newThread(NUMBER_ONE_STRING);
        Thread secondThread = new ThreadGenerator().newThread(NUMBER_TWO_STRING);

        firstThread.start();
        secondThread.start();
    }
}
