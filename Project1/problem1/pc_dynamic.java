package problem1;
import java.util.concurrent.atomic.*;
public class pc_dynamic {
    private static int NUM_END = 200000;
    static int NUM_THREAD=14;
    public static void main(String[] args){
        int result;

        long startTime = System.currentTimeMillis();
        result=prime(NUM_END);
        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        System.out.println("Execution Time : "+timeDiff+"ms");
        System.out.println("1..."+(NUM_END-1)+" prime# counter="+result+"\n");
    }
    static AtomicInteger number=new AtomicInteger(1);
    static int prime(int len){
        int ans=0;
        long[] ThreadTime = new long[NUM_THREAD];
        PrimeDThread[] pt = new PrimeDThread[NUM_THREAD];
            for(int i=0;i<NUM_THREAD;i++){
                pt[i] = new PrimeDThread(number,NUM_END);
                long startThreadTime = System.currentTimeMillis();
                ThreadTime[i]=startThreadTime;
                pt[i].start();
            }
        try {
            for(int i=0; i < NUM_THREAD; i++) {
              pt[i].join();
              long endThreadTime = System.currentTimeMillis();
              ThreadTime[i]=endThreadTime-ThreadTime[i];
              //System.out.println(i+" "+ThreadTime[i]+" ");
              ans += pt[i].ans;
            }
          } catch (InterruptedException IntExp) {
          }
        
        return ans;
    }

}

class PrimeDThread extends Thread{
    AtomicInteger number;
    int ans=0;
    int end;
    PrimeDThread(AtomicInteger n,int e){
        number=n; end=e;
    }
    public void run(){
        int n=number.incrementAndGet();
        while(n<end)
        {
            if(isPrime(n))ans++;
            n=number.incrementAndGet();
        }
    }
    private static boolean isPrime(int x){
        int i;
        if(x<=1) return false;
        for(i=2;i<x;i++){
            if((x%i==0)&&(i!=x)) return false;
        }
        return true;
    }
}
