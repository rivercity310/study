#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

void boj_1966() {
	int t;
	cin >> t;

	while (t--) {
		queue<int> q;
		priority_queue<int> pq;

		int n, m;
		cin >> n >> m;

		int tmp = -1;

		for (int i = 0; i < n; i++) {
			int x;
			cin >> x;

			if (i == m) tmp = x;

			pq.push(x);
			q.push(x);
		}

		int cnt = 0;
		while (true) {
			
			
		}
	}
}
