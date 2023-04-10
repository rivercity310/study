#include <iostream>
#include <ctime>

#include "Monster.h"


Monster::Monster(int startX, int startY, int distance) : GameObject(startX, startY, distance) {}
void Monster::move() {
	srand(time(NULL));
	int rInt = rand() % 4;

	// 순서대로 왼쪽 아래 위 오른쪽
	switch (rInt) {
	case 0:
	{
		if (this->y >= distance) this->y -= distance;
		else this->y = 0;
		break;
	}
	case 1: 
	{
		if (this->x <= 7) this->x += distance;
		else this->x = 9;
		break;
	}
	case 2: 
	{
		if (this->x >= distance) this->x -= distance;
		else this->x = 0;
		break;
	}
	case 3:
	{
		if (this->y <= 17) this->y += distance;
		else this->y = 19;
		break;
	}
	}
}

char Monster::getShape() {
	return 'M';
}