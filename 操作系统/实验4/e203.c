#include <signal.h>
#include <stdio.h>
#include <unistd.h>

void waiting();
void stop();

int wait_mark;

int main() {
    int p1, p2;
    while ((p1 = fork()) == -1)
        ;
    if (p1 > 0) {
        while ((p2 = fork()) == -1)
            ;
        if (p2 > 0) {
            printf("parent\n");
            wait_mark = 1;
            signal(SIGINT, stop);
            waiting();
            kill(p1, 16);
            kill(p2, 17);
            wait(0);
            wait(0);
            printf("parent process is kill!\n");
            exit(0);
        } else {
            printf("p2\n");
			signal(SIGINT,SIG_IGN);
            wait_mark = 1;  //置等待标志
            signal(17, stop);  //捕捉父进程信号 17，调用信号处理函数 stop()
            waiting();            //忙等待
            lockf(stdout, 1, 0);  //锁住标准输出 stdout
            printf("child process 2 is killed by prent!\n");
            lockf(stdout, 0, 0);  //解锁
            exit(0);              //子进程 p2 结束自己
        }
    } else {
        printf("p1\n");
		signal(SIGINT,SIG_IGN);
        wait_mark = 1;  //置等待标志
        signal(16, stop);  //捕捉父进程信号 16，调用信号处理函数 stop()
        waiting();         //忙等待
        lockf(stdout, 1, 0);  //锁住标准输出 stdout
        printf("child process 1 is killed by parent!\n");
        lockf(stdout, 0, 0);  //解锁
        exit(0);              //子进程 p2 结束自己
    }
    return 0;
}
void waiting() //忙等待函数
{
 while(wait_mark!=0); //忙等待!!!
}
void stop()
{
 wait_mark=0; //清除忙等待标志
}

