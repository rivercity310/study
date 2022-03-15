#include <iostream>
#include <map>

using std::string;
using std::map;

class Program_UI {
private:
	map<string, int> m;
	void insert();
	void select();
	void finish();
public:
	void run();
};

void Program_UI::insert() {
	std::cout << "이름과 점수 >> ";
	string name;
	int score;
	std::cin >> name >> score;
	m.insert(make_pair(name, score));
}

void Program_UI::select() {
	std::cout << "이름: ";
	string name; std::cin >> name;

	if (m.find(name) == m.end()) {
		std::cout << "없음" << std::endl;
	}
	else {
		std::cout << m[name] << std::endl;
	}
}

void Program_UI::finish() {
	std::cout << "프로그램을 종료합니다..." << std::endl;
}

void Program_UI::run() {
	std::cout << "***** 점수관리 프로그램 HIGH SCORE를 시작합니다 *****" << std::endl;

	
	while (true) {
		std::cout << "입력(1), 조회(2), 종료(3)  :";
		int select; std::cin >> select;

		switch (select) {
		case 1:
			Program_UI::insert();
			break;
		case 2:
			Program_UI::select();
			break;
		case 3:
			Program_UI::finish();
			exit(0);
		default:
			std::cout << "잘못된 선택입니다." << std::endl;
			std::cout << "다시 선택해주세요." << std::endl;
			break;
		}
	}
}


void chap10_Ex13() {
	Program_UI* prog = new Program_UI();
	prog->run();

	delete prog;
}