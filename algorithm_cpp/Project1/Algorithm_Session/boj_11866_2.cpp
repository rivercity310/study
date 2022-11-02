#include <iostream>
#include <queue>

using namespace std;

int n, k;


static void solve(queue<int>& q) {
	int del = k % q.size();
	queue<int> copyQ;

	int idx = 0;
	while (!q.empty()) {
		idx++;

		if (idx == del) {
			cout << q.front();
			q.pop();
			break;
		}

		q.push(q.front());
		q.pop();
	}

	while (!q.empty()) {
		copyQ.push(q.front());
		q.pop();
	}

	if (copyQ.empty()) return;

	cout << ", ";
	solve(copyQ);
}

void boj_11866_2() {
	cin >> n >> k;

	queue<int> q;
	for (int i = 1; i <= n; i++)
		q.push(i);

	cout << "<";
	solve(q);
	cout << ">";
}