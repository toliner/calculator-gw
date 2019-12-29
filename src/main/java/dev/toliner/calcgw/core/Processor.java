package dev.toliner.calcgw.core;

import dev.toliner.calcgw.core.values.IntegerValue;

import java.util.Deque;
import java.util.LinkedList;

public class Processor {

    private static final Processor instance = new Processor();
    private final Deque<Object> pushedKeys = new LinkedList<>();

    private Processor() {
    }

    public static Processor getInstance() {
        return instance;
    }

    public void addKeyPressed(Object obj) {
        if (obj instanceof Integer) {
            pushedKeys.add(obj);
        } else if (obj instanceof String) {
            StringBuilder num = new StringBuilder();
            while (pushedKeys.peek() instanceof Integer) {
                num.append(pushedKeys.pop());
            }
            pushedKeys.add(new IntegerValue(Integer.parseInt(num.reverse().toString())));
            pushedKeys.add(obj);
        } else {
            throw new IllegalArgumentException("obj must be able to convert to Expression");
        }
    }

    public void eval() {

    }

    public Expression parse() {
        throw new RuntimeException();
    }
}
