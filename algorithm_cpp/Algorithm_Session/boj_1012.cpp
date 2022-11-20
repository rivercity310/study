#include <iostream>
#include <vector>

constexpr int MAX = 51;
int grp[MAX][MAX];

using namespace std;

static bool dfs(int x, int y, int n, int m) {
	if (x < 0 || x >= n + 1 || y < 0 || y >= m + 1)
		return false;

	if (grp[x][y]) {
		grp[x][y] = 0;

		cout << "(" << x << ", " << y << ") ¹æ¹®\n";

		dfs(x + 1, y, n, m);
		dfs(x - 1, y, n, m);
		dfs(x, y + 1, n, m);
		dfs(x, y - 1, n, m);

		return true;
	}

	return false;
}

void boj_1012() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int t;
	cin >> t;

	while (t--) {
		int m, n, k;
		cin >> m >> n >> k;

		while (k--) {
			int x, y;
			cin >> x >> y;

			grp[y][x] = 1;
		}

		int cnt = 0;
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= m; j++)
				if (dfs(i, j, n, m))
					cnt++;

		cout << cnt << "\n";
	}
}