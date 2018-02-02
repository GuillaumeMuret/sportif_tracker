import Adafruit_BBIO.UART as UART
import serial

UART.setup("UART1")

ser = serial.Serial(port = "/dev/ttyO1", baudrate=9600)

ser.close()
ser.open()
while ser.isOpen():
    print "Serial is open!"
    #if ser.read()=='A':
    if len(ser.read())>0:
        while ser.read()!='\n':
            for i in range
            print ">"+ser.read()
    #ser.write('A')

ser.close()