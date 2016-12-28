package dam.psp;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DatoUDP implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer nTotalPartes ;
	private String mensaje;
	
	public DatoUDP(Integer id,Integer nTotalPar, String cadena) {
		this.id=id;
		this.nTotalPartes=nTotalPar;
		this.mensaje=cadena;
	}
	
	public byte[] toByteArray(){
		try{
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ObjectOutputStream os=new ObjectOutputStream(bytes);
			os.writeObject(this);
			os.close();
			return bytes.toByteArray();
		}
		catch(Exception e){
			return null;
		}
	}

}
