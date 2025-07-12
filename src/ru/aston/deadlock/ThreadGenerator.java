package ru.aston.deadlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadGenerator implements ThreadFactory {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static volatile boolean firstTurn = true;

    @Override
    public Thread newThread(final String number) {
        return new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (!firstTurn) condition.await();
                    System.out.println(number);
                    firstTurn = false;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }
}