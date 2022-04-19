#include <stdio.h>
void delay(int x) //延时函数
{
int i,j;
for(i=0;i<x;i++)
for(j=0;j<x;j++) ;
}
int main()
{
int p1,p2;
while((p1=fork())==-1); //创建子进程 p1
if(p1==0) //子进程 p1 创建成功
{
delay(10); //子进程 p1 延时
putchar('b'); //子进程 p1 显示字符'b'
}else{
while((p2=fork())==-1); //创建子进程 p2 
if(p2==0) //子进程 p2 创建成功
{
delay(10); //子进程 p2 延时
putchar('c'); //子进程 p2 显示字符'c'
}else{
delay(100); //父进程延时
putchar('a'); //父进程显示字符'a'
} }
return 0;
}
