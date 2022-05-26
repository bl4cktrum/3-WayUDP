// Implementation using DatagramSocket
import utils.MsgReceiver;
import utils.MsgSender;
import utils.StringData;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class udpServer
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		DatagramSocket ds = new DatagramSocket(1234);
		byte[] receive = new byte[65535];
		DatagramPacket DpReceive = null;
		SocketAddress clientSocketAddress= null;

		//Handshake to take client's socketAdress
		while (true)
		{
			DpReceive = new DatagramPacket(receive, receive.length);

			/*
			Başlangıçta burada bekleyecek
			yarım saniyede bir getResponse tarafından mesaj yollanacak
			 */
			System.out.println("Server waits handshake message");
			ds.receive(DpReceive);
			if(StringData.data(receive).toString().equals("OK")){
				System.out.println("Server takes OK!");
				clientSocketAddress = DpReceive.getSocketAddress();

				/*
				Send response message
				Gelen mesaj okay ise
				OK_BACK mesajı dönecek
				 */
				byte[] buf = "OK_BACK".getBytes();
				DatagramPacket DpSend =
						new DatagramPacket(buf, buf.length, clientSocketAddress);
				ds.send(DpSend);
				System.out.println("Server says OK_BACK");
				break;
			}
		}

		// Start sender & receiver
		Thread senderThread = new Thread(new MsgSender(ds,"server",clientSocketAddress));
		senderThread.start();

		Thread receiverThread = new Thread(new MsgReceiver(ds,"server"));
		receiverThread.start();

	}


}
