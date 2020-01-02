import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)
GPIO.setup(2,GPIO.IN)
GPIO.setup(3,GPIO.IN)
count=0
print("Traveller Counter")
while 1:
	if(GPIO.input(2)==False):
		count=count+1
		print("No. of Passengers: ",end=" ")
		print(count)
		time.sleep(2)

	if(GPIO.input(3)==False):
		if(count==0):
			continue
		else:
			count=count-1
			print("No. of Passengers: ",end=" ")
			print(count)
			time.sleep(2)
