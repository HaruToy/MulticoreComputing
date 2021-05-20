import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//BlockingQueue
public class ex1 {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        
        Produc producer = new Produc(queue);
        Consum consumer = new Consum(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
        Thread.sleep(10000);
    }
}
class Produc implements Runnable{
    protected BlockingQueue<String> queue= null;
    public Produc(BlockingQueue<String> queue){
        this.queue=queue;
    }
    public void run() {
        try {
            queue.put("1");
            System.out.println("1 in");
            Thread.sleep(1000); 
            queue.put("2");
            System.out.println("2 in");
            Thread.sleep(1000);
            queue.put("3");
            System.out.println("3 in");
            Thread.sleep(1000);
            queue.put("4");
            System.out.println("4 in");
            Thread.sleep(1000);
            queue.put("5");
            System.out.println("5 in");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Consum implements Runnable{

    protected BlockingQueue<String> queue = null;

    public Consum(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println(queue.take()+" out");
            System.out.println(queue.take()+" out");
            System.out.println(queue.take()+" out");
            System.out.println(queue.take()+" out");
            System.out.println(queue.take()+" out");
            System.out.println(queue.take()+" out");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

