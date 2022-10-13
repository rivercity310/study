#include <iostream>
#include <list>
#include <vector>

using namespace std;

void chap1_std_list() {
	list<int> list1 = { 1, 2, 3, 4, 5 };
	list1.push_back(6);
	list1.insert(next(list1.begin()), 0);     // 리스트 맨 처음 원소 다음 위치에 0 삽입
	list1.insert(list1.end(), 7);             // 맨 뒤에 7 삽입

	list1.pop_back();
	cout << "삽입/삭제 후 리스트: ";
	for (auto i : list1) cout << i << " ";
	cout << endl;
}

void chap1_std_list2() {
	// => v_it4 반복자는 무효화된다.
	vector<int> vec = { 1, 2, 3, 4, 5 };
	auto v_it4 = vec.begin() + 4;
	vec.insert(vec.begin() + 2, 0);

	
	// l_it4 반복자는 여전히 유효하다.
	list<int> lst = { 1, 2, 3, 4, 5 };
	auto l_it4 = next(lst.begin(), 4);
	lst.insert(next(lst.begin(), 2), 0);

	cout << *l_it4 << endl;
}