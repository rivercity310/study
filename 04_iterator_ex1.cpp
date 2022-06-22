#include <iostream>
#include <forward_list>
#include <vector>

using namespace std;

/*
[ 다양한 반복자에서 이동하기 ]
*/

void chap1_iterator() {
	vector<string> vec = {
		"Lewis Hamilton", "Nico Roseberg", "Sebastian Vettel",
		"Fernando Alonso"
	};

	auto it = vec.begin();
	cout << "최근 우승자: " << *it << "\n";

	it += 3;
	cout << "3년 전 우승자: " << *it << "\n";

	advance(it, -1);
	cout << "그 후 1년 뒤 우승자: " << *it << endl;


	forward_list<string> fwd(vec.begin(), vec.end());

	auto it1 = fwd.begin();
	cout << "최근 우승자: " << *it1 << "\n";

	advance(it1, 2);
	cout << "2년 전 우승자: " << *it1 << "\n";

	// forward_list의 반복자는 순방향 반복자이므로 다음 코드는 에러 발생
	// advance(it1, -2)
}