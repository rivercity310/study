#include <iostream>
#include <string>

using namespace std;

void rsp() {

	cout << "가위 바위 보 게임을 합니다. ";
	cout << "가위, 바위, 보 중에서 입력하세요." << endl;

	string p1;
	string p2;
	cout << "로미오: ";
	getline(cin, p1);

	cout << "줄리엣: ";
	getline(cin, p2);

	int r = p1 == "가위" ? 0 : p1 == "바위" ? 1 : 2;
	int j = p2 == "가위" ? 0 : p2 == "바위" ? 1 : 2;

	if (r == j) cout << "비겼습니다" << endl;
	else if ((r == 0 && j == 1) || (r == 1 && j == 2) || (r == 3 && j == 0)) cout << "줄리엣 승리" << endl;
	else cout << "로미오 승리" << endl;
}
