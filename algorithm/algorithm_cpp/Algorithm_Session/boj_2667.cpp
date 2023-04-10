#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

constexpr int MAX = 26;
constexpr int SIZE = 4;

static int cnt;
static int rows[] = { -1, 1, 0, 0 };
static int cols[] = { 0, 0, -1, 1 };

int arr[MAX][MAX];
vector<int> rst;

static void bfs(int r, int c, int n) {
	queue<pair<int, int>> q;
	q.push(make_pair(r, c));
	arr[r][c] = 0;

	while (!q.empty()) {
		auto tmp = q.front();
		q.pop();

		cnt++;

		int curR = tmp.first;
		int curC = tmp.second;

		for (int i = 0; i < SIZE; i++) {
			int nextR = rows[i] + curR;
			int nextC = cols[i] + curC;

			if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n || !arr[nextR][nextC])
				continue;
	
			q.push(make_pair(nextR, nextC));
			arr[nextR][nextC] = 0;
		}
	}

	rst.push_back(cnt);
	cnt = 0;
}

void boj_2667() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n;
	cin >> n;

	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			scanf_s("%1d", &arr[i][j]);

	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			if (arr[i][j]) {
				bfs(i, j, n);
				cnt = 0;
			}

	std::sort(rst.begin(), rst.end());

	cout << rst.size() << "\n";
	for (int val : rst)
		cout << val << "\n";
}