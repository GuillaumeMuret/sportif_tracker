#include <SoftwareSerial.h>

// Declare RX and TX GPIO pins used for UART communication with BeagleBone
SoftwareSerial serialUart(A0, A1); // RX, TX

/*
 * Method to transmit the data received by Bluetooth to the BeagleBone using a serial
 * communication protocol
 * @param dataToSend : the data to send to BeagleBone 
 */
void sendDataToBB(int dataToSend){
  serialUart.begin(9600);

  serialUart.flush();
  serialUart.print(dataToSend, HEX);
  serialUart.flush();
  Serial.println(dataToSend, HEX);
}

