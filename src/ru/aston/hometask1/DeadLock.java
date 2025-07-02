package ru.aston.hometask1;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean firstTurn = true;

    public static void main(String[] args) {
        new Thread(() ->{
            while (true) {
                lock.lock();
                try {
                    while (!firstTurn) condition.await();
                    System.out.println(1);
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
                    System.out.println(2);
                    firstTurn = true;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}