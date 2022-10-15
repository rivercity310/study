#include <iostream>
#include <vector>
#include <queue>

using namespace std;

constexpr int INFF = 1e9;
constexpr int MAX_NODE = 100001;

// 최대 노드 개수를 10만개라고 가정
vector<pair<int, int>> grp_ex[MAX_NODE];
int dist_ex[MAX_NODE];

void init_grp(int N) {
	grp_ex[1] = { {2, 2}, {3, 5}, {4, 1} };
	grp_ex[2] = { {3, 3}, {4, 2} };
	grp_ex[3] = { {2, 3}, {6, 5} };
	grp_ex[4] = { {3, 3}, {5, 1} };
	grp_ex[5] = { {3, 1}, {6, 2} };

	fill(dist_ex, dist_ex + (N + 1), INFF);
}

void dijkstra_with_pq(int start) {
	priority_queue<pair<int, int>> pq;
	pq.push({ 0, start });		// { 거리, 노드 번호 } 순으로 큐에 추가
	dist_ex[start] = 0;

	while (!pq.empty()) {
		pair<int, int> p = pq.top();
		pq.pop();

		// 큐의 기본 값이 최대힙이므로 입출력할때 음수를 붙여 최소힙처럼 동작하게 만든다.
		int now_dist = -p.first;
		int now_vertex = p.second;

		// 현재 거리 값보다 큐에서 꺼낸 거리 값이 더 크면 이미 처리된 노드로 간주한다.
		if (dist_ex[now_vertex] < now_dist)
			continue;

		for (pair<int, int> k : grp_ex[now_vertex]) {
			int end_vertex = k.first;
			int weight = k.second;

			int cost = dist_ex[now_vertex] + weight;
			if (dist_ex[end_vertex] > cost) {
				dist_ex[end_vertex] = cost;
				pq.push({ -cost, end_vertex });
			}
		}
	}
}

void dijkstra_ex() {
	int N = 6;
	int start = 1;

	init_grp(N);
	dijkstra_with_pq(start);

	for (int i = 1; i <= N; i++)
		cout << dist_ex[i] << " ";
}