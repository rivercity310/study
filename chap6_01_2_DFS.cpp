#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <stack>

#include "graph_and_edge.h"

using namespace std;


// 깊이 우선 탐색을 구현한 함수
template <typename T>
vector<unsigned> depth_first_search(const Graph<T>& G, unsigned start) {
	stack<unsigned> stack;
	set<unsigned> visited;
	vector<unsigned> visit_order;

	stack.push(start);

	while (!stack.empty()) {
		unsigned current_vertex = stack.top();
		stack.pop();

		// 현재 정점을 이전에 방문하지 않았다면
		if (visited.find(current_vertex) == visited.end()) {
			visited.insert(current_vertex);
			visit_order.push_back(current_vertex);

			for (Edge<T>& e : G.edges(current_vertex))
				// 인접한 정점 중에서 방문하지 않은 정점이 있다면 스택에 추가
				if (visited.find(e.dst) == visited.end())
					stack.push(e.dst);
		}
	}

	return visit_order;
}

void dfs_test() {
	using T = unsigned;

	// 그래프 객체 생성
	Graph<T> G = create_reference_graph<T>();

	cout << "[입력 그래프]" << endl;
	cout << G << endl;

	// 1번 정점부터 DFS 실행 & 방문 순서 출력
	cout << "\n\n[DFS 방문 순서]" << endl;
	vector<unsigned> dfs_visit_order = depth_first_search(G, 1);
	for (unsigned v : dfs_visit_order)
		cout << v << endl;
}