
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
 
public class MultiServer implements Runnable {

   private static ConfigLoader loader = new ConfigLoader();
	
   public static void main(String args[]) throws Exception { 
      ServerSocket ssocket = new ServerSocket(4004);
      System.out.println("Server start");
      
      loader.load();
      
      while (true) {
         Socket sock = ssocket.accept();
         System.out.println("Client connected");         
         new Thread(new MultiServer(sock, new Timer(), 1, 5000)).start();
      }
   }

   Socket csocket;
   Timer timer;
   MultiServer(Socket csocket, Timer timer, int timer_delay, int timer_period){
      this.csocket = csocket;
      this.timer = timer;     
	      this.timer.schedule(new TimerTask() {
	  		 public void run() {
	  		    	 Random rand = new Random();
	  		    	 try{
	  		         if(csocket.isConnected()){
	  		        	 String str = loader.getRandomString();
	  		        	 DataOutputStream out = new DataOutputStream(csocket.getOutputStream());
	  		        	 if(out != null){
	  		        	 out.writeUTF(str);
	  		        	 out.flush();
	  		        	 System.out.println("Send words to client. Message:"+str);
	  		        	 }
	  		         }
	  		    	 }catch(IOException e){
	  		    		e.printStackTrace();
	  		    	 }
	  		}
	  		}, timer_delay, timer_period);

   }
   
   public void run() {}	
}