package com.company;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class WaitThread implements Runnable{
    /*Constructor*/
    public WaitThread(){
    }

    @Override
    public void run(){
        waitForConnection();
    }

    /* Waiting for connection from remote devices*/
    private void waitForConnection() {
        // Retrieve local Bluetooth device object
        LocalDevice local = null;

        StreamConnectionNotifier connectionNotifier;
        StreamConnection connection = null;

        try {
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);

            // Creating the UUID for the application
            UUID uuid = new UUID(80087355);

            // Set RFCOMM server url
            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetoothServer";
            connectionNotifier = (StreamConnectionNotifier) Connector.open(url);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                System.out.println("Waiting for connection...");
                connection = connectionNotifier.acceptAndOpen();

                // If a connection is detected, an thread is created to handle connection
                Thread processThread = new Thread(new ProcessConnectionThread(connection));
                processThread.start();
            }catch(Exception e){
                e.printStackTrace();
                return;
            }
        }
    }
}
