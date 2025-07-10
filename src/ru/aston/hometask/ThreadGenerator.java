package ru.aston.hometask;

public class ThreadGenerator implements ThreadFactory {
    private static volatile boolean firstTurn = true;
    private static final Object lock = new Object();

    @Override
    public Thread newThread(String number) {
        return new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if ((number.equals("1") && firstTurn) || (number.equals("2") && !firstTurn)) {
                        System.out.println(number);
                        firstTurn = !firstTurn;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
            }
        });
    }
}