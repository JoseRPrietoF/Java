import java.util.ArrayList;


public interface INodo {
	

	ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	ArrayList<Integer> historial = new ArrayList<Integer>();
	ArrayList<Vertice> posibilidades = new ArrayList<Vertice>();
	
	public void addVertice(int destino, int peso);
	
	
}
