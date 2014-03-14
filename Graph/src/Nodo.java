
public class Nodo implements INodo {

	@Override
	public void nextNode(int[] loc, int target) {
		
	}

	@Override
	public void addVertice(int destino, int peso) {
		vertices.add(new Vertice(destino, peso));
	}


}
