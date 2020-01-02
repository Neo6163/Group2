#!/usr/bin/env python
import time
import RPi.GPIO as GPIO
from mfrc522 import SimpleMFRC522
reader=SimpleMFRC522()

text1=input('Card Holder Name: ')
text2=input('TOP-UP Value: ')
print("Now place your tag to write")
reader.write(text1)
reader.write(text2)
print("Written")
#finally:
#	GPIO.cleanup()
try:
	while(True):
		t1,t2=reader.read()
		t3=(float(t2)-1.4)
		reader.write(str(t3))
		print("**********************")
		print("Card Holder: ",end=" ")
		print(t1)
		print("Amount charged $1.4")
		print("Current Amount: ",end=" ")	
		y=(float(t2)-1.4)
		print(y) 
		time.sleep(2)
finally:
	GPIO.cleanup()
