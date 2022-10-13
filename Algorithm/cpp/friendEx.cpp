#include <iostream>
using namespace std;

/*
friend: 클래스 멤버 함수로는 적합하지 않지만, 클래스의 private, protected 멤버를 접근해야 하는 경우
물론 클래스의 멤버 함수가 아니므로 상속되지는 않는다.
*/

class RectManager;

class Rect {
private:
	int width, height;
public:
	Rect(int a, int b) : width(a), height(b) {};
	friend RectManager;
};

class RectManager {
public:
	bool equals(Rect r, Rect s);
};

bool RectManager::equals(Rect r, Rect s) {
	if (r.width == s.width && r.height == s.height) return true;
	else return false;
}

void friendEx() {
	Rect a(3, 4), b(4, 5), c(4, 5);
	RectManager manager;

	if (manager.equals(b, c)) cout << "Equal!" << endl;
	else cout << "Not Equal!!" << endl;
}