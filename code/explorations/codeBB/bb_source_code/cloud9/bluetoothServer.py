# Bluetooth Server using sockets

import socket
import sys
import bluetooth
import subprocess
from thread import *
from subprocess import call

HOST = ''   # Symbolic name meaning all available interfaces
PORT = 1 # Arbitrary non-privileged port
UUID = "7800110e-0000-1000-8000-00805f9b34fb"

server_socket=bluetooth.BluetoothSocket(bluetooth.RFCOMM)
subprocess.call(['sudo', 'hciconfig', 'hci0', 'piscan'])

try:
    server_socket.bind((HOST, PORT))
except socket.error , msg:
    print 'Bind failed. Error Code : ' + str(msg[0]) + ' Message ' + msg[1]
    sys.exit()
server_socket.listen(10)

print "Listening on port %d" % PORT

#bluetooth.advertise_service(server_socket, "helloService",
#                     service_classes=[bluetooth.SERIAL_PORT_CLASS],
#                     profiles=[bluetooth.SERIAL_PORT_PROFILE])
#bluetooth.advertise_service( server_socket, "SampleServer",
#                       service_id = UUID,
#                       service_classes = [ UUID, bluetooth.SERIAL_PORT_CLASS ],
#                       profiles = [ bluetooth.SERIAL_PORT_PROFILE ] 
#                        )

# Function for handling connections. This will be used to create threads
def client_thread(client_socket):
    # Sending message to connected client
    client_socket.send('Welcome to the server. Type something and hit enter\n') # Send only takes string
     
    # Infinite loop so that function do not terminate and thread do not end
    while True:
        
        data=client_socket.recv(2048)
        if data:
            transfer_data=data
            
            for data in transfer_data:
                client_socket.send(data)
                client_socket.send('Transfer complete')
        else:
            client_socket.send('No data received')
            break
    client_socket.close()
        
# Now keep talking with the client
while True:
    # Wait to accept a connection - blocking call
    client_socket, addr = server_socket.accept()
    print 'Connected with ' + addr[0] + ':' + str(addr[1])
     
    # Start new thread takes 1st argument as a function name to be run,
    # second is the tuple of arguments to the function
    start_new_thread(client_thread ,(client_socket,))
            
server_socket.close()