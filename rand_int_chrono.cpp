#include <iostream>
#include <random>
#include <vector>
#include <algorithm>
#include <iomanip>
#include <chrono>

#define MAX_RANGE 100
using namespace std;

void rd_int_chrono() {
	random_device rd;
	mt19937 rand(rd());
	uniform_int_distribution<mt19937::result_type> uniform_dist(1, MAX_RANGE);

	vector<int> v;

	chrono::steady_clock::time_point begin = chrono::steady_clock::now();
	while (true) {
		int random_int = uniform_dist(rand);
		cout << "random: " << random_int << endl;

		vector<int>::iterator it = find(v.begin(), v.end(), random_int);

		if (it == v.end()) {
			v.emplace_back(random_int);
			if (v.size() == MAX_RANGE) break;
		}
	}

	chrono::steady_clock::time_point end = chrono::steady_clock::now();

	cout << "[ 정렬 전 ]" << endl;
	for (auto it = v.begin(); it != v.end(); it++)
		cout << setw(5) << *it;
	cout << endl;

	sort(v.begin(), v.end());

	cout << "[ 정렬 후 ]" << endl;
	for (auto i : v)
		cout << setw(5) << i;
	cout << endl;

	auto diff = chrono::duration_cast<chrono::milliseconds>(end - begin);
	cout << "\n" << "소요 시간: " << diff.count() << "ms" << endl;
}