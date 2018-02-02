package com.company;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.InputStream;

import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread implements Runnable{
    private StreamConnection mConnection;

    // Constant that indicate command from devices
    private static final int EXIT_CMD = -1;
    private static final int KEY_RIGHT = 1;
    private static final int KEY_LEFT = 2;

    public ProcessConnectionThread(StreamConnection connection){
        mConnection=connection;
    }

    @Override
    public void run() {
        try {
            // Prepare to receive data
            InputStream inputStream = mConnection.openInputStream();
            System.out.println("Waiting for data");

            while (true) {
                int command = inputStream.read();
                if (command == EXIT_CMD) {
                    System.out.println("Process finished");
                    break;
                }
                processCommand(command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private void processCommand(int command){
            try {
                // Use Robot class to generate input key events (used to take control of mouse or keyboard)
                Robot robot = new Robot();
                switch (command) {
                    case KEY_RIGHT:
                        robot.keyPress(KeyEvent.VK_RIGHT);
                        System.out.println("Right pressed");
                        break;
                    case KEY_LEFT:
                        robot.keyPress(KeyEvent.VK_LEFT);
                        System.out.println("Left pressed");
                        break;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
