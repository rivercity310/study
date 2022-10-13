#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <queue>
#include "graph_and_edge.h"

using namespace std;


// 너비 우선 탐색 알고리즘 구현
template <typename T>
vector<unsigned> breadth_first_search(const Graph<T>& G, unsigned start) {
	queue<unsigned> queue;
	set<unsigned> visited;          // 방문한 정점
	vector<unsigned> visit_order;   // 방문 순서
	
	queue.push(start);

	while (!queue.empty()) {
		unsigned current_vertex = queue.front();
		queue.pop();

		// 현재 정점을 이전에 방문하지 않았다면
		if (visited.find(current_vertex) == visited.end()) {
			visited.insert(current_vertex);
			visit_order.push_back(current_vertex);

			for (Edge<T>& e : G.edges(current_vertex)) 
				// 인접한 정점 중에서 방문하지 않은 정점이 있다면 큐에 추가
				if (visited.find(e.dst) == visited.end())
					queue.push(e.dst);
		}
	}

	return visit_order;
}


// 테스트 함수
void bfs_test() {
	using T = unsigned;

	// 그래프 객체 생성
	Graph<T> G = create_reference_graph<T>();

	cout << "[입력 그래프]" << endl;
	cout << G << endl;

	// 1번 정점부터 BFS 실행 & 방문 순서 출력
	cout << "\n\n[BFS 방문 순서]" << endl;
	vector<unsigned> bfs_visit_order = breadth_first_search(G, 1);
	for (int i = 0; i < bfs_visit_order.size(); i++) {
		cout << bfs_visit_order[i];
		if (i != bfs_visit_order.size() - 1) cout << " -> ";
	}
}