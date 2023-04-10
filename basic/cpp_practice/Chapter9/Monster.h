#pragma once
#include "Game.h"

class Monster : public GameObject {
public:
	Monster(int startX, int startY, int distance);
	void move();
	char getShape();
};