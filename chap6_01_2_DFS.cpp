#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <stack>
#include <queue>

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





bool visited[9];
vector<int> graph[9];

void dfs(int x) {
	visited[x] = true;
	cout << x << " ";

	for (int i = 0; i < graph[x].size(); i++) {
		int y = graph[x][i];
		if (!visited[y]) 
			dfs(y);
	}
}

bool visited2[9];

void bfs(int x) {
	queue<int> q;
	q.push(x);
	visited2[x] = true;

	while (!q.empty()) {
		int curr_vertex = q.front();
		q.pop();

		cout << curr_vertex << " ";

		for (int i = 0; i < graph[curr_vertex].size(); i++) {
			int y = graph[curr_vertex][i];
			if (!visited2[y]) {
				q.push(y);
				visited2[y] = true;
			}
		}
	}

}

void m_test() {
	graph[0] = {};
	graph[1] = { 2, 3, 8 };
	graph[2] = { 1, 7 };
	graph[3] = { 1, 4, 5 };
	graph[4] = { 3, 5 };
	graph[5] = { 3, 4 };
	graph[6] = { 7 };
	graph[7] = { 2, 6, 8 };
	graph[8] = { 1, 7 };

	dfs(1);
	cout << endl;
	bfs(1);
}