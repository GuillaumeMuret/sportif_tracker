# Bluetooth client for RFCOMM socket


import bluetooth

bd_addr = "00:15:83:EF:3B:FC"

port = 1

sock=bluetooth.BluetoothSocket( bluetooth.RFCOMM )
sock.connect((bd_addr, port))

sock.send("hello!!")

sock.close()