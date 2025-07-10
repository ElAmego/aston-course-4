package ru.aston.livelock;

public class Main {
    public static void main(String[] args) {
        Thread firstThread = new ThreadGenerator().newThread();
        Thread secondThread = new ThreadGenerator().newThread();

        firstThread.start();
        secondThread.start();
    }
}