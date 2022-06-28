#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class cuckoo_hashing {
	vector<int> data1;
	vector<int> data2;
	int size;

	// 삽입은 재귀적으로 동작해야 하기 떄문에 실제 삽입 구현 함수를 따로 만든다.
	// 재귀 호출 횟수가 해시 테이블 크기보다 커진다면 순환으로 간주한다. 
	void insert_impl(int key, int cnt, int table) {
		if (cnt >= size) {
			cout << key << " 삽입 시 순환 발생!\n";
			cout << "재해싱이 필요합니다.." << endl;
			return;
		}

		if (table == 1) {
			int hash = hash1(key);
			if (data1[hash] == -1) {
				cout << table << "번 테이블에 " << key << " 삽입" << endl;
				data1[hash] = key;
			}
			else {
				int old = data1[hash];
				data1[hash] = key;
				cout << table << "번 테이블에 " << key << " 삽입: ";
				cout << "기존의 " << old << " 이동 -> ";
				insert_impl(old, cnt + 1, 2);
			}
		}
		else {
			int hash = hash2(key);
			if (data2[hash] == -1) {
				cout << table << "번 테이블에 " << key << " 삽입" << endl;
				data2[hash] = key;
			}
			else {
				int old = data2[hash];
				data2[hash] = key;
				cout << table << "번 테이블에 " << key << " 삽입: ";
				cout << "기존의 " << old << " 이동 -> ";
				insert_impl(old, cnt + 1, 1);
			}
		}
	}

public:
	cuckoo_hashing(int n) : size(n)
	{
		data1 = vector<int>(size, -1);
		data2 = vector<int>(size, -1);
	}

	// 두개의 해시 함수
	int hash1(int key) const {
		return key % size;
	}

	int hash2(int key) const {
		return (key / size) % size;
	}

	// 룩업
	vector<int>::iterator lookup(int key) {
		int hash_value1 = hash1(key);
		
		if (data1[hash_value1] == key) {
			cout << "1번 테이블에서 " << key << " 발견!" << endl;
			return data1.begin() + hash_value1;
		}

		int hash_value2 = hash2(key);

		if (data2[hash_value2] == key) {
			cout << "2번 테이블에서 " << key << " 발견!" << endl;
			return data2.begin() + hash_value2;
		}

		return data2.end();
	}

	// 삭제
	// lookup()은 양쪽 해시 테이블에서 key를 찾지 못하는 경우 data2.end()를 반환한다.
	void erase(int key) {
		auto pos = lookup(key);

		if (pos != data2.end()) {
			*pos = -1;
			cout << key << " 삭제!" << endl;
		}
		else cout << key << " 발견하지 못함!" << endl;
	}

	// 삽입
	void insert(int key) {
		insert_impl(key, 0, 1);
	}

	void prt_all() {
		cout << "Index: ";
		for (int i = 0; i < size; i++) cout << i << '\t';
		cout << endl;

		cout << "Data1: ";
		for_each(data1.begin(), data1.end(), [](int val) {
			cout << val << '\t';
		});
		cout << endl;

		cout << "Data2: ";
		for (auto it = data2.begin(); it != data2.end(); it++)
			cout << *it << '\t';
		cout << endl;
	}
};

void cuckoo_hashing_ex() {
	cuckoo_hashing map(7);
	map.prt_all();
	cout << endl;

	map.insert(10);
	map.insert(20);
	map.insert(30);
	cout << endl;

	map.insert(104);
	map.insert(2);
	map.insert(70);
	map.insert(9);
	map.insert(90);
	map.insert(2);
	map.insert(7);
	cout << endl;

	map.prt_all();
	cout << endl;
	map.insert(14);   // 순환 발생

}