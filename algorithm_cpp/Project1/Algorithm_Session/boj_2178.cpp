#include <iostream>
#include <vector>
#include <queue>

using namespace std;

constexpr int MAX = 4;

vector<vector<int>> arr;

static int rows[] = { -1, 1, 0, 0 };
static int cols[] = { 0, 0, -1, 1 };

static void bfs(int i, int j) {
	queue<pair<int, int>> q;
	q.push({ i, j });

	while (!q.empty()) {
		auto tmp = q.front();
		q.pop();

		int curR = tmp.first;
		int curC = tmp.second;

		for (int i = 0; i < MAX; i++) {
			int nextR = curR + rows[i];
			int nextC = curC + cols[i];

			if (nextR < 0 || nextC < 0 || nextR >= arr.size() || nextC >= arr[nextR].size())
				continue;

			if (arr[nextR][nextC] == 0)
				continue;

			if (arr[nextR][nextC] == 1) {
				arr[nextR][nextC] = arr[curR][curC] + 1;
				q.push({ nextR, nextC });
			}
		}
	}
}

void boj_2178() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n, m;
	cin >> n >> m;

	arr = vector<vector<int>>(n, vector<int>(m));

	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			scanf_s("%1d", &arr[i][j]);

	bfs(0, 0);
	cout << arr[n - 1][m - 1];
}