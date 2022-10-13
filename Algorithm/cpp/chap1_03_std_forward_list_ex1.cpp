#include <iostream>
#include <forward_list>
#include <string>

/*
[ 연결 리스트에서 remove_if() 함수를 이용한 조건부 원소 삭제 ]

선거 기간에 일부 시민들의 정보를 이용하여 선거권이 없는 사람을 가려내기

*/

struct citizen {
	std::string name;
	int age;
};

std::ostream& operator<<(std::ostream& os, const citizen& c) {
	return (os << "[" << c.name << ", " << c.age << "]");
}

void std_forward_list_ex1() {
	std::forward_list<citizen> citizens = {
		{"Kim", 22}, {"Lee", 25}, {"Park", 18}, {"Jin", 16}
	};

	std::cout << "전체 시민들: ";
	for (const auto& c : citizens) std::cout << c << " ";
	std::cout << std::endl;



	citizens.remove_if([](const citizen& c) {
		return (c.age < 19);
		});

	std::cout << "투표권이 있는 시민들: ";
	for (const auto& c : citizens) std::cout << c << " ";
	std::cout << std::endl;
}