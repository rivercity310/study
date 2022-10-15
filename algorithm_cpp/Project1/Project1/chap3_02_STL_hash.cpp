#include <iostream>
#include <unordered_map>
#include <unordered_set>
using namespace std;

namespace stl_hash {
	// 컨테이너의 모든 원소 출력
	void print(const unordered_set<int>& container) {
		for (const auto& element : container)
			cout << element << " ";
		cout << endl;
	}

	void print(const unordered_map<int, int>& container) {
		for (const auto& element : container)
			cout << "(" << element.first << ", " << element.second << ") ";
		cout << endl;
	}

	void find(const unordered_set<int>& container, const int element) {
		if (container.find(element) == container.end())
			cout << element << " 검색 실패!" << endl;
		else
			cout << element << " 검색 성공!" << endl;
	}

	void find(const unordered_map<int, int>& container, const int element) {
		unordered_map<int, int>::const_iterator it = container.find(element);
		if (it == container.end())
			cout << element << " 검색 실패!" << endl;
		else
			cout << element << " 검색 성공, 값 = " << it->second << endl;
	}
}

void stl_hs() {
	cout << "*** std::unordered_set 예제 ***" << endl;
	unordered_set<int> set = { 1, 2, 3, 4, 5 };

	cout << "set의 초기값: ";
	stl_hash::print(set);

	set.insert(2);
	set.insert(100);
	cout << "2, 100 삽입: ";
	stl_hash::print(set);

	cout << "100 삭제, 100, 2 찾기: " << endl;
	set.erase(100);
	stl_hash::find(set, 2);
	stl_hash::find(set, 100);
	stl_hash::print(set);

	cout << "\n\n" << endl;

	cout << "*** std::unordered_map 예제 ***" << endl;
	unordered_map<int, int> sqr_map;

	sqr_map.insert({ 2, 4 });
	sqr_map[3] = 9;
	cout << "2, 3의 제곱 삽입: ";
	stl_hash::print(sqr_map);

	sqr_map.erase(3);
	cout << "키 3 삭제, 3 찾기" << endl;
	stl_hash::find(sqr_map, 3);
	stl_hash::print(sqr_map);
}