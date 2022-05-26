package utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;

public class MsgSender implements Runnable{
    DatagramSocket ds;
    String user;
    SocketAddress socketAddress;

    public MsgSender(DatagramSocket ds, String user, SocketAddress socketAddress) {
        this.ds = ds;
        this.user = user;
        this.socketAddress = socketAddress;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                String message = sc.nextLine();
                String inp = user+":-" + message;
                byte[] buf = inp.getBytes();
                DatagramPacket DpSend = new DatagramPacket(buf, buf.length, socketAddress);
                ds.send(DpSend);
                if (message.equals("exit")){
                    System.out.println("there");
                    break;
                }
            }
        }catch (Exception ignored){}
        System.out.println("...EXITING");
        System.exit(0);
    }
}
