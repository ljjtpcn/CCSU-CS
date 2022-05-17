/* 文件名: muti_consumer.c
 * 功能: 消费者
 * 模拟多个消费者时，只要将该文件编译后的可执行程序在多个终端分别执行即可
 */
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <unistd.h>
#define SEMNUM 3
#define SHMSIZE 900
typedef union semun {
    int val;
    struct semid_ds *buf;
    short int *array;
} SEMUN;

int Buy(void *outbuffer, void *ConsumeFirstAddr, int pos, int num);
int V(const int semid, char *PType, int len);
int P(const int semid, char *PType, int len);
int Produce(char *buf);
int GetShm(int key, int *shmid, void **shmaddr);
int GetSemCollection(int key, int *semid);
int GetKey();

int main() {
    int semid = 0;
    int shmid = 0;
    int key = 0;
    int num_to_buy = 0; /* Num Of Production */
    int pos = 0;
    int i = 0;
    short int array[SEMNUM];
    char buffer[SHMSIZE];
    char *shmaddr = NULL;
    char *ConsumeFirstAddr = NULL;
    memset(buffer, 0x00, sizeof(buffer));
    memset(array, 0x00, sizeof(array));
    printf("Consumer's ID is[%d]\n", getpid());
    if ((key = GetKey()) < 0) {
        printf("Get Key Failed\n");
        return -1;
    }
    printf("key[%d]\n", key);
    if (GetSemCollection(key, &semid) < 0) {
        printf("Get Sem Failed[%d]\n", errno);
        return -1;
    }
    printf("semid[%d]\n", semid);
    if (GetShm(key, &shmid, &shmaddr) < 0) {
        printf("Get Shm Failed[%d]\n", errno);
        return -1;
    }
    printf("shmid[%d] shmaddr[%d]\n", shmid, shmaddr);
    memcpy(&pos, shmaddr + sizeof(int), sizeof(int));
    ConsumeFirstAddr = shmaddr + 2 * sizeof(int);
    printf("=======pos[%d], ConsumeFirstAddr[%s]===\n", pos, ConsumeFirstAddr);
    while (1) {
        printf("\n\n");
        memset(buffer, 0x00, sizeof(buffer));
        if (semctl(semid, 0, GETALL, array) < 0) {
            printf("Get Sem Val Failed\n");
            return -1;
        }
        for (i = 0; i < SEMNUM; i++) {
            if (i == 0) {
                printf("empty=[%d]\n", array[i]);
            } else if (i == 1) {
                printf("full=[%d]\n", array[i]);
            } else if (i == 2) {
                printf("mutex=[%d]\n", array[i]);
            }
        }
        printf("Please input the num of production you want to buy:");
        scanf("%d", &num_to_buy);
        if (num_to_buy < 0 || num_to_buy > 900) {
            printf("The num input is wrong,it must between 0 and 100\ n");
            continue;
        }
        /* Get Empty Shared Memory */
        printf("Consumer[%d] Now Requiring [%d] production to buy\n", getpid(),
               num_to_buy);
        if (P(semid, "full", num_to_buy) < 0) {
            printf("P full Operation Failed\n");
            return -1;
        }
        printf("Consumer[%d] can buy [%d] productions \n", num_to_buy);
        printf("Consumer[%d] waiting for buying the productions\n", getpid());
        /* Mutex */
        if (P(semid, "mutex", 1) < 0) {
            printf("P mutex Operation Failed\n");
            return -1;
        }
        printf("Now it's turn of the consumer[%d] to buy the productions\n",
               getpid());
        memcpy(&pos, shmaddr + sizeof(int), sizeof(int));
        printf("----->pos[%d] num_to_buy[%d] shmaddr[%s]\n", pos, num_to_buy,
               shmaddr + 2 * sizeof(int));
        if (Buy(buffer, ConsumeFirstAddr, pos, num_to_buy) < 0) {
            printf("Buy Production Failed\n");
            return -1;
        }
        printf("Consumer [%d] Buy Production[%s] shmaddr[%s]\n", getpid(),
               buffer, shmaddr + 2 * sizeof(int));
        pos = (pos + num_to_buy) % SHMSIZE;
        memcpy(shmaddr + sizeof(int), &pos, sizeof(int));
        V(semid, "mutex", 1);
        printf("Consumer [%d] leave the shop\n", getpid());
        V(semid, "empty", num_to_buy);
        printf("Now the shop has [%d] more position to put the productions\n",
               num_to_buy);
    }
    return 0;
}

