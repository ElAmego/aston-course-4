package ru.aston.hometask1;

public class LiveLock {
    private static boolean mustWait = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (mustWait) {
                    mustWait = false;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    mustWait = true;
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (mustWait) {
                    mustWait = false;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    mustWait = true;
                }
            }
        }).start();
    }
}