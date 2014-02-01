package com.company;

import java.util.ArrayList;
import java.util.List;

public class Attendant implements ParkingLotObserver {

    private List<ParkingLot> allLots = new ArrayList<ParkingLot>();
    private List<ParkingLot> availableLots = new ArrayList<ParkingLot>();

    public void responsibleFor(ParkingLot lot) {
        allLots.add(lot);
        lot.addObserver(this);
        if (!lot.isFull())
            availableLots.add(lot);
    }

    public int park(Object car) throws CannotParkException {
        if (availableLots.isEmpty())
            throw CannotParkException.becauseAllLotsAreFull(car, this);
        return availableLots.get(0).park(car);
    }

    public Object unpark(int token) throws CannotUnparkException {
        for (ParkingLot lot : allLots) {
            if (lot.hasCarFor(token))
                return lot.unpark(token);
        }
        throw new CannotUnparkException(token, this);
    }

    public void notifyFull(ParkingLot lot) {
        availableLots.remove(lot);
    }

    public void notifyHasSpace(ParkingLot lot) {
        availableLots.add(lot);
    }

}
