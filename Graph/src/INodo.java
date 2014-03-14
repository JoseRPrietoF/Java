import java.util.ArrayList;


public interface INodo {
	
	ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	int[] historial = new int[30];
	ArrayList<Vertice> posibilidades = new ArrayList<Vertice>();
	
	public void nextNode(int[] loc, int target);
	
	public void addVertice(int destino, int peso);
	
	
}
