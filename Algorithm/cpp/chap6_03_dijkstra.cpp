#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <queue>
#include <limits>
#include <algorithm>

#include "Label.h"
#include "graph_and_edge.h"
using namespace std;

template <typename T>
vector<unsigned> dijkstra_shortest_path(const Graph<T>& G, unsigned src, unsigned dst) {
	// 최소 힙
	priority_queue<Label<T>, vector<Label<T>>, greater<Label<T>>> heap;

	// 모든 정점에서 거리 값을 최대로 설정
	vector<T> distance(G.vertices(), numeric_limits<T>::max());

	set<unsigned> visited;                     // 방문한 정점
	vector<unsigned> parent(G.vertices());     // 이동 경로 기억을 위한 벡터

	heap.emplace(Label<T>{src, 0});
	parent[src] = src;

	while (!heap.empty()) {
		Label<T> current_vertex = heap.top();
		heap.pop();

		// 목적지 정점에 도착했다면 종료
		if (current_vertex.ID == dst) {
			cout << current_vertex.ID << "번 정점(목적 정점)에 도착!" << endl;
			break;
		}

		// 현재 정점을 이전에 방문하지 않았다면
		if (visited.find(current_vertex.ID) == visited.end()) {
			cout << current_vertex.ID << "번 정점에 안착!" << endl;

			// 현재 정점과 연결된 모든 엣지에 대해
			for (Edge<T>& e : G.edges(current_vertex.ID)) {
				unsigned neighbor = e.dst;
				unsigned new_distance = current_vertex.distance + e.weight;

				// 인접한 정점의 거리 값이 새로운 경로에 의한 거리값보다 크면
				// 힙에 추가하고 거리 값을 업데이트 한다
				if (new_distance < distance[neighbor]) {
					heap.emplace(Label<T>{neighbor, new_distance});

					parent[neighbor] = current_vertex.ID;
					distance[neighbor] = new_distance;
				}
			}

			visited.insert(current_vertex.ID);
		}
	}

	// 백트래킹 방식으로 시작 정점부터 목적 정점까지의 경로 구성
	vector<unsigned> shortest_path;
	unsigned current_vertex = dst;

	while (current_vertex != src) {
		shortest_path.push_back(current_vertex);
		current_vertex = parent[current_vertex];
	}

	shortest_path.push_back(src);
	reverse(shortest_path.begin(), shortest_path.end());

	return shortest_path;
}

void dijkstra_test() {
	using T = unsigned;

	Graph<T> G = create_reference_graph2<T>();

	cout << "[입력 그래프]" << endl;
	cout << G << endl;

	vector<unsigned> shortest_path = dijkstra_shortest_path<T>(G, 1, 6);

	cout << "\n[1번과 6번 정점 사이의 최단 경로]" << endl;
	for (unsigned v : shortest_path)
		cout << v << " ";
	cout << endl;
}