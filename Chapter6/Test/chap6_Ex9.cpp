#include <iostream>
#include <string>
using std::string;

class Board {
private:
	static string timeline;
	static int idx;
public:
	static void add(string feed);
	static void print();
};

string Board::timeline = "";
int Board::idx = 0;

void Board::add(string feed) {
	string str = (idx++) + ": " + feed + "\n";
	timeline.append(str);
}

void Board::print() {
	std::cout << "************ 게시판입니다 *************" << std::endl;
	std::cout << timeline << std::endl;
}

void chap6_Ex9() {
	Board::add("중간고사는 감독 없는 자율 시험입니다.");
	Board::add("코딩 라운지 많이 이용해주세요");
	Board::print();
	
	Board::add("황승수 학생이 경진대회 입상하였습니다.");
	Board::print();
}