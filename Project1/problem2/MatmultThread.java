package problem2;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
//import java.lang.*;

// command-line execution example) java MatmultD 6 < mat500.txt
// 6 means the number of threads to use
// < mat500.txt means the file that contains two matrices is given as standard input
//
// In eclipse, set the argument value and file input by using the menu [Run]->[Run Configurations]->{[Arguments], [Common->Input File]}.

// Original JAVA source code: http://stackoverflow.com/questions/21547462/how-to-multiply-2-dimensional-arrays-matrix-multiplication
public class MatmultThread
{

  static Scanner sc = new Scanner(System.in);
  static int thread_no=0;
  static int n;
  static int p;
  static int ans[][];
  public static void main(String [] args)
  {
    String path = System.getProperty("user.dir");
    File file = new File(path+"\\Project1\\problem2\\"+args[1]);
    try{
      sc = new Scanner(file);
      
      if (args.length==2) thread_no = Integer.valueOf(args[0]);
      else thread_no = 1;
      System.out.println(args[0]);
      int a[][]=readMatrix();
      int b[][]=readMatrix();

      long startTime = System.currentTimeMillis();
      int[][] c=multMatrix(a,b);
      long endTime = System.currentTimeMillis();

      //printMatrix(a);
      //printMatrix(b);    
      //printMatrix(c);

      //System.out.printf("thread_no: %d\n" , thread_no);
      //System.out.printf("Calculation Time: %d ms\n" , endTime-startTime);

      System.out.printf("[thread_no]:%2d , [Time]:%4d ms\n", thread_no, endTime-startTime);
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

   public static int[][] readMatrix() {
       int rows = sc.nextInt();
       int cols = sc.nextInt();
       int[][] result = new int[rows][cols];
       for (int i = 0; i < rows; i++) {
           for (int j = 0; j < cols; j++) {
              result[i][j] = sc.nextInt();
           }
       }
       return result;
   }

  public static void printMatrix(int[][] mat) {
  System.out.println("Matrix["+mat.length+"]["+mat[0].length+"]");
    int rows = mat.length;
    int columns = mat[0].length;
    int sum = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        System.out.printf("%4d " , mat[i][j]);
        sum+=mat[i][j];
      }
      System.out.println();
    }
    System.out.println();
    System.out.println("Matrix Sum = " + sum + "\n");
  }

  public static int[][] multMatrix(int a[][], int b[][]){//a[m][n], b[n][p]
    long[] ThreadTime = new long[thread_no];
    multiMThread[] mt = new multiMThread[thread_no];
    if(a.length == 0) return new int[0][0];
    if(a[0].length != b.length) return null; //invalid dims

    n = a[0].length;
    int m = a.length;
    p = b[0].length;
    ans = new int[m][p];

    for(int i=0;i<thread_no;i++){
      if(i==thread_no-1)
      {
          mt[i] = new multiMThread(((m)/(thread_no))*i,m,ans,a,b,p,n);
      }
      else
      {
          mt[i] = new multiMThread(((m)/(thread_no))*i,((m)/(thread_no))*(i+1),ans,a,b,p,n);
      }
      long startThreadTime = System.currentTimeMillis();
      ThreadTime[i]=startThreadTime;
      mt[i].start();
  }
  try {
      for(int i=0; i < thread_no; i++) {
        mt[i].join();
        long endThreadTime = System.currentTimeMillis();
        ThreadTime[i]=endThreadTime-ThreadTime[i];
        System.out.println(i+" "+ThreadTime[i]+" ");
      }
    } catch (InterruptedException IntExp) {
    }
    return ans;
  }
}
class multiMThread extends Thread{
  int lo;
  int hi;
  int[][] ans;
  int[][] a;
  int[][] b;
  int p;
  int n;
  multiMThread(int l, int h,int[][] an,int[][] ad,int[][] bd,int pd,int nd){
      lo=l; hi=h; ans=an; a=ad; b=bd; p=pd; n=nd;
  }
  public void run(){
    for(int i = lo;i < hi;i++){
      for(int j = 0;j < p;j++){
        for(int k = 0;k < n;k++){
          ans[i][j] += a[i][k] * b[k][j];
        }
      }
    }
  }
}