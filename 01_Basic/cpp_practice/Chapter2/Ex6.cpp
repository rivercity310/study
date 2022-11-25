#include <iostream>
#include <cstring>

using namespace std;

void restaurant() {
	cout << "중식당에 오신 것을 환영합니다." << endl;

	while (true) {
		cout << "짬뽕: 1, 짜장:2, 군만두:3, 종료:4 >> ";
		int selected;
		cin >> selected;
		if (selected == 4) {
			cout << "오늘 영업은 끝났습니다." << endl;
			break;
		}

		cout << "몇 인분? ";
		int servings;
		cin >> servings;
		
		switch (selected) {
		case 1:
			cout << "짬뽕 " << servings << "인분 나왔습니다." << endl;
			break;
		case 2:
			cout << "짜장 " << servings << "인분 나왔습니다." << endl;
			break;
		case 3:
			cout << "군만두 " << servings << "인분 나왔습니다." << endl;
			break;
		default:
			cout << "다시 주문하세요!!" << endl;
			break;
		}
	}
}

void cafe() {
	int total = 0;
	cout << "에스프레소 2000원, 아메리카노 2300원, 카푸치노 2500원입니다." << endl;
	
	while (true) {
		if (total >= 20000) {
			cout << "오늘 영업은 종료! " << total << endl;
			break;
		}

		cout << "주문: ";
		string beverage;
		int servings;
		cin >> beverage >> servings;

		if (beverage == "espresso") total += 2000 * servings;
		else if (beverage == "americano") total += 2300 * servings;
		else if (beverage == "cafuccino") total += 2500 * servings;
		else {
			cout << "잘못된 입력입니다...." << endl;
			continue;
		}

		cout << beverage << " " << servings << "잔 나왔습니다." << endl;
	}
}
