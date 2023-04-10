#include <iostream>
#include <vector>

void chap10_Ex9() {
	std::vector<int> v;

	while (true) {
		int input; 
		std::cout << "점수를 입력하세요 (0을 입력하면 종료) ";
		std::cin >> input;
		if (input == 0) {
			std::cout << "프로그램을 종료합니다..." << std::endl;
		}
		else v.push_back(input);

		int sum = 0;
		for (int i = 0; i < v.size(); i++) {
			sum += v[i];
			std::cout << v[i] << " ";
			std::cout << std::endl;
		}
		std::cout << "평균 = " << (double)sum / v.size() << std::endl;
	}
}
