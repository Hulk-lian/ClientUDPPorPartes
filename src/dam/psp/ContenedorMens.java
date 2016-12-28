package dam.psp;

public class ContenedorMens {
	int nparte=1;
	int nPartesTotal;
	StringBuilder mensaje;
	
	public ContenedorMens(Integer ntotal,String fragMens) {
		this.nPartesTotal=ntotal;
		this.mensaje=new StringBuilder();
		this.mensaje.append(fragMens);
	}
	public void appendMens(String fragmento,int pos){
		this.mensaje.insert(pos, fragmento);
	}
}
