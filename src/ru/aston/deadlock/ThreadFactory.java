package ru.aston.deadlock;

public interface ThreadFactory {
    Thread newThread(final String number);
}