int GetKey() {
    char filepath[128];
    char cmd[128];
    int key = 0;
    memset(filepath, 0x00, sizeof(filepath));
    memset(cmd, 0x00, sizeof(cmd));
    sprintf(filepath, "%s/key.ini", getenv("HOME"));
    /*
    sprintf(cmd, "touch %s", filepath);
    if ( system(cmd) < 0 )
    {
    printf("Create Key File Failed\n");
    return -1;
    }
    */
    if ((key = ftok(filepath, 20)) < 0) {
        printf("Create Key Failed\n");
        return -1;
    }
    return key;
}
int GetSemCollection(int key, int *semid) {
    int lsemid = 0;
    SEMUN unsem;
    short int array[3];
    if ((lsemid = semget(key, SEMNUM, IPC_CREAT | 0666)) < 0) {
        printf("Get Sem ID Failed\n");
        return -1;
    }
#if 0
array[0] = SHMSIZE; /* Empty Buffer Size */
array[1] = 0; /* Full Buffer Size */
array[2] = 1; /* For Mutex */
unsem.array=array;
if ( semctl(lsemid, 0, SETALL, unsem) < 0 )
{
printf("Init Sem Failed\n");
return -1;
77
}
#endif
    *semid = lsemid;
    return 0;
}
int GetShm(int key, int *shmid, void **shmaddr) {
    int lshmid = 0;
    char *lshmaddr = NULL;
    if ((lshmid = shmget(key, SHMSIZE + 2 * sizeof(int), IPC_CREAT | 0666)) <
        0) {
        printf("Get Shared Memory Failed\n");
        return -1;
    }
    if ((lshmaddr = (char *)shmat(lshmid, 0, 0)) == (char *)-1) {
        printf("Attach Shared Memory Failed, PID[%d]\n", getpid());
        return -1;
    }
    *shmid = lshmid;
    *shmaddr = lshmaddr;
    return 0;
}
int Produce(char *buf) {
    if (buf == NULL) {
        printf("Produce Buffer Couldn't Be NULL\n");
        return -1;
    }
    printf("Please Produce Production:\n");
    gets(buf);
    printf("\nProducer PID[%d] Has Produce Something [%s]\n", buf);
    return 0;
}
int P(const int semid, char *PType, int len) {
    struct sembuf stSembuf;
    memset(&stSembuf, 0x00, sizeof(stSembuf));
    if (PType == NULL) {
        printf("PType Cann't Be NULL\n");
        return -1;
    }
    if (strcmp(PType, "empty") == 0) {
        stSembuf.sem_num = 0;
    } else if (strcmp(PType, "full") == 0) {
        stSembuf.sem_num = 1;
    } else if (strcmp(PType, "mutex") == 0) {
        stSembuf.sem_num = 2;
    } else {
        printf("Sem Type Wrong\n");
        return -1;
    }
    stSembuf.sem_op = (-1) * len;
    stSembuf.sem_flg = SEM_UNDO;
    if (semop(semid, &stSembuf, 1) < 0) {
        printf("P [%s] Operation Failed\n", PType);
        return -1;
    }
    return 0;
}
int V(const int semid, char *PType, int len) {
    struct sembuf stSembuf;
    memset(&stSembuf, 0x00, sizeof(stSembuf));
    if (PType == NULL) {
        printf("PType Cann't Be NULL\n");
        return -1;
    }
    if (strcmp(PType, "empty") == 0) {
        stSembuf.sem_num = 0;
    } else if (strcmp(PType, "full") == 0) {
        stSembuf.sem_num = 1;
    } else if (strcmp(PType, "mutex") == 0) {
        stSembuf.sem_num = 2;
    } else {
        printf("Sem Type Wrong\n");
        return -1;
    }
    stSembuf.sem_op = len;
    stSembuf.sem_flg = SEM_UNDO;
    if (semop(semid, &stSembuf, 1) < 0) {
        printf("P [%s] Operation Failed\n", PType);
        return -1;
    }
    return 0;
}
int Buy(void *outbuffer, void *ConsumeFirstAddr, int pos, int num) {
    int i = 0;
    char *tmp = (char *)ConsumeFirstAddr;
    if (outbuffer == NULL) {
        printf("Parameter 'outbuffer' cann't be NULL\n");
        return -1;
    }
    memcpy(outbuffer, tmp + pos, num);
    for (i = 0; i < num; i++) { tmp[pos + i] = '*'; }
    return 0;
}
