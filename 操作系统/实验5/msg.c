#include <stdio.h>
#include <sys/types.h>
#include <sys/msg.h>
#include <sys/ipc.h>
#define MSGKEY 75

struct msgform{
	long mtype;
	char mtext[1030];
}msg;
int msgqid;

void Client()
{
	int i;
	msgqid = msgget(MSGKEY, 0777);
	for(i = 10; i >= 1; i --){
		msg.mtype = i;
		printf("(client %d) sent.\n", i);
		msgsnd(msgqid, &msg, 1024, 0);
		sleep(1);
	}
	exit(0);
}

void Server()
{
	msgqid = msgget(MSGKEY, 0777|IPC_CREAT);
	do{
		msgrcv(msgqid, &msg, 1030, 0, 0);
		printf("(server %d) received.\n", msg.mtype);
	}while(msg.mtype != 1);
	msgctl(msgqid, IPC_RMID, 0);
	exit(0);
}


int main()
{
	int i;
	while((i = fork()) == -1);
	if(!i) Server();
	else{
		while((i = fork()) == -1);
		if(!i) Client();
	}
	wait(0);
	wait(0);
	return 0;
}
