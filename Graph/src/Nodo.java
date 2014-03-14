
public class Nodo implements INodo {

	int id;
	
	public Nodo(int id) {
		this.id = id;
	}

	@Override
	public void addVertice(int destino, int peso) {
		vertices.add(new Vertice(destino, peso));
	}


}
