package com.neos.trackandroll.cervo.communication.armband;

import com.neos.trackandroll.cervo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;


public final class PythonExecutor extends Thread {

    /**
     * The python file to execute on the beaglebone
     */
    private static final String PYTHON_FILENAME = "serialReceiveHR.py";

    /**
     * Process called at the start of the python executor
     */
    @Override
    public void run() {
        List<String> cmd = new ArrayList<>();
        try {
            //Build command by adding arguments
            cmd.add("python");
            cmd.add(PYTHON_FILENAME);

            //Run macro on target
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor();
        } catch (Exception e) {
            LogUtils.e("ERROR on PythonExecutor ", e);
        }
    }
}