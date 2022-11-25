#include <iostream>

// [ 실전문제 2. 미래도시 ]

using namespace std;
constexpr int INF = 1e9;
constexpr int MAX = 101;

int city[MAX][MAX];

void floyd_w(int N) {
	for (int k = 1; k <= N; k++)
		for (int a = 1; a <= N; a++)
			for (int b = 1; b <= N; b++)
				city[a][b] = std::min(city[a][b], city[a][k] + city[k][b]);
}

void dijk_ex2() {
	int N, M;
	cin >> N >> M;

	for (int i = 1; i < MAX; i++)
		std::fill(city[i], city[i] + (N + 1), INF);

	while (M--) {
		int a, b;
		cin >> a >> b;

		city[a][b] = 1;
		city[b][a] = 1;
	}

	floyd_w(N);

	int X, K;
	cin >> X >> K;

	int dist = city[1][K] + city[K][X];

	if (dist >= INF) cout << -1;
	else cout << dist;
}