package utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class HandshakeCompleter implements Runnable{
    Boolean sharedObj;
    DatagramSocket ds;
    HandshakeStarter hs;

    public HandshakeCompleter(Boolean sharedObj, DatagramSocket ds,HandshakeStarter hs) {
        this.sharedObj = sharedObj;
        this.ds = ds;
        this.hs = hs;
    }

    @Override
    public void run() {
        byte[] receive = new byte[65535];
        try{
            DatagramPacket DpReceive = new DatagramPacket(receive, receive.length);
            System.out.println("Client waits OK_BACK");
            ds.receive(DpReceive);
            if(StringData.data(receive).toString().equals("OK_BACK")){
                    hs.sharedObj=true;
                System.out.println("Client takes OK_BACK");
            }
        }catch (Exception ignored){}
    }
}
