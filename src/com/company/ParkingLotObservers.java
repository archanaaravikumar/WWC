package com.company;

import java.util.ArrayList;

public class ParkingLotObservers extends ArrayList<ParkingLotObserver> {

    public void notifyHasSpace(ParkingLot lot) {
        for (ParkingLotObserver observer : this) {
            observer.notifyHasSpace(lot);
        }
    }

    public void notifyFull(ParkingLot lot) {
        for (ParkingLotObserver observer : this) {
            observer.notifyFull(lot);
        }
    }

}
