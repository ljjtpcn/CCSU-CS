#! /bin/zsh
# mountusb.sh

#退出程序函数
quit()
{
 clear
 echo "**********************************"
 echo "* Thank you to use ,Good bye! *"
 echo "**********************************"
 exit 0
}
#加载 U 盘函数
mountusb()
{
 clear26
 mkdir /mnt/usb
 /sbin/fdisk -l | grep /dev/sd
 echo "Please Enter the device name of usb as shown above:"
 read PARAMETER
 mount /dev/$PARAMETER /mnt/usb
}
#卸载 U 盘函数
umountusb()
{
 clear
 umount /mnt/usb
}
#显示 U 盘内容函数
display()
{ 27
 clear
 ls -l /mnt/usb
}
#拷贝硬盘文件到 U 盘函数
cpdisktousb()
{
 clear
 echo "Please Enter the filename to be Copied(under current 
directory):"
 read FILE
 echo "Copying,Please wait..."
cp $FILE /mnt/usb
}

#拷贝 U 盘文件到硬盘函数
cpusbtodisk()
{
 clear
 echo "Please Enter the filename to be Copied in USB:"
 read FILE
 echo "Copying,Please wait..."
 cp /mnt/usb/$FILE .
}

clear
while true
do
 echo "========================================"
 echo "    *** UNIX USB MANAGE PROGRAM ***     "
 echo "========================================"
 echo " 1-MOUNT USB                            "
 echo " 2-UMOUNT USB "
 echo " 3-display USB"
 echo " 4-cpdisktousb USB "
 echo " 5-cpusbtodisk USB "
 echo " 0-EXIT "
 echo "========================================"
 echo -e "Please Enter a Choice(0-5):\c"
 read CHOICE
 case $CHOICE in
 1) mountusb;;
 2) umountusb;;
 3) display;;
 4) cpdisktousb;;
 5) cpusbtodisk;;
 0) quit;;
 *) echo "Invalid Choice!Correct Choice is (0-5)"
 sleep 4
 clear;;
 esac
done

