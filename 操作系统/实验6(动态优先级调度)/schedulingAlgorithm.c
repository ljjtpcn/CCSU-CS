#include <stdio.h>
#include <unistd.h>
#include<stdlib.h>
#include <string.h>
typedef struct pcb
{
    int pid;
    int prior;
    char status[10];
    int time;
    struct pcb *next;
} PCB;

FILE *fp = NULL;

int main()
{
    int iNumProc = 0;
    int i = 0;
    PCB *stPCBHead = NULL;
    PCB *stPCB = NULL;
    PCB *stPCBFront = NULL;
    PCB *stPCBNode = NULL;

    char filename[128];

    memset(filename, 0x00, sizeof(filename));

    sprintf(filename, "/home/ljjtpcn/Desktop/schedule.txt");
    if ((fp = fopen(filename, "w")) == NULL)
    {
        printf("Create log file failed!\n");
        return -1;
    }

    /* Input the number of process */
    printf("Please input the number of process\n");
    scanf("%d", &iNumProc);
    /* Process number must great than 0 */
    while (iNumProc <= 0)
    {
        printf("Ther number of process cann't be 0! \n");
        printf("Please input the number of process again\n");
        scanf("%d", &iNumProc);
    }
    for (i = 0; i < iNumProc; i++)
    {
        /* Create process's node */
        stPCBNode = (PCB *)malloc(sizeof(PCB));
        if (stPCBNode == NULL)
        {
            printf("malloc memory failed!\n");
            return -1;
        }
        memset(stPCBNode, 0x00, sizeof(PCB));
        /* Init process's information */
        stPCBNode->pid = i; /* Process ID */
        printf("Please input ther prior of process[%d]\n", i);
        scanf("%d", &stPCBNode->prior); /* Process's prior */
        while (stPCBNode->prior < 0)
        {
            printf("Process's prior must great than 0\n");
            printf("Please input ther run time of process[%d]\n", i);
            scanf("%d", &stPCBNode->prior);
        }
        printf("Please input ther run time of process[%d][%d]\n", i, __LINE__);
        scanf("%d", &stPCBNode->time);
        while (stPCBNode->time < 0)
        {
            printf("Process's run time must great than 0\n");
            printf("Please input ther run time of process[%d]\n", i);
            scanf("%d", &stPCBNode->time);
        }
        strcpy(stPCBNode->status, "ready"); /* Init the process's status ready
                                             */
        stPCBNode->next = NULL;
        /* Link the new process's node */
        if (stPCBHead == NULL)
        {
            stPCBHead = stPCBNode;
            continue;
        }
        stPCB = stPCBHead;
        stPCBFront = stPCBHead;
        while (stPCB != NULL && stPCB->prior >= stPCBNode->prior)
        {
            stPCBFront = stPCB;
            stPCB = stPCB->next;
        }
        if (stPCB == stPCBHead)
        {
            stPCBNode->next = stPCBHead;
            stPCBHead = stPCBNode;
        }
        else if (stPCB == NULL)
        {
            stPCBFront->next = stPCBNode; /* Add to the tail of the link
                                           */
        }
        else /* insert into the link */
        {
            stPCBNode->next = stPCB;
            stPCBFront->next = stPCBNode;
        }
        printf("Create the process [%d] success\n", i);
    }
    fprintf(fp, "Create %d processes success\n", iNumProc);
    printf("In the begin of schedule, these process's queque\n");
    fprintf(fp, "Before Schedule\n");
    printProc(stPCBHead);
    sleep(1);
    /* Now Schedual */
    printf("Begin schedule\n");
    fprintf(fp, "Begin schedule\n");
    stPCB = stPCBHead;

    while (iNumProc > 0)
    {
        /* schedule from first process */
        if (strcmp(stPCBHead->status, "ready") == 0)
            strcpy(stPCBHead->status, "running");
        printProc(stPCBHead);
        stPCBHead->time--;
        stPCBHead->prior--;
        /* Update the the level of proccess which in wait status */
        for (stPCB = stPCBHead; stPCB != NULL; stPCB = stPCB->next)
        {
            if (strcmp(stPCB->status, "ready") == 0)
            {
                stPCB->prior++;
            }
        }
        /* sort the schedule again */
        sort(&stPCBHead, &iNumProc); /* Sort the link and delete which the
process's time is 0 */
    }
    return 0;
}


int sort(PCB **pstPCBHead, int *iNumProc)
{
    PCB *pstPCB = NULL;
    PCB *pstPCBFront = NULL;
    int flag = 0;
    pstPCB = (*pstPCBHead)->next;
    pstPCBFront = (*pstPCBHead);
    if ((*pstPCBHead)->time == 0)
    {
        printf("Proccess [%d] run end!\n", pstPCBFront->pid);
        (*pstPCBHead) = (*pstPCBHead)->next;
        free(pstPCBFront);
        pstPCBFront = NULL;
        (*iNumProc)--;
        return 0;
    }
    if ((*iNumProc) <= 1)
    {
        return 0;
    }
    while (pstPCB != NULL && (*pstPCBHead)->prior <= pstPCB->prior)
    {
        pstPCBFront = pstPCB;
        pstPCB = pstPCB->next;
        flag = 1;
    }
    if (pstPCB == NULL && flag == 1)
    {
        strcpy((*pstPCBHead)->status, "ready");
        pstPCBFront->next = (*pstPCBHead);
        *pstPCBHead = (*pstPCBHead)->next;
        pstPCBFront->next->next = NULL;
    }
    else if (flag == 1)
    {
        strcpy((*pstPCBHead)->status, "ready");
        pstPCBFront->next = (*pstPCBHead);
        (*pstPCBHead) = (*pstPCBHead)->next;
        pstPCBFront->next->next = pstPCB;
    }
    return 0;
}

int printProc(PCB *pstPCBHead)
{
PCB *pstPCB=pstPCBHead;
printf("-----------------------------------------------------------\n");
printf("| Process's ID | Process's prior| Process's Stauts |The time left|Current Process Addr|Next Process Addr|\n");
printf("-----------------------------------------------------------\n");
fprintf(fp, "---------------------------------------------------------\n");
fprintf(fp, "| Process's ID | Process's prior| Process's Stauts |The time left|Current Process Addr|Next Process Addr|\n");
fprintf(fp, "-----------------------------------------------------------\n");
while ( pstPCB != NULL )
{
sleep(1);
printf("|%16d|%16d|%18s|%13d|%20d|%17d|\n", pstPCB->pid, 
pstPCB->prior, pstPCB->status, pstPCB->time, pstPCB, pstPCB->next);
printf("------------------------------------------------------\n");
fprintf(fp, "|%16d|%16d|%18s|%13d|%20d|%17d|\n", pstPCB->pid, 
pstPCB->prior, pstPCB->status, pstPCB->time, pstPCB, pstPCB->next);
fprintf(fp, "--------------------------------------------\n");
pstPCB=pstPCB->next;
}
printf("\n\n");
fprintf(fp, "\n\n");
return 0;
}
