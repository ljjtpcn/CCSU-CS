/* 文件名: muti_producer.c
 * 功能: 生产者
 * 模拟多个生产者时，只要将该文件编译后的可执行程序在多个终端分别执行即可
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

int GetKey();
int GetShm(int key, int *shmid, void **shmaddr);
int GetSemCollection(int key, int *semid);
int Append(void *AppendFirstAddr, char *buffer, int pos);
int V(const int semid, char *PType, int len);
int P(const int semid, char *PType, int len);
int Produce(char *buf);

int main() {
    int semid = 0;
    int shmid = 0;
    int key = 0;
    int num_of_production = 0; /* Num Of Production */
    int pos = 0;
    char *AppendFirstAddr = NULL;
    int i = 0;
    char buffer[SHMSIZE];
    char *shmaddr = NULL;
    short int array[SEMNUM];
    memset(buffer, 0x00, sizeof(buffer));
    memset(array, 0x00, sizeof(array));
    printf("<------------------------------------------>\n");
    printf("The producer's ID is [%d]\n", getpid());
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
    printf("shmid[%d], shmaddr[%d]\n", shmid, shmaddr);
    printf("<------------------------------------------>\n");
    /* Init the shm's first sizeof(int) unit to 0 */
    memcpy(&pos, shmaddr, sizeof(int));
    AppendFirstAddr = shmaddr + 2 * sizeof(int);
    printf("AppendFirstAddr[%s] pos[%d]\n", AppendFirstAddr, pos);
    while (1) {
        memset(buffer, 0x00, sizeof(buffer));
        printf("\n");
        /* Produce Production */
        if (semctl(semid, 0, GETALL, array) < 0) {
            printf("Get Sem Val Failed\n");
            return -1;
        }
        printf("The resource of the system's list:\n");
        for (i = 0; i < SEMNUM; i++) {
            if (i == 0) {
                printf("empty position=[%d]\n", array[i]);
            } else if (i == 1) {
                printf("full position=[%d]\n", array[i]);
            } else if (i == 2) {
                printf("mutex=[%d]\n", array[i]);
            }
        }
        printf("\n");
        if (Produce(buffer) < 0) {
            printf("Get Shm Failed[%d]\n", errno);
            return -1;
        }
        if (strncmp(buffer, "quit", 4) == 0) {
            printf("All producer's Sem and shm will be over\n");
            break;
        }
        num_of_production = strlen(buffer);
        printf("---->Producer[%d] produce [%d] productions:[%s]<----\n\n",
               getpid(), num_of_production, buffer);
        printf(
            "Producer [%d] Requiring [%d] shop's position to put the "
            "production \n",
            getpid(), num_of_production);
        /* Get Empty Shared Memory */
        if (P(semid, "empty", num_of_production) < 0) {
            printf("P full Operation Failed\n");
            return -1;
        }
        printf("Producer [%d] Get [%d] position of the shop\n", getpid(),
               num_of_production);
        printf(
            "Producer[%d] Requring the right(mutex) to put the production to "
            "the position... \n",
            getpid());
        /* Mutex */
        if (P(semid, "mutex", 1) < 0) {
            printf("P mutex Operation Failed\n");
            return -1;
        }
        printf(
            "Producer[%d] Get the right(mutex) to put the production to right "
            "position\n",
            getpid());
        memcpy(&pos, shmaddr, sizeof(int));
        printf("----->pos[%d]<------\n", pos);
        if (Append(AppendFirstAddr, buffer, pos) < 0) {
            printf("Append Production To Shared Memory Failed\n");
            return -1;
        }
        printf(
            "Producer [%d] put the production [%s] to the position of the "
            "shop\n",
            getpid(), buffer);
        printf("(The production of the shop is )Shared Memory Content[%s]\n",
               AppendFirstAddr);
        pos = (pos + num_of_production) % SHMSIZE;
        memcpy(shmaddr, &pos, sizeof(int));
        V(semid, "mutex", 1);
        printf(
            "Producer [%d] release the operation right(mutex) of the position "
            "of the shop\n",
            getpid());
        V(semid, "full", num_of_production);
        printf("Now Consumer can take [%d] more of production[%s] to consume\n",
               num_of_production, buffer);
    }
    semctl(semid, 0, IPC_RMID);
    shmdt(shmaddr);
    if (shmctl(shmid, IPC_RMID, NULL) < 0) {
        printf("RM Shm Failed[%d]\n", errno);
        return -1;
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
    sprintf(cmd, "touch %s", filepath);
    if (system(cmd) < 0) {
        printf("Create Key File Failed\n");
        return -1;
    }
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
    array[0] = SHMSIZE; /* Empty Buffer Size */
    array[1] = 0;       /* Full Buffer Size */
    array[2] = 1;       /* For Mutex */
    unsem.array = array;
    if (semctl(lsemid, 0, SETALL, unsem) < 0) {
        printf("Init Sem Failed\n");
        return -1;
    }
    *semid = lsemid;
    return 0;
}
int GetShm(int key, int *shmid, void **shmaddr) {
    int lshmid = 0;
    int shmsize = SHMSIZE + 2 * sizeof(int);
    char *lshmaddr = NULL;
    /* shmsize=SHMSIZE+2*sizeof(int);*/
    if ((lshmid = shmget(key, shmsize, IPC_CREAT | 0666)) < 0) {
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
    printf("Producer[%d] Produce Production(Please Input):\n");
    gets(buf);
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
int Append(void *AppendFirstAddr, char *buffer, int pos) {
    char *tmp = (char *)AppendFirstAddr;
    if (AppendFirstAddr == NULL || buffer == NULL) {
        printf("Parameter cann't be NULL[%d]\n", __LINE__);
        return -1;
    }
    printf("---%d--[%d]---\n", pos, __LINE__);
    sprintf(tmp + pos, "%s", buffer);
    return 0;
}
