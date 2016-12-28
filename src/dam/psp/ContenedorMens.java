package dam.psp;

public class ContenedorMens {
	int nparte=1;
	int nPartesTotal;
	String mensaje;
	
	public ContenedorMens(Integer ntotal,String fragMens) {
		this.nPartesTotal=ntotal;
		this.mensaje+=fragMens;
	}
}
