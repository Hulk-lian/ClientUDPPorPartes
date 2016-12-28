package dam.psp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DatoUDP implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer nTotalPartes ;
	private String mensaje;
	
	public Integer getId(){
		return id;
	}
	public String getMensaje(){
		return mensaje;
	}
	public Integer getNTotalPartes(){
		return nTotalPartes;
	}
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
	
	public static DatoUDP fromByteArray(byte[] objSer){
		try{
			ByteArrayInputStream byteArray = new ByteArrayInputStream(objSer);
			ObjectInputStream objImpInputStream= new ObjectInputStream(byteArray);
			DatoUDP temp=(DatoUDP)objImpInputStream.readObject();
			objImpInputStream.close();
			return temp;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

}
