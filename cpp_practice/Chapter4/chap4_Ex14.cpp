#include <iostream>
#include <string>
#include <ctime>
#include <random>

#include "GamblingGame.h"

using namespace std;

GamblingGame::GamblingGame() {
	players = new Player[2];
	initPlayers();
}

GamblingGame::~GamblingGame() {
	delete[] players;
}

void GamblingGame::initPlayers() {
	for (int i = 0; i < 2; i++) {
		cout << i + 1 << "번째 선수 이름: ";
		string name;
		getline(cin, name);

		(players + i)->setName(name);
	}
}

void GamblingGame::run() {
	int i = 0;
	while (true) {
		if (i >= 2) i = 0;
		cout << (players + i)->getName() << "<Enter>" << endl;
		string enter;
		getline(cin, enter, '\n');

		srand(time(NULL));
		int first, second, third;
		first = rand() % 3;
		second = rand() % 3;
		third = rand() % 3;

		cout << first << "\t" << second << "\t" << third << "\t";
		if (first == second && second == third) {
			cout << (players + i)->getName() << "님 승리!" << endl;
			break;
		}
		else {
			cout << "아쉽군요!\n\n" << endl;
		}

		i++;
	}
}

void Player::setName(string name) {
	this->name = name;
}

string Player::getName() {
	return this->name;
}

void Ex14() {
	GamblingGame gg;
	gg.run();

}