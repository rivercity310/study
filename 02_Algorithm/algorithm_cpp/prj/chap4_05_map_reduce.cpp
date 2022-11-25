#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <cmath>
using namespace std;

// 맵 연산 테스트 함수 작성
void transform_test(vector<int>& v) {
	vector<int> Tr;

	cout << "[맵 테스트]" << endl;
	cout << "입력 배열: ";
	for (auto i : v) cout << i << " ";
	cout << endl;

	// std::transform() 함수 사용 : 원본을 바꾸지 않고 별도의 벡터를 만들어 반환
	std::transform(v.begin(), v.end(), std::back_inserter(Tr), [](int x) {
		return std::pow(x, 2);
	});

	cout << "std::transform(), Tr: ";
	for (auto i : Tr) cout << i << " ";
	cout << endl;

	// std::for_each() 함수 사용 : 원본 벡터 자체를 변경
	std::for_each(v.begin(), v.end(), [](int& x) {
		x = pow(x, 2);
	});

	cout << "std::for_each(), v: ";
	for (auto i : v) cout << i << " ";
	cout << endl;
}

void edr_test(vector<int> v) {
	cout << "\n[리듀스 테스트]" << endl;
	cout << "입력 배열: ";
	for (auto i : v) cout << i << " ";
	cout << endl;

	// std::accumulate() 함수 사용
	int ans = std::accumulate(v.begin(), v.end(), 0, [](int acc, int x) {
		return acc + x;
		});
	cout << "std::accumulate(), ans: " << ans << endl;

}

void map_reduce_test() {
	vector<int> v = { 1, 10, 4, 7, 3, 5, 6, 9, 8, 2 };

	transform_test(v);
	//reduce_test(v);
}