#include <iostream>
#include <vector>
#include <chrono>
#include <algorithm>
#include <random>
#include <iomanip>
using namespace std;


bool lin_sch(vector<int>& v, int val, int& idx) {
	for (int i = 0; i < v.size(); i++) {
		if (v[i] == val) {
			idx = i;
			return true;
		}
	}

	return false;
}

bool bin_sch(vector<int>& v, int val, int& idx) {
	int pl = 0;
	int pr = v.size() - 1;
	int pc;

	do {
		pc = (pl + pr) / 2;
		
		if (v[pc] == val) {
			idx = pc;
			return true;
		}
		else if (v[pc] < val) pl = pc + 1;
		else pr = pc - 1;
	} while (pl <= pr);

	return false;
}

void prt_vt(vector<int> v) {
	for (auto i : v)
		cout << setw(10) << left << i;
	cout << "\n" << endl;
}

void sch_test() {
	vector<int> v;
	random_device rd;
	mt19937 rand(rd());

	// [a, b] 범위에서 정수 난수 발생
	uniform_int_distribution<mt19937::result_type> uniform_dist(1, 1000);

	// 벡터에 난수 추가
	for (int i = 0; i < 50; i++)
		v.emplace_back(uniform_dist(rand));


	cout << "정렬 전 벡터" << endl;
	prt_vt(v);

	// std::sort() -> Quick Sort 실행
	sort(v.begin(), v.end());

	cout << "정렬 후 벡터" << endl;
	prt_vt(v);

	while (true) {
		int s;
		cout << "(1) 선형 검색, (2) 이진 검색, (3) 종료  >> ";
		cin >> s;
		if (s == 3) return;

		int idx;
		int key;
		cout << "찾을 값: ";
		cin >> key;

		// 검색 시간측정 시작

		chrono::steady_clock::time_point begin = chrono::steady_clock::now();
		switch (s) {
		case 1: 
			if (lin_sch(v, key, idx)) {
				cout << key << ": " << idx << "번째에 존재!" << endl;

				chrono::steady_clock::time_point end = chrono::steady_clock::now();
				auto diff = chrono::duration_cast<chrono::microseconds>(end - begin);

				cout << "선형 검색 수행시간: " << diff.count() << "ms" << endl;
			}
			else 
				cout << key << ": 존재하지 않습니다!" << endl;
			break;

		case 2:
			if (bin_sch(v, key, idx)) {
				cout << key << ": " << idx << "번째에 존재!" << endl;

				chrono::steady_clock::time_point end = chrono::steady_clock::now();
				auto diff = chrono::duration_cast<chrono::microseconds>(end - begin);

				cout << "이진 검색 수행시간: " << diff.count() << "ms" << endl;
			}
			else
				cout << key << ": 존재하지 않습니다!" << endl;
			break;
		}
	}
}
