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
	priority_queue<int, deque<int>, std::less<int>> pq;

	pq.push(1);
	pq.push(2);
	pq.push(-1);

	cout << pq.top() << endl;
	pq.pop();

	cout << pq.top() << endl;

	vector<int> v;
	v.push_back(5);
	v.push_back(4);
	v.push_back(3);

	const double pi = 3.14;
	sort(v.begin(), v.end());
	for_each(v.begin(), v.end(), [pi](int i) {cout << i * pi << " "; });
	cout << endl;


	for (auto i : v) cout << i << " ";
	cout << endl;

	const double fat_kg = 23.2;
	const double fat_rate = 27.2;
	double target_fat_rate;
	cout << "목표 체지방률? ";
	cin >> target_fat_rate;

	double target_fat_kg = fat_kg * target_fat_rate / fat_rate;
	printf("현재 체지방율: %.2f%%\n", fat_rate);
	printf("목표 체지방량: %.2fkg\n", target_fat_kg);
	printf("감량할 지방량: %.2fkg\n", fat_kg - target_fat_kg);
}