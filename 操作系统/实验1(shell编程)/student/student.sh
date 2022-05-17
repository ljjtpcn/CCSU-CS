#!/bin/zsh
# student.sh

#exit method
quit()
{
   clear
   exit
}

#add record method
add()
{
   clear
   echo "Enter name and score of a record."
   echo "\c"
   if [ ! -f ./record ] ; then
     touch record
   fi
   read NEWNAME
   echo "$NEWNAME" >> ./record
   echo "Add Successfully"
   sort -o ./record ./record
}

#search method
 search()
 {
    clear
    echo -n "Please Enter Name >>>"
    read NAME
    if [ ! -f ./record ]; then
      echo "You must have some scores before you can search!"
      sleep 2
      clear
      return
    fi
    until [ ! -z "$NAME" ] 
    do
      echo "You didn't enter a name!"
      echo "Please Enter NAME >>>"
      read NAME
    done
    grep -i "$NAME" ./record 2> /dev/null
    if [ $? = 1 ];then
      echo "Name not in recond."
    fi
 }

#delete method
delete()
{
    clear
    echo "Please Enter Name >>>"
    read NAME
    if [ ! -f ./record ]; then
      echo "You must have some scores before you can search"
      sleep 5
      clear
      return
    fi
    until [ ! -z "$NAME" ]
    do
      echo "You did't enter a name!"
      echo "Please Enter Name >>>"
      read NAME
    done
    grep -i "$NAME" ./record 2> /dev/null
    if [ $? = 1 ];then
      echo "Name not in record."
    else
      cp record record.bak
      rm -f record
      grep -vi "$NAME" ./record.bak > record
      rm -f record.bak
      echo "Delete Successfully!"
    fi
}

#display method
display()
{
    more ./record
}

clear
while true
do
  echo "************************************"
  echo "*      STUDENT'S RECORD MENU       *"
  echo "************************************"
  echo "####################################"
  echo "#       1:ADD    A   RECORD        #"
  echo "#       2:SEARCH     RECORD        #"
  echo "#       3:DELETE A   RECORD        #"
  echo "#       4:SHOW ALL   RECORD        #"
  echo "#       Q:EXIT                     #"
  echo "####################################"
  echo -n "Please Enter Your Choice {1,Q}:"
  read CHOICE
  case $CHOICE in
    1) add;;
    2) search;;
    3) delete;;
    4) display;;
    Q|q) quit;;
    *) echo "Invalid Choice!";
       sleep 2;
       clear;;
  esac
 done


