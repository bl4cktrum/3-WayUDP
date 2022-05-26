package utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HandshakeStarter implements Runnable{
    Boolean sharedObj;
    DatagramSocket ds;

    public HandshakeStarter(Boolean sharedObj, DatagramSocket ds) {
        this.sharedObj = sharedObj;
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            boolean flag = false;
            while (!sharedObj) {
                //Send handshake message to server
                byte[] buf = "OK".getBytes();
                DatagramPacket DpSend =
                        new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 1234);
                ds.send(DpSend);
                System.out.println("Client says OK!");
                Thread.sleep(1000);

            }
        }catch (Exception ignored){}
    }
}
