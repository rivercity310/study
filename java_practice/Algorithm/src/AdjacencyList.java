import java.util.ArrayList;

// ArrayList를 사용하여 양방향 인접리스트 구현하기

class ListGraph {
	private ArrayList<ArrayList<Integer>> listGraph;
	
	// 그래프 초기화
	public ListGraph(int initSize) {
		this.listGraph = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < initSize + 1; i++) 
			listGraph.add(new ArrayList<Integer>());
	}
	
	// 그래프 return
	public ArrayList<ArrayList<Integer>> getGraph() {
		return this.listGraph;
	}
	
	// 그래프의 특정 노드 return
	public ArrayList<Integer> getNode(int i) {
		return this.listGraph.get(i);
	}
	
	// 그래프 양방향 추가
	public void put(int x, int y) {
		listGraph.get(x).add(y);
		listGraph.get(y).add(x);
	}
	
	// 그래프 단방향 추가
	public void putSingle(int x, int y) {
		listGraph.get(x).add(y);
	}
	
	// 그래프 출력 (인접리스트)
	public void printGraphToAdjList() {
		for (int i = 1; i < listGraph.size(); i++) {
			System.out.printf("정점 %d의 인접리스트", i);
			
			for (int j = 0; j < listGraph.get(i).size(); j++)
				System.out.printf(" -> %d", listGraph.get(i).get(j));
			
			System.out.println();
		}
	}
}

public class AdjacencyList {
	public static void main(String[] args) {
		int initSize = 6;
		ListGraph adjList = new ListGraph(initSize);
		
		adjList.put(1, 2);
		adjList.put(1, 3);
		adjList.put(2, 3);
		adjList.put(2, 4);
		adjList.put(3, 4);
		adjList.put(3, 5);
		adjList.put(4, 5);
		adjList.put(4, 6);

		adjList.printGraphToAdjList();
	}
}





