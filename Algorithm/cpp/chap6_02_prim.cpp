#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <queue>
#include <limits>
#include "graph_and_edge.h"
#include "label.h"
using namespace std;

// 프림 알고리즘 구현 함수
template <typename T>
vector<unsigned> prim_MST(const Graph<T>& G, unsigned src) {
	// 최소 힙
	priority_queue<Label<T>, vector<Label<T>>, greater<Label<T>>> heap;

	// 모든 정점에서 거리 값을 최대로 설정
	vector<T> distance(G.vertices(), numeric_limits<T>::max());

	set<unsigned> visited;     // 방문한 정점
	vector<unsigned> MST;      // 최소 신장 트리

	heap.emplace(Label<T>{src, 0});

	while (!heap.empty()) {
		Label<T> current_vertex = heap.top();
		heap.pop();

		// 현재 정점을 이전에 방문하지 않았다면
		if (visited.find(current_vertex.ID) == visited.end()) {
			MST.push_back(current_vertex.ID);

			for (Edge<T>& e : G.edges(current_vertex.ID)) {
				unsigned neighbor = e.dst;
				T new_distance = e.weight;

				// 인접한 정점의 거리 값이 새로운 경로에 의한 거리값보다 크면
				// 힙에 추가하고, 거리 값을 업데이트
				if (new_distance < distance[neighbor]) {
					heap.emplace(Label<T>{neighbor, new_distance});
					distance[neighbor] = new_distance;
				}
			}

			visited.insert(current_vertex.ID);
		}
	}

	return MST;
}

void prim_test() {
	using T = unsigned;

	Graph<T> G = create_reference_graph2<T>();
	cout << "[입력 그래프]" << endl;
	cout << G << endl;

	vector<unsigned> MST = prim_MST<T>(G, 1);

	cout << "\n\n[최소 신장 트리]" << endl;
	for (unsigned v : MST)
		cout << v << endl;
	cout << endl;
}