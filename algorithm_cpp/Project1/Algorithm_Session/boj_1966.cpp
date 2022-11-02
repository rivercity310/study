#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

void boj_1966() {
	int t;
	cin >> t;

	while (t--) {
		int n, m;
		cin >> n >> m;

		// n개의 문서, 현재 큐에 m번째 놓인 문서가 몇번째로 출력되는가 
		queue<int> printer;
		priority_queue<int> max_pq;
		priority_queue<int> min_pq;

		int f = -1;

		for (int i = 0; i < n; i++) {
			int x;
			cin >> x;

			if (i == m)
				f = x;

			printer.push(x);
			max_pq.push(x);
			min_pq.push(-x);
		}

		int out = 0;
		while (!max_pq.empty()) {
			int x = max_pq.top();
			max_pq.pop();

			int rk = printer.front();

			while (x != rk) {

				int tmp = printer.front();
				printer.push(tmp);
				printer.pop();
				rk = tmp;
			}

			printer.pop();
			out++;

			if (rk == f) {
				int max_val = max_pq.top();
				int min_val = -min_pq.top();

				if (max_val != min_val) cout << out;
				else cout << printer.size() + m;

				cout << endl;
				break;
			}
		}
	}	
}
