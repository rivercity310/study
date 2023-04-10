#pragma once
#include "Game.h"

class Human : public GameObject {
public:
	Human(int startX, int startY, int distance);
	void move();
	char getShape();
};