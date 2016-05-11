import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

//	MENORCA

/*
8 20 // 8 nodos, 20 aristas totales
1 2 12 // Origen    Destino    Pes o(kms) 1 ciutadella
2 1 12 Ferreries hacia ciudadela, 12kms
2 3 4
2 4 5
3 2 4 // mercadal
3 4 3
3 5 7
4 3 3 // migjorn
4 2 5
4 5 8
5 3 7  // alaior
5 4 8
5 6 11
6 5 11  // Mahon
6 8 3
6 7 3
8 6 3  // Sant lluis
8 7 3
7 8 3  // Es Castell
7 6 3
 */
public class Grafo {
	final int MAX = 10005; // maxi num vertices
	final int INF = 1000000000; // distancia infinita inicial

	Scanner sc = new Scanner(System.in);
	List<List<Node>> nodos = new ArrayList<List<Node>>(); // lista de adyacencias
	int distanciaTotal[] = new int[MAX]; // distancia[ u ] distancia de vertice inicial a vert ID = u
	boolean nodosVisitados[] = new boolean[MAX];
	PriorityQueue<Node> colaP = new PriorityQueue<Node>(); 
	int nVertices; // num de vertices
	int nodosPasados[] = new int[MAX]; // impresion de caminos
	
	private ArrayList<Integer> resultado = new ArrayList<Integer>();
	public Grafo(){
		nVertices = 8; // 8 vertices, es como decir 8 pueblos o 8 nodos
		
		for (int i = 0; i <= nVertices; ++i)
			nodos.add(new ArrayList<Node>()); 
		// añadimos todos los posibles caminos adyacentes.
		nodos.get(1).add(new Node(2, 12));
		nodos.get(2).add(new Node(1, 12));
		nodos.get(2).add(new Node(3, 4));
		nodos.get(2).add(new Node(4, 5));
		nodos.get(3).add(new Node(2, 4));
		nodos.get(3).add(new Node(4, 3));
		nodos.get(3).add(new Node(5, 7));
		nodos.get(4).add(new Node(3, 3));
		nodos.get(4).add(new Node(2, 5));
		nodos.get(4).add(new Node(5, 8));
		nodos.get(5).add(new Node(3, 7));
		nodos.get(5).add(new Node(4, 8));
		nodos.get(5).add(new Node(6, 11));
		nodos.get(6).add(new Node(5, 11));
		nodos.get(6).add(new Node(8, 7));
		nodos.get(6).add(new Node(7, 3));
		nodos.get(8).add(new Node(6, 3));
		nodos.get(8).add(new Node(7, 3));
		nodos.get(7).add(new Node(8, 3));
		nodos.get(7).add(new Node(6, 3));
		/*
			1-> Ciutadella, 2-> Ferreries, -> 3 Mercadal, -> 4 Migjorn, -> 5 Alaior, -> 6 Mahon, -> 7 Es Castell ,-> 8 Sant lluis.
		 */
	}
	

	private void init() {
		for (int i = 0; i <= nVertices; ++i) {
			distanciaTotal[i] = INF; 
			nodosVisitados[i] = false; 
			nodosPasados[i] = -1; 
		}
	}

	// Paso de relajacion, marcamos como final si el valor es bueno
	private void relajacion(int actual, int adyacente, int peso) {
		// Si la distancia del origen al vertice actual + peso de su arista es menor a la distancia del origen al vertice adyacente
		if (distanciaTotal[actual] + peso < distanciaTotal[adyacente]) {
			distanciaTotal[adyacente] = distanciaTotal[actual] + peso; 
			nodosPasados[adyacente] = actual; 
			colaP.add(new Node(adyacente, distanciaTotal[adyacente])); 
		}
	}
	
	// impresion en resultado
	private void buscar(int destino) {
		if (nodosPasados[destino] != -1) 
			buscar(nodosPasados[destino]); // recursivamente sigo explorando
		//System.out.printf("%d ", destino); // terminada la recursion imprimo los vertices recorridos
		resultado.add(destino);
	}


	public ArrayList<Integer> getResultado() {
		return resultado;
	}


	// Algoritmo que, dependiendo del punto inicial (origen), rellena arrays con caminos mas cortos
	void dijkstra(int inicial) {
		init(); // inicializamos
		colaP.add(new Node(inicial, 0)); // vertice inicial en la Cola de Prioridad
		distanciaTotal[inicial] = 0;
		int actual, adyacente, peso;
		while (!colaP.isEmpty()) { // Mientras la cola no este vacia
			actual = colaP.element().id; // Obtengo de la cola el nodo con menor peso, en un comienzo será el inicial
			colaP.remove(); // Sacamos el elemento de la cola
			if (nodosVisitados[actual])
				continue; // Si el vértice actual ya fue visitado entonces sigo  sacando elementos de la cola
			
			nodosVisitados[actual] = true; // Marco como visitado el vértice actual

			for (int i = 0; i < nodos.get(actual).size(); i++) { // reviso adyacentes
				adyacente = nodos.get(actual).get(i).id; 
				peso = nodos.get(actual).get(i).peso;
				if (!nodosVisitados[adyacente]) { 
					relajacion(actual, adyacente, peso); // realizamos el paso de relajacion en caso de no ser visitado
				}
			}
		}

		System.out.printf("\n Caminos mas cortos desde nodo %d \n", inicial);
		for (int i = 1; i <= nVertices; ++i) {
			System.out.printf("Nodo %d , distancia mas corta = %d kms\n", i, distanciaTotal[i]);
		}
		System.out.printf("Dame un destino: ");
		int destino;
		destino = sc.nextInt();
		buscar(destino);
		System.out.printf("\n");
	}

	public static void main(String[] args) {
		int inicial;
		Grafo g = new Grafo();

		
		System.out.print("Ingrese el vertice inicial: ");
		inicial = g.sc.nextInt();
		g.dijkstra(inicial);
		System.out.println(g.getResultado());
	}

}
