#include <iostream>
#include <ctime>
#include <cstdlib>
#include <string>

class Player {
	std::string name;
public:
	Player() : Player("홍길동") {}
	Player(std::string name) { this->setName(name); }
	std::string getName() { return this->name; }
	void setName(std::string name) { this->name = name; }
};

class UpAndDownGame {
	static int randomInt;
public:
	static int gameInit(int r);
	static void run(Player* players, int size);
};

int UpAndDownGame::randomInt = UpAndDownGame::gameInit(100);

int UpAndDownGame::gameInit(int r) {
	srand(time(NULL));
	return rand() % r;
}

void UpAndDownGame::run(Player* players, int size) {
	UpAndDownGame::randomInt = UpAndDownGame::gameInit(100);
	std::cout << "Up & Down 게임을 시작합니다." << std::endl;
	std::cout << "[정답]: " << randomInt << std::endl;
	
	int i = 0;
	int left = 0, right = 99;
	while (true) {
		if (i >= size) i = 0;
		std::string pName = (players + i)->getName();
		std::cout << "답은 " << left << "과 " << right << "사이에 있습니다." << std::endl;
		
		int userInput;
		std::cout << pName << ": ";
		std::cin >> userInput;

		if (left < right && userInput > randomInt && userInput < right) right = userInput;
		if (left < right && userInput < randomInt && userInput > left) left = userInput;
		
		if (userInput == randomInt) {
			std::cout << pName << "님이 승리하였습니다." << std::endl;
			
			std::cout << "다시하기(1), 종료(0): ";
			int retry;
			std::cin >> retry;

			if (retry == 1) run(players, size);
			return;
		}

		i++;
	}
}

void chap6_challenge() {
	std::cout << "참가 인원을 입력해주세요: ";
	int size;
	std::cin >> size;
	std::cin.ignore();

	Player* players = new Player[size];
	for (int i = 0; i < size; i++) {
		std::cout << "플레이어 " << i + 1 << " 이름: ";
		std::string name;
		std::getline(std::cin, name, '\n');
		(players + i)->setName(name);
	}

	std::cout << "플레이어 등록이 완료되었습니다." << std::endl;

	UpAndDownGame::run(players, size);

	delete[] players;
} 