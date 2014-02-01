package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static AtomicInteger atomicInteger;

    public static void main(String[] args) {
        String car;
        int option, token;

        System.out.println("Enter size of the parking lot");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        try {
            String size = bufferRead.readLine();
            Attendant attendant = new Attendant();
            atomicInteger = new AtomicInteger();


            ParkingLot parkingLot = new ParkingLot(Integer.parseInt(size), atomicInteger);
            ParkingLot parkingLot1 = new ParkingLot(Integer.parseInt(size),atomicInteger);

            attendant.responsibleFor(parkingLot);
            attendant.responsibleFor(parkingLot1);

            do{
                System.out.println("1 to park, 2 to unpark, 3 to exit");
                option = Integer.parseInt(bufferRead.readLine());

                switch (option){

                    case 1:
                        System.out.println("Enter car number");
                        car = bufferRead.readLine();

                        try{
                            token = attendant.park(car);
                            System.out.println("Car parked. Token is " + token );
                        }catch (CannotParkException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Enter token");
                        token = Integer.parseInt(bufferRead.readLine());
                        try{
                            attendant.unpark(token);
                            System.out.println("Car unparked");
                        }catch (CannotUnparkException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    default:
                        break;
                }

            }while(option!=3);

        } catch (IOException e) {
            System.out.println("Exception");
        }
    }
}
