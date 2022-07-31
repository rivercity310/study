#include <iostream>
#include <iomanip>
#include <vector>
#include <queue>

using namespace std;

constexpr int UNREACHABLE = 1e9;
constexpr int MAX_VERTEX = 1e5 + 1;
constexpr int FW_MAX = 501;

vector<pair<int, int>> ex_grp[MAX_VERTEX];
int ex_dist[MAX_VERTEX];

int ex_fw_grp[FW_MAX][FW_MAX];

// 함수에서 2차원 배열을 매개변수로 사용할 때는 매개변수 이름 뒤에 []를 두 개 붙이고 
// 두 번째 대괄호에는 배열의 가로 크기를 지정해야 합니다
void prt_fw_grp(int(*fw_grp)[FW_MAX], int N) {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			int val = fw_grp[i][j] == UNREACHABLE ? -1 : fw_grp[i][j];
			cout << setw(5) << left << val;
		}
		cout << "\n";
	}
}

void init_ex_grp(int N) {
	ex_grp[1] = { {2, 2}, {3, 5}, {4, 1} };
	ex_grp[2] = { {3, 3}, {4, 2} };
	ex_grp[3] = { {2, 3}, {6, 5} };
	ex_grp[4] = { {3, 3}, {5, 1} };
	ex_grp[5] = { {3, 1}, {6, 2} };

	fill(ex_dist, ex_dist + (N + 1), UNREACHABLE);
}

void init_ex_fw_grp(int N) {
	for (int i = 0; i <= N; i++)
		fill(ex_fw_grp[i], ex_fw_grp[i] + (N + 1), UNREACHABLE);

	for (int i = 0; i <= N; i++)
		for (int j = 0; j <= N; j++)
			if (i == j)
				ex_fw_grp[i][j] = 0;

	for (int i = 0; i <= N; i++)
		for (pair<int, int>& p : ex_grp[i]) {
			ex_fw_grp[i][p.first] = p.second;
		}

	cout << "[ fw_grp 초기화 완료 ]" << "\n";
	prt_fw_grp(ex_fw_grp, N);
	cout << "\n\n";
}

void ex_floyd_warshall(int N) {
	for (int k = 1; k <= N; k++)
		for (int a = 1; a <= N; a++)
			for (int b = 1; b <= N; b++)
				ex_fw_grp[a][b] = std::min(ex_fw_grp[a][b], ex_fw_grp[a][k] + ex_fw_grp[k][b]);
}

void ex_dijkstra(int start) {
	priority_queue<pair<int, int>> pq;
	pq.push({ 0, start });
	ex_dist[start] = 0;

	while (!pq.empty()) {
		pair<int, int> p = pq.top();
		pq.pop();

		int now_dist = -p.first;
		int now_vertex = p.second;

		if (ex_dist[now_vertex] < now_dist)
			continue;

		for (pair<int, int>& k : ex_grp[now_vertex]) {
			int linked_vertex = k.first;
			int weight = k.second;

			int cost = ex_dist[now_vertex] + weight;
			if (ex_dist[linked_vertex] > cost) {
				ex_dist[linked_vertex] = cost;
				pq.push({ -cost, linked_vertex });
			}
		}
	}
}

void dijk_to_fw() {
	int start = 2;
	int N = 6;

	init_ex_grp(N);

	ex_dijkstra(start);

	cout << "[ Dijkstra ] Start = " << start << "\n";
	for (int i = 1; i <= N; i++) {
		int val = ex_dist[i] == UNREACHABLE ? -1 : ex_dist[i];
		cout << setw(5) << left << val;
	}
	cout << "\n" << endl;

	init_ex_fw_grp(N);
	ex_floyd_warshall(N);

	cout << "[ Floyd - Warshall ]" << '\n';
	prt_fw_grp(ex_fw_grp, N);
}