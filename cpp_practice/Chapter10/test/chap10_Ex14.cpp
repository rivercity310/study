#include <iostream>
#include <map>

using std::map;
using std::string;

void chap10_Ex14() {
	std::cout << "***** 암호관리 프로그램 WHO를 시작합니다 *****" << std::endl;
	map<string, string> pas;

	while (true) {
		std::cout << "삽입(1), 검사(2), 종료(3) : ";
		int select; std::cin >> select;

		switch (select) {
		case 1:
		{
			std::cout << "이름 암호 >> ";
			string id, password;
			std::cin >> id >> password;

			pas.insert(make_pair(id, password));
			break;
		}
		case 2:
		{
			std::cout << "이름? ";
			string id; std::cin >> id;
			std::cout << "암호? ";
			string password; std::cin >> password;

			if (pas.find(id) == pas.end()) std::cout << "실패~" << std::endl;
			else {
				std::cout << "통과~" << std::endl;
				std::cout << "ID: " << id << "PW: " << pas[id] << std::endl;
			}
			break;
		}
		case 3:
			std::cout << "프로그램을 종료합니다..." << std::endl;
			exit(0);
		default:
			std::cout << "잘못된 입력입니다..." << std::endl;
			break;
		}
	}
}