#include <iostream>
#include <vector>
#include <chrono>
#include <algorithm>
#include <random>
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

void sch_test() {
	vector<int> v;
	while (true) {
		int n;
		cout << "Vector Input: ";
		cin >> n;
		if (n == -1) break;

		v.emplace_back(n);
	}


	cout << "정렬 전 벡터" << endl;
	for (auto i : v) cout << i << " ";
	cout << "\n" << endl;

	sort(v.begin(), v.end());

	cout << "정렬 후 벡터" << endl;
	for (auto i : v) cout << i << " ";
	cout << "\n" << endl;

	while (true) {
		int s;
		cout << "(1) 선형 검색, (2) 이진 검색, (3) 종료  >> ";
		cin >> s;
		if (s == 3) return;

		int idx;
		int key;
		cout << "찾을 값: ";
		cin >> key;

		switch (s) {
		case 1: 
			if (lin_sch(v, key, idx))
				cout << key << ": " << idx << "번째에 존재!" << endl;
			else cout << key << ": 존재하지 않습니다!" << endl;
			break;
		case 2: 
			if (bin_sch(v, key, idx))
				cout << key << ": " << idx << "번째에 존재!" << endl;
			else
				cout << key << ": 존재하지 않습니다!" << endl;
			break;
		}
	}
}
