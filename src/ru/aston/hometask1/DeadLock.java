package ru.aston.hometask1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static volatile boolean firstTurn = true;
    private static final String NUMBER_ONE_STRING = "1";
    private static final String NUMBER_TWO_STRING = "2";

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (!firstTurn) condition.await();
                    System.out.println(NUMBER_ONE_STRING);
                    firstTurn = false;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (firstTurn) condition.await();
                    System.out.println(NUMBER_TWO_STRING);
                    firstTurn = true;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}