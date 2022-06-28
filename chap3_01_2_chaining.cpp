#include <iostream>
#include <vector>
#include <list>
#include <algorithm>

using namespace std;

class hash_chaining {
private:
	vector<list<int>> data;
	using uint = unsigned int;

public:
	hash_chaining(size_t n) {
		data = vector<list<int>>(n);
	}

	void insert(uint val) {
		int n = data.size();
		data[val % n].push_back(val);
		cout << val << " 삽입!" << endl;
	}
	
	bool find(uint val) {
		int n = data.size();
		list<int>* entries = &data[val & n];
		return std::find(entries->begin(), entries->end(), val) != entries->end();
	}

	void erase(uint val) {
		int n = data.size();
		list<int>& entries = data[val % n];

		auto it = std::find(entries.begin(), entries.end(), val);

		if (it != entries.end()) {
			entries.erase(it);
			cout << val << " 삭제!" << endl;
		}
	}

	void prt_all() {
		cout << "Index: ";
		for (int i = 0; i < data.size(); i++) cout << i << " ";
		cout << endl;
		
		for (int i = 0; i < data.size(); i++) {
			cout << i << "번째: ";

			for_each(data[i].begin(), data[i].end(), [](int val) {
				cout << val << " ";
				});
			cout << endl;
		}
	}
};

void hs_2() {
	hash_chaining hc(10);
	for (int i = 0; i < 10; i++) hc.insert(i);
	hc.prt_all();

	for (int i = 9; i > 6; i--) hc.erase(i);
	hc.prt_all();
}