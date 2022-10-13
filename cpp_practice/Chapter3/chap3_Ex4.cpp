#include <iostream>

using namespace std;
/*
CoffeeMachine 클래스를 작성해보자.
[ 규칙 ]
에스프레소 한 잔에는 커피와 물이 각각 1씩 소비된다.
아메리카노 한 잔에는 커피와 물이 각각 1, 2씩 소비된다.
설탕 커피는 커피 1, 물 2, 설탕 1이 소비된다.
*/

class CoffeeMachine {
private:
	int coffee;
	int water;
	int sugar;
public:
	CoffeeMachine();
	CoffeeMachine(int c, int w, int s);

	void fill();
	void show();
	void interfaces();

	void drinkEspresso(int servings);
	void drinkAmericano(int servings);
	void drinkSugarCoffee(int servings);
};

CoffeeMachine::CoffeeMachine() : CoffeeMachine(10, 10, 10) {}
CoffeeMachine::CoffeeMachine(int c, int w, int s) {
	this->coffee = c;
	this->water = w;
	this->sugar = s;
}


// 현재 커피 머신의 상태를 출력
void CoffeeMachine::show() {
	cout << "\n----------------------------------\n";
	cout << "[ 커피 머신 상태 ]\n";
	cout << "커피: " << this->coffee << "\n";
	cout << "물: " << this->water << "\n";
	cout << "설탕: " << this->sugar << "\n";
	cout << "-------------------------------------" << endl;
}

// 커피, 물, 설탕을 10씩 채우기 (최대 용량을 10이라고 가정)
void CoffeeMachine::fill() {
	this->coffee = 10;
	this->water = 10;
	this->sugar = 10;
}

void CoffeeMachine::interfaces() {
	cout << "재료가 부족해요..." << endl;
	while (true) {
		cout << "남은 재료 보기(1), 채우기(2), 나가기(3) >> ";
		int selected;
		cin >> selected;

		switch (selected) {
		case 1:
			show();
			break;
		case 2:
			fill();
			continue;
		case 3:
			return;
		}
	}
}

// 에스프레소, 아메리카노, 설탕 커피 추출
void CoffeeMachine::drinkEspresso(int servings) {
	for (int i = 0; i < servings; i++) {
		if (coffee > 0 && water > 0) {
			this->coffee -= 1;
			this->water -= 1;
		}
		else {
			interfaces();
		}
	}
}

void CoffeeMachine::drinkAmericano(int servings) {
	for (int i = 0; i < servings; i++) {
		if (coffee > 0 && water > 1) {
			this->coffee -= 1;
			this->water -= 2;
		}
		else {
			interfaces();
		}
	}
}

void CoffeeMachine::drinkSugarCoffee(int servings) {
	for (int i = 0; i < servings; i++) {
		if (coffee > 0 && water > 1 && sugar > 1) {
			this->coffee -= 1;
			this->water -= 2;
			this->sugar -= 2;
		}
		else {
			interfaces();
		}
	}
}

void operating(CoffeeMachine* c, int selected) {
	
	cout << "몇 잔? ";
	int servings;
	cin >> servings;

	switch (selected) {
	case 1:
		c->drinkEspresso(servings);
		cout << "에스프레소 " << servings << "잔 나왔습니다~\n" << endl;
		break;
	case 2:
		c->drinkAmericano(servings);
		cout << "아메리카노 " << servings << "잔 나왔습니다~\n" << endl;
		break;
	case 3:
		c->drinkSugarCoffee(servings);
		cout << "설탕 커피 " << servings << "잔 나왔습니다~\n" << endl;
		break;
	}

	c->show();
}

/*
int main() {
	cout << "[ 승수 카페에 오신 것을 환영합니다! ]\n" << endl;
	CoffeeMachine myMachine;
	CoffeeMachine* p = &myMachine;
	while (true) {
		cout << "어떤 커피를 주문하시겠어요? \n";
		cout << "에스프레소(1), 아메리카노(2), 설탕 커피(3), 퇴장(4) >> ";
		int selected;
		cin >> selected;
		if (selected == 4) {
			cout << "안녕히 가세요!\n" << endl;
			return 0;
		}
		else if (selected > 4) {
			cout << "알 수 없는 명령이네요....\n" << endl;
			continue;
		}

		operating(p, selected);
	}
}
*/