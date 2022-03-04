#include <iostream>
#include <algorithm>
#include <vector>

void chap10_algorithm() {
	std::vector<int> v;
	
	std::cout << "5개의 정수를 입력하세요: ";
	for (int i = 0; i < 5; i++) {
		int n; std::cin >> n;
		v.push_back(n);
	}

	// v.begin()에서 v.end() 사이의 값을 오름차순으로 정렬
	sort(v.begin(), v.end());

	for (auto it = v.begin(); it != v.end(); it++)
		std::cout << *it << " ";
	std::cout << std::endl;
}