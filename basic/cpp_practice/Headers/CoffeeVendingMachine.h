#ifndef COFFEE_VENDING_MACHINE
#define COFFEE_VENDING_MACHINE

#include "Container.h"

class CoffeeVendingMachine {
private:
	Container* tank;         // tank[0]은 원두, tank[1]은 물, tank[2]는 설탕통
	void fill();               // 3개 통 모두 10으로 채우기
	void checkTank(int selected);          // 남은 용량 체크하기
	void selectEspresso();     // 에스프레소: 커피 1, 물 1
	void selectAmericano();    // 아메리카노, 커피 1, 물 2
	void selectSugarCoffee();  // 설탕커피, 커피 1, 물 2, 설탕 1
	void show();               // 현재 커피, 물, 설탕 잔량 출력
public:
	CoffeeVendingMachine();
	void run();                // 커피 자판기 작동
};


#endif