#include <iostream>

#include "Container.h"
#include "CoffeeVendingMachine.h"

using namespace std;

Container::Container() {
	size = 10;
}

void Container::fill() {
	size = 10;
}

void Container::consume(int n) {
	this->size -= n;
}

int Container::getSize() {
	return this->size;
}

CoffeeVendingMachine::CoffeeVendingMachine() {
	tank = new Container[3];
}

void CoffeeVendingMachine::fill() {
	for (int i = 0; i < 3; i++)
		(tank + i)->fill();
}

// 0은 커피, 1은 물, 2는 설탕통
void CoffeeVendingMachine::checkTank(int selected) {
	int coffee = (tank + 0)->getSize();
	int water = (tank + 1)->getSize();
	int sugar = (tank + 2)->getSize();

	switch (selected) {
	case 1:
		if (coffee >= 1 && water >= 1) selectEspresso();
		else cout << "재료가 부족해요!" << endl;
		break;
	case 2:
		if (coffee >= 1 && water >= 2) selectAmericano();
		else cout << "재료가 부족해요!" << endl;
		break;
	case 3:
		if (coffee >= 1 && water >= 2 && sugar >= 1) selectSugarCoffee();
		else cout << "재료가 부족해요!" << endl;
		break;
	}
}

void CoffeeVendingMachine::selectEspresso() {
	(tank + 0)->consume(1);
	(tank + 1)->consume(1);
	cout << "에스프레소 나왔습니다!" << endl;
}

void CoffeeVendingMachine::selectAmericano() {
	(tank + 0)->consume(1);
	(tank + 1)->consume(2);
	cout << "아메리카노 나왔습니다!" << endl;
}

void CoffeeVendingMachine::selectSugarCoffee() {
	(tank + 0)->consume(1);
	(tank + 1)->consume(2);
	(tank + 2)->consume(1);
	cout << "설탕커피 나왔습니다!" << endl;
}

void CoffeeVendingMachine::show() {
	int coffee = (tank + 0)->getSize();
	int water = (tank + 1)->getSize();
	int sugar = (tank + 2)->getSize();

	cout << "커피: " << coffee << ", 물: " << water << ", 설탕: " << sugar << endl;
}

void CoffeeVendingMachine::run() {
	cout << "**** 커피자판기를 작동합니다 ****" << endl;
	while (true) {
		cout << "메뉴 (1: 에스프레소, 2: 아메리카노, 3: 설탕커피, 4: 잔량보기, 5: 채우기)" << endl;
		int selected;
		cin >> selected;
		
		switch (selected) {
		case 1:
		case 2:
		case 3:
			checkTank(selected);
			break;
		case 4:
			show();
			break;
		case 5:
			fill();
			show();
			break;
		default:
			cout << "잘못된 입력이네요... " << endl;
			break;
		}
	}
}


void Ex11() {
	CoffeeVendingMachine CVM;
	CVM.run();
}