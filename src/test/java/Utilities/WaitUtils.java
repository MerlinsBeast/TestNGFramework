package Utilities;

public class WaitUtils {

    public static void waitUntil(int time){
       try{
           Thread.sleep(time);
       }
       catch(Exception e){
           e.printStackTrace();
       }

    }
}
