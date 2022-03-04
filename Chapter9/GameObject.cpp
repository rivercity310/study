#include <iostream>
#include "Game.h"

GameObject::GameObject(int startX, int startY, int distance) {
	this->x = startX;
	this->y = startY;
	this->distance = distance;
}
GameObject::~GameObject() {}

int GameObject::getX() { return this->x; }
int GameObject::getY() { return this->y; }

bool GameObject::collide(GameObject* p) {
	if (this->x == p->getX() && this->y == p->getY()) return true;
	else return false;
}