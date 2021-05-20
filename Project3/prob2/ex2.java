import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//ReadWriteLook
public class ex2 {
    static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static int num=0;
    public static void main(String[] args) throws Exception{
    
        Reader1 reader1 = new Reader1();
        Reader2 reader2 = new Reader2();
        Writer1 writer1 = new Writer1();
        Writer2 writer2 = new Writer2();

        for(int i=0;i<5;i++)
        {
        new Thread(writer1).start();
        new Thread(reader1).start();
        new Thread(writer2).start();
        new Thread(reader2).start();
        }
    }
}
class Reader1 implements Runnable{
    public void run(){
        ex2.readWriteLock.readLock().lock();
        System.out.println("The number is "+Integer.toString(ex2.num));
        ex2.readWriteLock.readLock().unlock();

    }
}
class Reader2 implements Runnable{
    public void run(){
        ex2.readWriteLock.readLock().lock();
        System.out.println("The number is "+Integer.toString(ex2.num));
        ex2.readWriteLock.readLock().unlock();

    }
}
class Writer1 implements Runnable{
    public void run(){
        ex2.readWriteLock.writeLock().lock();
        ex2.num=ex2.num+1;
        System.out.println("Adding...");
        ex2.readWriteLock.writeLock().unlock();
        try {
            Thread.sleep((int)(Math.random() * 5000)); 
          } catch (InterruptedException e) {}
    }
}
class Writer2 implements Runnable{
    public void run(){
        ex2.readWriteLock.writeLock().lock();
        ex2.num=ex2.num-1;
        System.out.println("Subtracting...");
        ex2.readWriteLock.writeLock().unlock();
        try {
            Thread.sleep((int)(Math.random() * 5000)); 
          } catch (InterruptedException e) {}
    }
}