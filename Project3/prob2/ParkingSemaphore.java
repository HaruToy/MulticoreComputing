
import java.util.concurrent.Semaphore;


class ParkinSemaphore {
    static int capacity=5;
    static int NUM_THREAD=40;
    static Semaphore sema=new Semaphore(capacity);
    public static void main(String[] args) {
        // Let's create a blocking queue that can hold at most 5 elements.
        // The two threads will access the same queue, in order
        // to test its blocking capabilities.
        for(int i=1;i<=NUM_THREAD;i++)
        {
            Thread Car = new Car(i);
            Car.start();
        }
    }
}

class Car extends Thread{ 
    private int num;
    
    public Car(int n) {
        this.num = n; 
    }
    public void run() {
        while (true) {
          try{
            try {
              sleep((int)(Math.random() * 10000)); // drive before parking
            } catch (InterruptedException e) {}
            System.out.println("Car "+num+": trying to enter");
            ParkinSemaphore.sema.acquire();
            System.out.println("Car "+num+": entered");
            try {
              sleep((int)(Math.random() * 5000)); // stay within the parking garage
            } catch (InterruptedException e) {}
            ParkinSemaphore.sema.release();
            System.out.println("Car "+num+": left");
          }catch(InterruptedException e){
            e.printStackTrace();
          }
          }
    }
}

