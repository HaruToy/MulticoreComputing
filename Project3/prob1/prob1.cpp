#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <omp.h>
#include<iostream>
#define num_steps 200000
using namespace std;

int main(int argc, char* argv[]) {
	int i,z;

    int result=1;
	double start_time, end_time;
	int num_thread;
	int type=0; //1 - static , 2 - dynamic, 3 - guided.
    
    if (argc==3) {
		num_thread = atoi(argv[2]);
        type=atoi(argv[1]);
	} else {
		printf("wrong input. ex) a.out [type number] [thread number]\n");
		exit(0);
	}
    
	omp_set_num_threads(num_thread);

	start_time = omp_get_wtime();

    if(type==1) //static
    {
        #pragma omp parallel for reduction(+:result) private(z,i) schedule(static)
        for (i = 2; i<num_steps; i++) {
            for(z=2;z<i;z++){
                if((i%z==0)&&(i!=z)) break;
                if(i-1==z)result++;
            }
        }
    }
    else if(type==2) //dynamic
    {
        #pragma omp parallel for reduction(+:result) private(z,i) schedule(dynamic)
        for (i = 0; i<num_steps; i++) {
            for(z=2;z<i;z++){
                if((i%z==0)&&(i!=z)) break;
                if(i-1==z)result++;
            }
        }
    }
	else if(type==3)  //guided
    {
        #pragma omp parallel for reduction(+:result) private(z,i) schedule(guided)
        for (i = 0; i<num_steps; i++) {
            for(z=2;z<i;z++){
                if((i%z==0)&&(i!=z)) break;
                if(i-1==z)result++;
            }
        }
    }
	end_time = omp_get_wtime();
	double timeDiff = end_time - start_time;
    if(type==1)cout<<"STATIC SCHEDULING"<<endl;
    else if(type==2)cout<<"DYNAMIC SCHEDULING"<<endl;
    else if(type==3)cout<<"GUIDED SCHEDULING"<<endl;
    cout<<"NUM of Thread : "<<num_thread<<endl;
	printf("Execution Time : %lfs\n", timeDiff);

	printf("result = %d\n", result);
}