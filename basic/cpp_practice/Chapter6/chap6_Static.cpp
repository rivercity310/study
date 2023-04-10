#include <iostream>
using namespace std;

class Chap6_Person {
	int money;    // 개인 소유의 돈
public:
	Chap6_Person(int money) { this->money = money; }
	void addMoney(int money) { this->money += money; }
	int getMoney() { return this->money; }
	static int sharedMoney;  // 공금
	static void addShared(int n) {
		sharedMoney += n;
	}
};

// static 멤버 변수는 외부에 전역 변수로 선언되어야 한다.
int Chap6_Person::sharedMoney = 100;

void St() {
	Chap6_Person han(100);
	han.addMoney(300);    // han의 개인 돈
	han.sharedMoney = 200;  // 공금
	Chap6_Person::addShared(1000);    // ::를 이용해서도 접근 가능

	Chap6_Person lee(500);
	lee.addMoney(550);
	lee.addShared(1000);

	cout << "han's money: " << han.getMoney() << endl;
	cout << "lee's money: " << lee.getMoney() << endl;
	cout << "Shared Money: " << Chap6_Person::sharedMoney << endl;
}