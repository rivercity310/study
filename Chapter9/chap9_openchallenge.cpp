#pragma once
#include <iostream>

#include "Game.h"
#include "Human.h"
#include "Monster.h"
#include "Food.h"


class GameUI {
private:
	static GameObject* obj[3];
public:
	static void drawMap();
	static void run();
};

/*
obj[0] : Human
obj[1] : Monster
obj[2] : Food
*/

void GameUI::drawMap() {
	for (int i = 0; i < 10; i++)
		for (int j = 0; j < 20; j++) {
			if (obj[0]->getX() == i && obj[0]->getY() == j) std::cout << obj[0]->getShape();
			else if (obj[1]->getX() == i && obj[1]->getY() == j) std::cout << obj[1]->getShape();
			else if (obj[2]->getX() == i && obj[2]->getY() == j) std::cout << obj[2]->getShape();
			else std::cout << "-";
		}
}

void GameUI::run() {
	obj[0] = new Human(0, 0, 1);
	obj[1] = new Monster(5, 10, 2);
	obj[2] = new Food(8, 13, 1);

	while (true) {
		drawMap();
		if (obj[0]->collide(obj[1])) std::cout << "Monster Win!!" << std::endl; break;
		if (obj[0]->collide(obj[2])) std::cout << "Human Win!!" << std::endl; break;

		for (int i = 0; i < 3; i++) {
			obj[i]->move();
		}
	}

	delete[] obj;
}




void chap9_openchallenge() {
	GameUI::run();
}