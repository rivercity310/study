#include <iostream>
#include <string>

using namespace std;
int ID = 0;

/*
은행 계좌를 표현하는 Account 클래스 작성
- 클래스 선언부: Account.h에 작성
- 클래스 구현부: Account.cpp에 작성
- main(): main.cpp에 작성
*/

class Account {
private:
	string name;
	int money;
	int id;

public:
	Account();
	Account(string name, int id, int money);
	void withdraw(int sub);
	void deposit(int add);

	string getOwner() { return this->name; }
	int inquiry() { return this->money; }

	void setName(string name) { this->name = name; }
	void setId(int id) { this->id = id; }
	void setInitMoney(int initMoney) { this->money = initMoney; }
};

Account::Account() : Account("홍길동", 0, 0) {}
Account::Account(string name, int id, int money) {
	this->name = name;
	this->id = id;
	this->money = money;
}

void Account::deposit(int add) {
	this->money += add;
	cout << add << "원이 성공적으로 입금되었습니다." << endl;
}

void Account::withdraw(int sub) {
	if (this->money - sub < 0) {
		cout << "출금이 불가합니다." << endl;
		return;
	}

	this->money -= sub;
	cout << sub << "원이 성공적으로 출금되었습니다." << endl;
}


/*
int main() {
	Account a;

	while (true) {
		cout << "계좌 생성(1), 입금(2), 출금(3), 종료(4) : ";
		int selected;
		cin >> selected;

		// case 문 내에서 변수를 선언하려면 중괄호로 묶어주어야 한다.
		switch (selected) {
		case 1:
		{
			cout << "이름과 입금할 잔액을 빈칸으로 구분하여 입력: ";
			string name;
			int initMoney;
			cin >> name >> initMoney;
			a.setName(name);
			a.setInitMoney(initMoney);
			a.setId(ID++);
			cout << "계좌 생성에 성공하였습니다..." << endl;
			break;
		}

		case 2:
		{
			cout << "얼마를 입금하시겠어요? ";
			int add;
			cin >> add;

			a.deposit(add);
			cout << "현재 잔액: " << a.inquiry() << endl;
			break;
		}
		case 3:
			cout << "얼마를 출금하시겠어요? ";
			int sub;
			cin >> sub;

			a.withdraw(sub);
			cout << "현재 잔액: " << a.inquiry() << endl;
			break;

		case 4:
		{
			cout << "프로그램을 종료합니다..." << endl;
			return 0;
		}
		default:
			cout << "잘못된 선택입니다!!!" << endl;
			break;
		}
	}
}
*/