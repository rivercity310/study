#include <iostream>
#include <vector>
#include <queue>
#include <map>
#include <algorithm>

#include "graph_and_edge.h"
#include "disjoint_set.h"


// 트리도 그래프로 표현할 수 있으므로 최소 신장 트리도 Graph 객체로 반환
// 단, 사이클이 존재하면 안됨
template <typename T>
Graph<T> minimum_spanning_tree(const Graph<T>& G) {
	// 엣지 가중치를 이용한 최소 힙 구성
	priority_queue<Edge<T>, vector<Edge<T>>, greater<Edge<T>>> edge_min_heap;

	// 모든 엣지를 최소 힙에 추가
	for (const Edge<T>& e : G.edges())
		edge_min_heap.push(e);

	// 정점 개수에 해당하는 크기의 디스조인트-셋 자료구조 생성 및 초기화
	unsigned N = G.vertices();
	SimpleDisjointSet dset(N);
	for (unsigned i = 0; i < N; i++)
		dset.make_set(i);

	// 디스조인트-셋 자료구조를 이용하여 최소 신장 트리 구하기
	Graph<T> MST(N);
	
	while (!edge_min_heap.empty()) {
		// 최소 힙에서 최소 가중치를 갖는 에지 추출
		Edge<T> e = edge_min_heap.top();
		edge_min_heap.pop();

		// 선택한 엣지가 사이클을 생성하지 않으면 해당 엣지를 MST에 추가
		if (dset.find(e.src) != dset.find(e.dst)) {
			MST.add_edge(Edge<T>{e.src, e.dst, e.weight});
			dset.union_sets(e.src, e.dst);
		}
	}

	return MST;
}

// 테스트 함수
void mst_test() {
	using T = unsigned;

	// 그래프 객체 생성
	Graph<T> G(9);

	map<unsigned, vector<pair<unsigned, T>>> edge_map;
	edge_map[1] = { {2, 2}, {5, 3} };
	edge_map[2] = { {1, 2}, {5, 5}, {4, 1} };
	edge_map[3] = { {4, 2}, {7, 3} };
	edge_map[4] = { {2, 1}, {3, 2}, {5, 2}, {6, 4}, {8, 5} };
	edge_map[5] = { {1, 3}, {2, 5}, {4, 2}, {8, 3} };
	edge_map[6] = { {4, 4}, {7, 4}, {8, 1} };
	edge_map[7] = { {3, 3}, {6, 4} };
	edge_map[8] = { {4, 5}, {5, 3}, {6, 1} };

	for (auto& i : edge_map)
		for (auto& j : i.second)
			G.add_edge(Edge<T>{i.first, j.first, j.second});

	cout << "[입력 그래프]" << endl;
	cout << G << endl;

	Graph<T> MST = minimum_spanning_tree(G);
	cout << "\n\n[최소 신장 트리]" << endl;
	cout << MST << endl;
}