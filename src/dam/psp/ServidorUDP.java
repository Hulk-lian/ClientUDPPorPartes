package dam.psp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class ServidorUDP {
	DatagramSocket socket;
	HashMap<String, ContenedorMens> dic=new HashMap<String, ContenedorMens>();
	
	public ServidorUDP() {
		try{
			socket=new DatagramSocket(Constantes.PUERTO_SERV,
					InetAddress.getByName(Constantes.IP_SERV));
			byte[] mensajeRecibido=new byte[Constantes.MAX_TAM_TOTAL];
			DatagramPacket datorecibido= new DatagramPacket(mensajeRecibido, Constantes.MAX_TAM_TOTAL);
			System.out.println("Escuchando en el puerto: "+socket.getLocalPort());
			
			do{
				socket.receive(datorecibido);
				String IPCli=datorecibido.getAddress().getHostAddress();
			}while(true);
		}
		catch (Exception e) {
			
		}
	}

	public static void main(String[] args) {
		new ServidorUDP();
	}

}
