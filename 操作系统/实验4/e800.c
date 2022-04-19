#include <signal.h>
#include <stdio.h>
#include <unistd.h>
int pid1, pid2;
void IntDelete() /* 发送信号函数 */
{
    kill(pid1, 16); /* 发信号 16 */
    kill(pid2, 17); /* 发信号 17 */
}
void Int1() /* 子进程 1 打印信息 */
{
    printf("child process 1 is killes by parent!\n");
    exit(0);
}
void Int2() /* 子进程 2 打印信息 */
{
    printf("child process 2 is killes by parent!\n");
    exit(0);
}
int main() {
    int exitpid;
    signal(SIGINT, SIG_IGN);  /* 忽略 SIGINT 信号 */
    signal(SIGQUIT, SIG_IGN); /* 忽略 SIGQUIT 信号 */
    while ((pid1 = fork()) == -1)
        ;          /* 创建子进程 pid1 */
    if (pid1 == 0) /* 在子进程 pid1 中 */
    {
        printf("p1\n"); /* pid1 打印它的名字 */
        signal(SIGUSR1, Int1); /* pid1 捕捉信号 SIGUSR1,转信号处理函数 Int1() */
        signal(16, SIG_IGN); /* pid1 忽略信号 16 */
        pause();             /* pid1 等待信号被处理 */
        exit(0);             /* pid1 结束 */
    } else {
        while ((pid2 = fork()) == -1)
            ;          /* 创建子进程 pid2 */
        if (pid2 == 0) /* 在子进程 pid2 中 */
        {
            printf("p2\n"); /* pid2 打印它的名字 */
            signal(SIGUSR2,
                   Int2); /*pid2 捕捉信号 SIGUSR2,转信号处理函数 Int2()*/
            signal(17, SIG_IGN); /* pid2 忽略信号 17 */
            pause();             /* pid2 等待信号被处理 */
            exit(0);             /* pid2 结束 */
        } else {
            printf("parent\n"); /* 父进程打印它的名字 */
            signal(SIGINT,
                   IntDelete); /*父进程捕捉 SIGINT 信号,转 IntDelete() */
            waitpid(-1, &exitpid, 0); /* 父进程等待任何子进程结束 */
            printf("parent process is killed!\n"); /* 父进程打印信息 */
        }
    }
    return 0;
}
