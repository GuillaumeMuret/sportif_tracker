package com.neos.trackandroll.cervo.communication.armband;

import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;


public class PythonExecutor {

    public static final String PYTHON_FILENAME = "serialReceiveHR.py";

    class LaunchScript extends Thread {
        List<String> cmd = new ArrayList<String>();

        public void run(){
    
            try{
                //Build command by adding arguments
                cmd.add("python");
                cmd.add(PYTHON_FILENAME);

                //Run macro on target
                ProcessBuilder pb = new ProcessBuilder(cmd);
                pb.redirectErrorStream(true);
                Process process = pb.start();
                process.waitFor();
            } catch (Exception e){
                LogUtils.e("ERROR on PythonExecutor ",e);
            }
        }
    }
}