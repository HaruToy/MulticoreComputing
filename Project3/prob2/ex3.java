import java.util.concurrent.atomic.*;
//AtomicInteger
public class ex3 {
    static AtomicInteger atomicInteger = new AtomicInteger();
    public static void main(String[] args) throws Exception{
    
        Read read = new Read();
        Setter setter = new Setter();
        GetNumAddNum adder1 = new GetNumAddNum();
        AddNumGetNum adder2 = new AddNumGetNum();
        System.out.println("Initial Num : "+atomicInteger.get());
        

            new Thread(adder1).start();
            new Thread(read).start();
            new Thread(adder2).start();
            new Thread(setter).start();
        
        Thread.sleep(5000);
        System.out.println("Final Num : "+atomicInteger.get());
    }
}
class Read implements Runnable{
    public void run()
    {
        System.out.println("read");
        System.out.println("read complete "+ex3.atomicInteger.get());
    }
}
class Setter implements Runnable{
    public void run()
    {
        System.out.println("set to 50");
        ex3.atomicInteger.set(50);  
        System.out.println("Set complete");
    }
}
class GetNumAddNum implements Runnable{
    public void run()
    {
        System.out.println("Get number before add..");
        System.out.println("Complete to Get number before add "+ex3.atomicInteger.getAndAdd(10));
    }
}
class AddNumGetNum implements Runnable{
    public void run()
    {
        System.out.println("Get number after add..");
        System.out.println("Complete to Get number after add.. "+ex3.atomicInteger.addAndGet(10));
    }
}

