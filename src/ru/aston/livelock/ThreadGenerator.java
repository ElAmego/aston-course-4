package ru.aston.livelock;

public class ThreadGenerator implements ThreadFactory {
    private static boolean mustWait = true;

    @Override
    public Thread newThread() {
        return new Thread(() -> {
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
        });
    }
}