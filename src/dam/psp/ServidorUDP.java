package dam.psp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class ServidorUDP {
	DatagramSocket socket;
	HashMap<String, ContenedorMens> dic=new HashMap<String, ContenedorMens>();
	DatoUDP elDato;
	ContenedorMens contenedorAux;
	
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
				elDato=DatoUDP.fromByteArray(datorecibido.getData());
				System.out.println("Recibiendo paquete de "+IPCli);
				//si esta dentro del diccionario una part e
				if(dic.containsKey(IPCli)){
					contenedorAux=(ContenedorMens)dic.get(IPCli);
					contenedorAux.nparte++;//aumentar el numero de parted de ese objeto
					int posInsert=(elDato.getId()-1)*Constantes.TAM_PARTE_MAX;//pos del texto donde se añadirá
					contenedorAux.appendMens(elDato.getMensaje(), posInsert);//insertarlo porque puede llegar desordenado y asi se evita que sea diferencte el mensaje
					dic.put(IPCli, contenedorAux);//actualizacion del registro
					//si la informacion esta completa
					if(contenedorAux.nparte==contenedorAux.nPartesTotal){
						System.out.println("Mensaje de: "+IPCli+"|| "+contenedorAux.mensaje);
						//reseteo por si llegan otros mensajes
						contenedorAux.nparte=0;
						contenedorAux.mensaje.setLength(0);
					}
				}
				//si no esta se crea una nueva entrada
				else{
					dic.put(IPCli, new ContenedorMens(elDato.getNTotalPartes(), elDato.getMensaje()));
				}
			}while(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ServidorUDP();
	}

}
