import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class UartReceive {
    

    class LaunchScript extends Thread {
        public Process processLaunch = null;
        String processLaunchError, launchOutput;
        List<String> cmd = new ArrayList<String>();
    
        public void run(){
    
            try{
                //Build command by adding arguments
                //cmd.add("python");
                //cmd.add("/var/lib/cloud9/examples/serialReceiveHR.py");
                cmd.add("/var/lib/cloud9/examples/launchUartReceivePyScript.sh");
                System.out.println(cmd);
        
                //Run macro on target
                ProcessBuilder pb = new ProcessBuilder(cmd);
                pb.redirectErrorStream(true);
                UartReceive.CommandReader commandReader = new UartReceive.CommandReader(pb.start(), processLaunchError, launchOutput);
                commandReader.start();
                /*Process process = pb.start();
                process.waitFor();
                BufferedReader brLaunch_error = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
                BufferedReader brLaunch_input = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
                while ((launchOutput = brLaunch_input.readLine()) != null){
                    System.out.println("Line read");
                    handle(launchOutput);
                }
                while ((processLaunchError = brLaunch_error.readLine()) != null){
                }
            
                if (process.waitFor()==0){
                    System.exit(0);
                }*/
            
                System.err.println(processLaunchError.toString());
                System.exit(1);
                while(true);
                
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public class CommandReader extends Thread{
        private Process process;
        private String processLaunchError, launchOutput;
        public CommandReader(Process process, String processLaunchError, String launchOutput){
            this.process = process;
            this.processLaunchError=processLaunchError;
            this.launchOutput=launchOutput;
        }
        
        public void run(){
            try{
                System.out.println("In thread CommandReader");
                BufferedReader brLaunch_error = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));
                BufferedReader brLaunch_input = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                System.out.println("In thread CommandReader");
                
                
                while ((launchOutput = brLaunch_input.readLine()) != null){
                    System.out.println("Line read");
                    handle(launchOutput);
                }
                while ((processLaunchError = brLaunch_error.readLine()) != null){
                }
                
                if (process.waitFor()==0){
                    System.exit(0);
                }
            } catch (Exception e){
                    e.printStackTrace();
            }
            
            System.err.println(processLaunchError.toString());
            System.exit(1);
        }
    }
    
    public void handle(String launchOutput){
        System.out.println("In handle");
        System.out.println(launchOutput);
    }
    
    public static void main(String args[]) {
        LaunchScript launchScript = new UartReceive().new LaunchScript();
        
        try{
            launchScript.start();
            
        }catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }
}