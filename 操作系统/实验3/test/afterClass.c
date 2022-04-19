//Linux程序
#include "stdio.h"
#include "unistd.h"
int main()
{
int p1,p2,p3;
p1=fork();
p2=fork();
p3=fork();
//注：getpid()获取当前进程 pid
if(p1>0 && p2>0 && p3>0) printf("A:%d\n",getpid());
if(p1==0 && p2>0 && p3>0) printf("B:%d\n",getpid());
if(p1==0 && p2==0 && p3>0) printf("C:%d\n",getpid());
if(p1==0 && p2==0 && p3==0)printf("D:%d\n",getpid());
if(p1==0 && p2>0 && p3==0) printf("E:%d\n",getpid());
if(p1>0 && p2==0 && p3>0) printf("F:%d\n",getpid());
if(p1>0 && p2==0 && p3==0) printf("G:%d\n",getpid());
 if(p1>0 && p2>0 && p3==0) printf("H:%d\n",getpid());
 sleep(10);
 return 0;
}
