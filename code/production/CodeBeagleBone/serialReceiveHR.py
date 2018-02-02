import Adafruit_BBIO.UART as UART
import serial
import time
import sys, os
import socket
import re

# Setup UART
UART.setup("UART1")
ser = serial.Serial(port = "/dev/ttyO1", baudrate = 9600,
               parity=serial.PARITY_NONE,
               stopbits=serial.STOPBITS_ONE,
               bytesize=serial.EIGHTBITS,
               timeout=1)
               
# Launch shell script configuring UART GPIO pins
os.system('./uartConfig.sh')

# Define socket parameters
host="localhost"
port=55000

# Create the socket that will send Heart Rate data to the server
try:
    socket=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    socket.connect((host, port))
except Exception as e:
    socket.send("Error attempting to open socket and connect to localhost")
    exit()
               
# Open the socket
try:
    ser.close()
    ser.open()
except Exception as e:
    socket.send("error trying to open serial port:"+str(e))
    exit()
    
# While seriel port is opened, retrieve data
if ser.isOpen():
    # Flush the buffers
    try:
        ser.flushInput()
        ser.flushOutput()
    except Exception.e1:
        socket.send("error flushing buffers")
        
    # Read input data and process it
    i=0
    while True:
        receivedData=ser.readline()
        if len(receivedData)>0 and receivedData!='' and receivedData != '\r':
            serialData=[]   
            serialData+=receivedData    # serialData = buffer containing read bytes. Ex : ['1','1','0'] => deviceId = 0x1, heartBeatsForTenSec = 0x10 (16 beats)
            bpmCounter=""
            if len(serialData)>1:
                i=1
                while i < len(serialData):
                    bpmCounter+=serialData[i]   # All data in the buffer after the first byte is heart beat data so we append them together in a hex string
                    i=i+1
            else:
                bpmCounter=""
            
            # Build JSON string to send to the server
            stringJSON="{\"process\" : \"sendBpm\",\"params\" : {\"sensorId\" : \""+serialData[0]+"\",\"bpmForTenSec\" : \""+bpmCounter+"\"}}\r\n"
            socket.send(stringJSON)
            sys.stdout.flush()
        else:
            serialData=[]
else:
    socket.send("Cannot read from serial port")

# Once operation is ended, close serial port and socket
ser.close()
socket.close()