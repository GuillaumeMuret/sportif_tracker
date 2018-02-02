import Adafruit_BBIO.UART as UART
import serial

UART.setup("UART2")

ser = serial.Serial(port = "/dev/ttyO2", baudrate=9600)

ser.close()
ser.open()
while ser.isOpen():
    print "Serial is open!"
    #if ser.read()=='A':
    #print ">"+ser.read()
    ser.write('A')

ser.close()