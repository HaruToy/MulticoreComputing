package Project2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ParkingGarage{
     BlockingQueue<Integer> queue;

    public ParkingGarage(int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    public synchronized void enter(int result) { // enter parking garage
        try {
            queue.put(result);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public synchronized void leave() { // leave parking garage
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
  }

public class ParkingGarageOperation {
    static int capacity=10;
    static int NUM_THREAD=40;
    public static void main(String[] args) {
        // Let's create a blocking queue that can hold at most 5 elements.
        
        ParkingGarage parkingGarage = new ParkingGarage(capacity);
        // The two threads will access the same queue, in order
        // to test its blocking capabilities.
        for(int i=1;i<=NUM_THREAD;i++)
        {
            Thread Car = new Car(i,parkingGarage);
            Car.start();
        }
    }
}

class Car extends Thread{ 
    private int num;
    private ParkingGarage parking;
    public Car(int n,ParkingGarage p) {
        this.num = n; this.parking=p;
    }
    public void run() {
        while (true) {
            try {
              sleep((int)(Math.random() * 10000)); // drive before parking
            } catch (InterruptedException e) {}
            System.out.println("Car "+num+": trying to enter");
            parking.enter(num);
            System.out.println("Car "+num+": entered");
            try {
              sleep((int)(Math.random() * 5000)); // stay within the parking garage
            } catch (InterruptedException e) {}
            parking.leave();
            System.out.println("Car "+num+": left");
          }
    }
}

