package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingLot {

    private int capacity;
    private ParkingLotObservers observers = new ParkingLotObservers();
    private Map<Integer, Object> cars = new HashMap<Integer, Object>();
    private AtomicInteger atomicInteger;

    public ParkingLot(int capacity, AtomicInteger atomicInteger) {
        this.capacity = capacity;
        this.atomicInteger = atomicInteger;
    }

    public int park(Object car) throws CannotParkException {
        if (isFull())
            throw CannotParkException.becauseLotIsFull(car, this);
        if (hasCar(car))
            throw CannotParkException.becauseCarIsPresentInLot(car, this);
        int token = atomicInteger.getAndIncrement();
        cars.put(token, car);
        if (isFull())
            observers.notifyFull(this);
        return token;
    }

    public Object unpark(int token) throws CannotUnparkException {
        if (!hasCarFor(token)){
            throw new CannotUnparkException(token, this);
        }
        Object car = cars.get(token);
        cars.remove(token);
        if (wasFullBeforeUnpark())
            observers.notifyHasSpace(this);

        return car;
    }

    private boolean hasCar(Object car) {
        return cars.containsValue(car);
    }

    public boolean isFull() {
        return capacity <= cars.size();
    }

    public boolean hasCarFor(Object token) {
        return cars.containsKey(token);
    }

    public void addObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    private boolean wasFullBeforeUnpark() {
        return capacity - cars.size() == 1;
    }

}