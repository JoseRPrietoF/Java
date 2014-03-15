
public class Node implements Comparable<Node> {
	int id, peso;
	Node( int d , int p ){							//constructor
		this.id = d;
		this.peso = p;
	}
	public int compareTo( Node other){				//es necesario definir un comparador para el correcto funcionamiento del PriorityQueue
		if( peso > other.peso ) return 1;
		if( peso == other.peso ) return 0;
		return -1;
	}
}
