package com.company;

interface ParkingLotObserver {

    public abstract void notifyFull(ParkingLot lot);

    public abstract void notifyHasSpace(ParkingLot lot);

}
