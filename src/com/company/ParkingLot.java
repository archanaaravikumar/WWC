package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingLot {

    private int capacity;
    private Map<Integer, Object> cars = new HashMap<Integer, Object>();
    private AtomicInteger atomicInteger;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        atomicInteger = new AtomicInteger();
    }

    public int park(Object car) throws CannotParkException {
        if (isFull())
            throw CannotParkException.becauseLotIsFull(car, this);
        if (hasCar(car))
            throw CannotParkException.becauseCarIsPresentInLot(car, this);
        int token = atomicInteger.getAndIncrement();
        cars.put(token, car);
        return token;
    }

    public Object unpark(int token) throws CannotUnparkException {
        if (!hasCarFor(token)){
            throw new CannotUnparkException(token, this);
        }
        Object car = cars.get(token);
        cars.remove(token);
        return car;
    }

    private boolean hasCar(Object car) {
        return cars.containsValue(car);
    }

    private boolean isFull() {
        return capacity <= cars.size();
    }

    public boolean hasCarFor(Object token) {
        return cars.containsKey(token);
    }
}