import utils.HandshakeCompleter;
import utils.HandshakeStarter;
import utils.MsgReceiver;
import utils.MsgSender;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;


public class udpClient
{
	public static void main(String args[]) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		DatagramSocket ds = new DatagramSocket();
		InetAddress ip = InetAddress.getLocalHost();

		byte[] receive = new byte[65535];
		DatagramPacket DpReceive = null;
		boolean responseOK=false;

		Boolean sharedObject=false;

		//Handshake başlat.
		HandshakeStarter hs = new HandshakeStarter(sharedObject,ds);
		Thread handshakeStarterThread = new Thread(hs);
		handshakeStarterThread.start();

		//Completer'ı başlat
		HandshakeCompleter hc = new HandshakeCompleter(sharedObject,ds,hs);
		Thread handshakeComleterThread = new Thread(hc);
		handshakeComleterThread.start();

		// Start sender & receiver
		SocketAddress serverSockerAddress = new InetSocketAddress(InetAddress.getLocalHost(),1234);
		Thread senderThread = new Thread(new MsgSender(ds,"client",serverSockerAddress));
		senderThread.start();

		Thread receiverThread = new Thread(new MsgReceiver(ds,"client"));
		receiverThread.start();

	}
}
