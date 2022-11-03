#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>
#include <cmath>

using namespace std;

void boj_1966() {
	int t;
	cin >> t;

	while (t--) {
		int n, m;
		cin >> n >> m;

		queue<int> q;
		deque<int> dq;

		int max_val = -1;
		int min_val = 10;

		for (int i = 0; i < n; i++) {
			int x;
			cin >> x;

			if (x > max_val) max_val = x;
			if (x < min_val) min_val = x;

			q.push(x);

			if (dq.size() == 0)
				dq.push_back(x);
			else if (x > dq.back())
				dq.push_back(x);
			else
				dq.push_front(x);
		}	

		int cnt = 0;
		while (!q.empty()) {
			if (q.front() != dq.front()) {
				q.push(q.front());
				q.pop();
			}

			else {
				dq.pop_front();
				int rk = q.front();
				q.pop();
				cnt++;

				// 남은 원소가 모두 같은 경우
				if (max_val == min_val) {
					// 1 1 1 (-1) 1
					cnt = 0;

					/*
					while (q.front() != f) {
						q.pop();
						cnt++;
					}
					*/
				}

				cout << cnt << endl;
			}
		}
	}
}
