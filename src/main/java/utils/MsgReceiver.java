package utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class MsgReceiver implements Runnable{
    DatagramSocket ds;
    String user;

    public MsgReceiver(DatagramSocket ds, String user) {
        this.ds = ds;
        this.user = user;
    }

    @Override
    public void run() {
        byte[] receive = new byte[65535];

        DatagramPacket DpReceive = null;
        while(true){
            try{
                //Get message
                DpReceive = new DatagramPacket(receive, receive.length);
                ds.receive(DpReceive);
                System.out.println(StringData.data(receive));
                if (StringData.data(receive).toString().contains(":-exit"))
                {
                    System.out.println("sent exit.....EXITING");
                    System.exit(0);
                }
            }catch (Exception ignored){}
        }

    }
}
