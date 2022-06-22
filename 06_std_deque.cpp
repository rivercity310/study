#include <iostream>
#include <deque>
#include <stack>
#include <queue>

using namespace std;

void chap1_std_deque() {
	deque<int> deq = { 1, 2, 3, 4, 5 };

	deq.push_front(0);
	deq.push_back(6);

	deq.insert(deq.begin() + 2, 10);
	deq.pop_back();
	deq.pop_front();

	for (auto i : deq) cout << i << " ";
	cout << endl;

	deq.erase(deq.begin() + 1);
	deq.erase(deq.begin() + 3, deq.end());

	for (auto it = deq.begin(); it != deq.end(); it++) cout << *it << " ";
	cout << endl;
}

void chap1_std_stack() {
	stack<int, vector<int>> stk;

}

void chap1_std_queue() {
	queue<int> q;
	priority_queue<int, deque<int>, std::greater<int>> pq;
}