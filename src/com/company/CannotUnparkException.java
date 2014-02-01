package com.company;

public class CannotUnparkException extends Exception {

    public CannotUnparkException(Object token, ParkingLot parkingLot) {
        super("The car corresponding to the token " + token + " was not found in the lot " + parkingLot);
    }

}