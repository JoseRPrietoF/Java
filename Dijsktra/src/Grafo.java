import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
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
	// similar a los defines de C++
	final int MAX = 10005; // maximo numero de vértices
	final int INF = 1 << 30; // definimos un valor grande que represente la
								// distancia infinita inicial, basta conque sea
								// superior al maximo valor del peso en alguna de las aristas

	Scanner sc = new Scanner(System.in); // para lectura de datos
	List<List<Node>> nodos = new ArrayList<List<Node>>(); // lista de adyacencias
	int distanciaTotal[] = new int[MAX]; // distancia[ u ] distancia de vértice inicial a vértice con ID = u
	boolean nodosVisitados[] = new boolean[MAX]; // para vértices visitados
	PriorityQueue<Node> Q = new PriorityQueue<Node>(); // cola de prioridad
	int nVertices; // numero de vertices
	int nodosPasados[] = new int[MAX]; // para la impresion de caminos
	
	private ArrayList<Integer> resultado = new ArrayList<Integer>(); // Camino final
	public Grafo(){
		nVertices = 8; // 8 vertices, es como decir 8 pueblos o 8 nodos
		//E = 40; // 40 posibles camino,s aristas. Cada nodo tiene sus aristas adyacentes, se suman todas.
		for (int i = 0; i <= nVertices; ++i)
			nodos.add(new ArrayList<Node>()); // inicializamos lista de adyacencia
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
	

	void init() {
		for (int i = 0; i <= nVertices; ++i) {
			distanciaTotal[i] = INF; // inicializamos todas las distancias con valor infinito
			nodosVisitados[i] = false; // inicializamos todos los vértices como no visitados
			nodosPasados[i] = -1; // inicializamos el previo del vertice i con -1
		}
	}

	// Paso de relajacion, marcamos como final si el valor es bueno
	void relajacion(int actual, int adyacente, int peso) {
		// Si la distancia del origen al vertice actual + peso de su arista es
		// menor a la distancia del origen al vertice adyacente
		if (distanciaTotal[actual] + peso < distanciaTotal[adyacente]) {
			distanciaTotal[adyacente] = distanciaTotal[actual] + peso; // relajamos el vertice actualizando la distancia
			nodosPasados[adyacente] = actual; // a su vez actualizamos el vertice previo
			Q.add(new Node(adyacente, distanciaTotal[adyacente])); // agregamos adyacente a la cola de prioridad
		}
	}
	
	// Impresion del camino mas corto desde el vertice inicial y final
	// ingresados
	void buscar(int destino) {
		if (nodosPasados[destino] != -1) // si aun poseo un vertice previo
			buscar(nodosPasados[destino]); // recursivamente sigo explorando
		//System.out.printf("%d ", destino); // terminada la recursion imprimo los vertices recorridos
		resultado.add(destino);
	}


	public ArrayList<Integer> getResultado() {
		return resultado;
	}


	// Algoritmo que, dependiendo del punto inicial, rellena un 
	void dijkstra(int inicial) {
		init(); // inicializamos nuestros arreglos
		Q.add(new Node(inicial, 0)); // Insertamos el vértice inicial en la Cola de Prioridad
		distanciaTotal[inicial] = 0; // Este paso es importante, inicializamos la  distancia del inicial como 0
		int actual, adyacente, peso;
		while (!Q.isEmpty()) { // Mientras cola no este vacia
			actual = Q.element().id; // Obtengo de la cola el nodo con menor
										// peso, en un comienzo será el inicial
			Q.remove(); // Sacamos el elemento de la cola
			if (nodosVisitados[actual])
				continue; // Si el vértice actual ya fue visitado entonces sigo
							// sacando elementos de la cola
			nodosVisitados[actual] = true; // Marco como visitado el vértice actual

			for (int i = 0; i < nodos.get(actual).size(); ++i) { // reviso sus adyacentes del vertice actual
				adyacente = nodos.get(actual).get(i).id; // id del vertice adyacente
				peso = nodos.get(actual).get(i).peso; // peso de la arista que
														// une actual con adyacente ( actual , adyacente )
				if (!nodosVisitados[adyacente]) { // si el vertice adyacente no fue visitado
					relajacion(actual, adyacente, peso); // realizamos el paso de relajacion
				}
			}
		}

		System.out.printf("Distancias mas cortas iniciando en vertice %d\n", inicial);
		for (int i = 1; i <= nVertices; ++i) {
			System.out.printf("Vertice %d , distancia mas corta = %d\n", i, distanciaTotal[i]);
		}

		System.out.println("\n**************Impresion de camino mas corto**************");
		System.out.printf("Ingrese vertice destino: ");
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
