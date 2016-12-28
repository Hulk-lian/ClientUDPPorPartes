package dam.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
	private String mensEnviar;
	DatagramSocket socket;
	DatoUDP elDatoAEnviar;
	DatagramPacket paquete;
	
	public Cliente() {
		try {
			this.pedirMensaje();
			socket= new DatagramSocket(Constantes.PUERTO_CLI,
					InetAddress.getByName(Constantes.IP_CLIENTE));
			System.out.println("La longitud del menaje: "+mensEnviar.length());
			int nEnvios=this.mensEnviar.length()/Constantes.TAM_PARTE_MAX;
			int restantes=this.mensEnviar.length()%Constantes.TAM_PARTE_MAX;
			
			//en caso de que queden algunos caracteres se envia un paquete más con los restantes
			if(restantes>0){
				nEnvios++;
			}
			System.out.println("Mensaje dividido en "+nEnvios);
			int cont=0;
			while(cont<nEnvios){
				String fragmentoMensaje;
				if(cont== nEnvios-1 && restantes > 0){ 
						fragmentoMensaje=mensEnviar.substring(cont * Constantes.TAM_PARTE_MAX,Constantes.TAM_PARTE_MAX *cont+restantes);
				}else{
						fragmentoMensaje=mensEnviar.substring(cont * Constantes.TAM_PARTE_MAX,Constantes.TAM_PARTE_MAX *(cont+restantes+1));
				}
				elDatoAEnviar=new DatoUDP(cont+1, nEnvios, fragmentoMensaje);
				byte[] datoSerializado= elDatoAEnviar.toByteArray();
				paquete= new DatagramPacket(datoSerializado, datoSerializado.length,InetAddress.getByName(Constantes.IP_SERV),Constantes.PUERTO_SERV);
				
				System.out.println("Dato enviado "+cont+ " parte "+fragmentoMensaje);
				socket.send(paquete);
				Thread.sleep(200);
				cont++;
			}
			System.out.println("Envio finalizado correctamente");
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		finally{
			socket.close();
		}
	}
	
	private void pedirMensaje() throws IOException{
		System.out.println("Introduzca el menaje a enviar: ");
		BufferedReader lector=new BufferedReader(new InputStreamReader(System.in));
		mensEnviar=lector.readLine();
	}
	
	
	public static void main(String[] args) {
		new Cliente();
	}

}
