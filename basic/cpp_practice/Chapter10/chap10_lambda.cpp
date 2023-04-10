#include <iostream>
#include <vector>
#include <algorithm>

using std::cout;
using std::endl;
using std::string;

void chap10_lambda() {
	auto love = [](string a, string b) {
		cout << a << "보다 " << b << "가 좋아" << endl;
	};

	love("돈", "너");
	love("냉면", "만두");
}

// 지역 변수 pi의 값을 캡쳐 리스트를 이용하여 람다식에서 활용
void chap10_lambda2() {
	double pi = 3.14;
	auto calc = [pi](int r) -> double {return pi * r * r; };

	cout << "면적은 " << calc(3) << endl;
}

// 캡쳐 리스트에 참조를 활용하여 합을 외부 변수에 저장하는 람다식 만들기
void chap10_lambda3() {
	int sum = 0;
	[&sum](int x, int y) {sum = x + y; }(2, 3);
	cout << "합은 " << sum << endl;
}

// STL 템플릿에 람다식 활용
void chap10_lambda4() {
	std::vector<int> v = { 1, 2, 3, 4, 5 };

	// for_each()는 벡터 v의 첫번째 원소부터 끝까지 검색하면서
	// 각 원소에 대해 3번째 매개 변수인 람다식 호출, 매개 변수 n에 각 원소 값 전달
	std::for_each(v.begin(), v.end(), [](int n) {cout << n << " "; });
}