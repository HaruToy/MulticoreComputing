package problem1;

public class pc_static {
    private static int NUM_END = 200000;
    static int NUM_THREAD=2;
    public static void main(String[] args){
        int result;

        long startTime = System.currentTimeMillis();
        result=prime(NUM_END);
        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        System.out.println("[Static LB] # of Thread : "+NUM_THREAD+"\nExecution Time : "+timeDiff+"ms");
        System.out.println("1..."+(NUM_END-1)+" prime# counter="+result+"\n");
    }
    static int prime(int len){
        int ans=0;
        long[] ThreadTime = new long[NUM_THREAD];
        PrimeThread[] pt = new PrimeThread[NUM_THREAD];
        for(int i=0;i<NUM_THREAD;i++){
            //For each thread, Determine whether the number within a certain range is a prime number and hand over the result.
            if(i==NUM_THREAD-1)
            {
                pt[i] = new PrimeThread(((NUM_END)/(NUM_THREAD))*i+1,NUM_END);
            }
            else
            {
                pt[i] = new PrimeThread(((NUM_END)/(NUM_THREAD))*i+1,((NUM_END)/(NUM_THREAD))*(i+1));
            }
            long startThreadTime = System.currentTimeMillis();
            ThreadTime[i]=startThreadTime;
            pt[i].start();
        }
        try {
            for(int i=0; i < NUM_THREAD; i++) {
              pt[i].join();
              long endThreadTime = System.currentTimeMillis();
              ThreadTime[i]=endThreadTime-ThreadTime[i];
              System.out.println(i+"th thread's Execution Time : "+ThreadTime[i]+" ");
              //Output the sum of the results returned by the thread.
              ans += pt[i].ans;
            }
          } catch (InterruptedException IntExp) {
          }
        return ans;
    }

}
class PrimeThread extends Thread{
    int lo;
    int hi;
    int ans=0;
    PrimeThread(int l, int h){
        lo=l; hi=h;
    }
    public void run(){
        //Check Prime Number in Thread
        for(int i=lo;i<hi+1;i++)
          if(isPrime(i))ans++;
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