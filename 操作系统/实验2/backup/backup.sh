#!/bin/zsh
# backup.sh

#备份目录函数
backupdir()
{
   dirtest
   echo "Backupping..."
   tar -zcvf /home/ljjtpcn/Desktop/backup.tar.gz $DIRECTORY
}

#恢复目录函数
restoredir()
{
   dirtest
   echo "Restoring..."
   tar -xzvf /home/ljjtpcn/Desktop/backup.tar.gz
}

#验证目录函数
dirtest()
{
   echo "Please enter the directory name of backup file:"
   read DIRECTORY
   if [ ! -d $DIRECTORY ]
   then
     echo "Sorry,$DIRECTORY is not a directory!"
     exit 1
   fi
   cd $DIRECTORY
}

clear
ANS=Y
while [ $ANS = Y -o $ANS = y ] 
do
  echo "==========================="
  echo "=   Backup-Restore Men    ="
  echo "+++++++++++++++++++++++++++"
  echo "+   1:Backup Directory    +"
  echo "+   2:Restore Directory   +"
  echo "+   3:Exit                +"
  echo "+++++++++++++++++++++++++++"
  echo "Please enter a choice(1-3):"

  read CHOICE
  case "$CHOICE" in
    1) backupdir;;
    2) restoredir;;
    3) exit 1;;
    *) echo "Invalid Choice!"
       exit 1;;
  esac
  
  if [ $? -ne 0 ] 
  then 
    echo "Program encounter error!"
    exit 2
  else
    echo "Operate successfully!"
  fi
  
  echo "Would you like to continue ? Y/y to continue, any other key to exit:"
  read ANS
  clear
done
