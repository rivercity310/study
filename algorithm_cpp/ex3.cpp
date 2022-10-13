#include <iostream>
#include <vector>
#include <queue>

using namespace std;

// [ 실전문제 3. 전보 ]

constexpr int INF = 1e9;
constexpr int MAX = 30001;

// 방향 그래프 => 인접 리스트 방식으로 구현, 만약 가중치가 있는 그래프라면 pair
// 무방향 그래프 => 인접 행렬 방식으로 구현
vector<pair<int, int>> city_ex3[MAX];
vector<int> city_dist(MAX, INF);

void dijk_city(int start) {
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
	pq.push({ 0, start });
	city_dist[start] = 0;

	while (!pq.empty()) {
		pair<int, int> pr = pq.top();
		pq.pop();

		int dist = pr.first;
		int now = pr.second;

		if (city_dist[now] < dist)
			continue;

		for (pair<int, int>& p : city_ex3[now]) {
			int end_vertex = p.first;
			int weight = p.second;
			int cost = city_dist[now] + weight;

			if (city_dist[end_vertex] > cost) {
				city_dist[end_vertex] = cost;
				pq.push({ cost, end_vertex });
			}
		}
	}
}

void dijk_ex3() {
	int N, M, C;
	cin >> N >> M >> C;

	while (M--) {
		int X, Y, Z;
		cin >> X >> Y >> Z;

		// 특정 도시 X에서 다른 특정 도시 Y로 이어지는 통로의 전달시간이 Z
		city_ex3[X].push_back(make_pair(Y, Z));
	}

	dijk_city(C);

	cout << "[다익스트라 결과]" << "\n";
	for (int i = 1; i <= N; i++)
		cout << city_dist[i] << "\t";
	cout << endl;

	int received_cnt = 0;
	int maximum_time = 0;

	for (int i = 1; i <= N; i++) {
		if (city_dist[i] != INF) {
			maximum_time = max(maximum_time, city_dist[i]);
			received_cnt++;
		}

	}

	cout << received_cnt - 1 << " " << maximum_time;
}