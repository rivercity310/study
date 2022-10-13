#pragma once
#include "Game.h"

class Food : public GameObject {
public:
	Food(int startX, int startY, int distance);
	void move();
	char getShape();
};