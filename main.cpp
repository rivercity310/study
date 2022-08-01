#include <iostream>
#include <vector>
#include <iomanip>
#include <queue>
#include <algorithm>

using namespace std;

constexpr int INF = 1e9;

vector<pair<int, int>> gp_ex[30001];
vector<int> distance_ex;

void dijk(int start) {
	priority_queue<pair<int, int>> q;
	
	q.push(make_pair(0, start));
	distance_ex[start] = 0;

	while (!q.empty()) {
		pair<int, int> p = q.top();
		q.pop();

		int dist = -p.first;
		int vertex = p.second;

		if (distance_ex[vertex] < dist)
			continue;

		for (pair<int, int>& k : gp_ex[vertex]) {
			int end_vertex = k.first;
			int weight = k.second;

			int cost = distance_ex[vertex] + weight;
			if (distance_ex[end_vertex] > cost) {
				distance_ex[end_vertex] = cost;
				q.push(make_pair(-cost, end_vertex));
			}
		}
	}
}

int main() {
	int N, M, C;
	cin >> N >> M >> C;

	distance_ex = vector<int>(N + 1, INF);

	while (M--) {
		int X, Y, Z;
		cin >> X >> Y >> Z;
		
		gp_ex[X].push_back(make_pair(Y, Z));
	}

	dijk(C);
	
	int cnt = 0;
	int time = 0;

	for (int dist : distance_ex) {
		if (dist == 0 || dist == INF)
			continue;

		if (dist > time) time = dist;
		cnt++;
	}

	cout << cnt << " " << time;
}