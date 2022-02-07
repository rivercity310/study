#include <iostream>

using namespace std;

class Tower {
private:
	int height;
public:
	Tower();
	Tower(int height);
	~Tower();
	int getHeight();
};

Tower::Tower() : Tower(1) {}
Tower::Tower(int height) {
	this->height = height;
}
Tower::~Tower() {
	cout << "높이 " << this->height << "타워 소멸" << endl;
}

int Tower::getHeight() {
	return this->height;
}

/*
int main() {
	Tower myTower;
	Tower seoulTower(100);

	cout << "높이는 " << myTower.getHeight() << "미터" << endl;
	cout << "높이는 " << seoulTower.getHeight() << "미터" << endl;
}
*/