#include <iostream>
#include <vector>
#include <string>
#include <ctime>

using std::string;

class Nation {
	string nation;
	string capital;
public:
	Nation(string nation, string capital) {
		this->nation = nation;
		this->capital = capital;
	}
	string getNation() { return this->nation; }
	string getCapital() { return this->capital; }
};

class GameUI {
	std::vector<Nation> v;
public:
	void run();
	void inputInfos();
	void quiz();
	void finish();
};

void GameUI::inputInfos() {
	std::cout << "현재 " << v.size() << "개의 나라가 입력되어 있습니다." << std::endl;
	std::cout << v.size() + 1 << " : ";
	string nat, cap;
	std::cin >> nat >> cap;

	v.push_back(Nation(nat, cap));
}

void GameUI::quiz() {
	srand((unsigned)time(NULL));
	int r = rand() % v.size();

	std::cout << v[r].getNation() << "의 수도는 ? ";
	string answer; std::cin >> answer;

	if (answer == v[r].getCapital()) std::cout << "Correct!!" << std::endl;
	else std::cout << "No!!" << std::endl;
}

void GameUI::finish() {
	std::cout << "프로그램을 종료합니다!" << std::endl;
}

void GameUI::run() {
	std::cout << "***** 나라의 수도 맞추기 게임 시작! *****" << std::endl;
	while (true) {
		std::cout << "정보 입력(1), 퀴즈(2), 종료(3) : ";
		int input; std::cin >> input;

		switch (input) {
		case 1:
			GameUI::inputInfos();
			break;
		case 2:
			GameUI::quiz();
			break;
		case 3:
			GameUI::finish();
			return;
		default:
			std::cout << "잘못된 입력입니다.." << std::endl;
			break;
		}
	}
}

void chap10_Ex10() {
	GameUI* game = new GameUI();
	game->run();

	delete game;
}