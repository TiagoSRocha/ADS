import java.util.ArrayList;

class Brain implements Runnable{


     private static Brain obj;
    tempSensor sensorTemp;

     private Brain() {}

     // Only one thread can execute this at a time
     public static synchronized Brain getBrain()
     {
         if (obj==null) {
             obj = new Brain();
            new Thread(new Brain()).start();
         }
         return obj;
     }
     public void run() {
         //ir a pool das notificacoes e lidar.


     }

 }
