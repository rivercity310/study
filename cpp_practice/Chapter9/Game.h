#pragma once

class GameObject {
protected:
	int distance;    // 한번 이동거리
	int x, y;        // 현재 위치 좌표
public:
	GameObject(int startX, int startY, int distance);
	
	virtual ~GameObject();
	virtual void move() = 0;
	virtual char getShape() = 0;

	int getX();
	int getY();
	bool collide(GameObject* p);
};