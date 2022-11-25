#include <iostream>
#include <vector>
using namespace std;

class hash_map {
	using uint = unsigned int;

	vector<int> data;

public:
	hash_map(size_t n) {
		data = vector<int>(n, -1);
	}

	void insert(int val) {
		int n = data.size();
		data[val % n] = val;
		cout << val << " 삽입!" << endl;
	}

	bool find(uint val) {
		int n = data.size();
		return data[val % n] == val;
	}

	void erase(uint val) {
		int n = data.size();
		if (data[val % n] == val) {
			data[val % n] = -1;
			cout << val << " 삭제!" << endl;
		}
	}
};

void hs_1() {
	hash_map hs(10);

	auto print = [&](int value) {
		if (hs.find(value)) cout << "해시 맵에서 " << value << " 찾음!" << endl;
		else cout << "해시 맵에서 " << value << " 찾지 못함!" << endl;
		cout << endl;
	};

	for (int i = 0; i < 10; i++) hs.insert(i);
	print(5);

	hs.erase(8);
	print(8);



}