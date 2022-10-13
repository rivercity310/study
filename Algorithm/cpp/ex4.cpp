#include <iostream>
#include <vector>
#include <queue>

using namespace std;

constexpr int MAX = 501;

int t[MAX];
int indg[MAX];
vector<int> g[MAX];

void tp_st(int N) {
	queue<int> q;
	for (int i = 1; i <= N; i++)
		if (indg[i] == 0)
			q.push(i);

	// deep-copy
	vector<int> rst(N + 1);
	for (int i = 1; i <= N; i++)
		rst[i] = t[i];

	while (!q.empty()) {
		int now = q.front();
		q.pop();

		cout << now << " ";

		for (int k : g[now]) {
			indg[k]--;
			rst[k] = max(rst[k], rst[now] + t[k]);

			if (indg[k] == 0)
				q.push(k);
		}
	}

	cout << endl;

	for (int k : rst) {
		if (k == 0) continue;
		cout << k << "\n";
	}
}

void ex4_test() {
	int N;
	cin >> N;

	vector<int> data[MAX];

	for (int i = 1; i <= N; i++) {
		int p;
		while (true) {
			cin >> p;
			if (p == -1) break;

			data[i].push_back(p);
		}

		t[i] = data[i][0];

		for (int j = 1; j < data[i].size(); j++)
			g[data[i][j]].push_back(i);
	}

	for (int i = 1; i <= N; i++)
		for (int k : g[i])
			indg[k]++;

	tp_st(N);
}
