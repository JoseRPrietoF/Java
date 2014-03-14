import java.util.ArrayList;


public class Grafo {
	
	ArrayList<Nodo> nodos = new ArrayList<Nodo>();
	
	public void addNodo(Nodo n){
		nodos.add(n);
	}
	
	public void nextNode(ArrayList<Nodo> loc, int target) {
		int peso = calcPeso(loc);
		
	}
	
	public int[] find(int target){
		ArrayList<Nodo> loc = new ArrayList<Nodo>();
		nextNode(loc, target);
		return null;
	}
	
	public int calcPeso(ArrayList<Nodo>  arr){
		int peso = 0;
		for (int i=0; i< arr.length; i++){
			peso += arr[i];
		}
		return peso;
	}
	
}
