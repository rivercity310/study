#include <iostream>
#include <vector>
#include <utility>
#include <set>

using namespace std;

// constexpr : 매크로(define)처럼 컴파일 상수로 지정
constexpr int INF = 1e9;

vector<vector<pair<int, int>>> grph;
vector<int> dist;
set<int> visited;
int cnt = 0;

ostream& operator << (ostream& os, vector<int>& dist) {
	for (int i = 1; i < dist.size(); i++) {
		if (dist[i] == INF)
			os << "-1 ";
		else
			os << dist[i] << " ";
	}

	cnt++;

	return os;
}

// 우선순위 큐(힙)를 사용하면 이 과정을 O(logN) 과정으로 줄일 수 있다. 
int get_smallest_node() {
	int min_value = INF;
	int idx = 0;

	for (int i = 1; i < grph.size(); i++) {
		if (dist[i] < min_value && visited.find(i) == visited.end()) {
			min_value = dist[i];
			idx = i;
		}
	}

	return idx;
}

void dijkstra_test(int start) {
	dist[start] = 0;
	visited.insert(start);
	for (auto& p : grph[start])
		dist[p.first] = p.second;

	cout << "\n\n노드 선택 : " << start << "\n";
	cout << "업데이트 완료\n";
	cout << dist << "\n\n" << endl;

	for (int i = 0; i < grph.size() - 2; i++) {
		int now = get_smallest_node();
		visited.insert(now);

		for (auto& p : grph[now]) {
			int cost = dist[now] + p.second;

			if (cost < dist[p.first])
				dist[p.first] = cost;
		}

		cout << "노드 선택 : " << now << "\n";
		cout << "업데이트 완료\n";
		cout << dist << "\n\n" << endl;
	}
}

int main_dijk() {
	int N, M;
	cin >> N >> M;

	grph = vector<vector<pair<int, int>>>(N + 1);
	dist = vector<int>(N + 1, INF);

	int start;
	cin >> start;

	for (int i = 0; i < M; i++) {
		int a, b, c;
		cin >> a >> b >> c;

		grph[a].push_back(make_pair(b, c));
	}

	dijkstra_test(start);

	cout << dist;

	cout << "\n\n연산자 중복 함수의 호출 횟수 : " << cnt;

	return 0;
}