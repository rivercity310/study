#include <iostream>
#include <vector>
using std::vector;

class Shape {
protected:
	virtual void draw() = 0;
public:
	void paint() { draw(); }
};

class Circle : public Shape {
protected:
	virtual void draw() { std::cout << "Circle" << std::endl; }
};

class Rect : public Shape {
protected:
	virtual void draw() { std::cout << "Rectangle" << std::endl; }
};

class Line : public Shape {
protected:
	virtual void draw() { std::cout << "Line" << std::endl; }
};

class UI {
public:
	static int selectMenu() {
		std::cout << "삽입(1), 삭제(2), 모두보기(3), 종료(4) >> ";
		int n; std::cin >> n;

		return n;
	}

	static int selectShape() {
		std::cout << "선(1), 원(2), 사각형(3) >> ";
		int n; std::cin >> n;

		return n;
	}

	static int selectDelIdx() {
		std::cout << "삭제하고자 하는 도형의 인덱스 >> ";
		int n; std::cin >> n;

		return n;
	}

	static void showAll(vector<Shape*>& v, vector<Shape*>::iterator& it) {
		int i = 0;
		for (it = v.begin(); it != v.end(); it++, i++) {
			std::cout << i << ": ";
			v.at(i)->paint();
		}
	}
};

class GraphicEditor {
	vector<Shape*> v;
	vector<Shape*>::iterator it;
public:
	GraphicEditor() { 
		std::cout << "그래픽 에디터입니다." << std::endl; 
		run();
	}
	void run();
};

void GraphicEditor::run() {
	while (true) {
		switch (UI::selectMenu()) {
		case 1:
			switch (UI::selectShape()) {
			case 1:
				v.push_back(new Line());
				break;
			case 2:
				v.push_back(new Circle());
				break;
			case 3:
				v.push_back(new Rect());
				break;
			default:
				std::cout << "1, 2, 3번 중에서 선택해주세요.." << std::endl;
				break;
			}
			break;
		case 2:
		{
			int n = UI::selectDelIdx();
			if (n >= v.size() || n < 0) {
				std::cout << "없는 인덱스입니다..." << std::endl;
				break;
			}

			it = v.begin();
			Shape* tmp = *(it + n);
			v.erase(it + n);
			delete tmp;
			break;
		}
		case 3:
			UI::showAll(v, it);
			break;
		case 4:
			std::cout << "프로그램을 종료합니다.." << std::endl;
			exit(0);
		default:
			std::cout << "잘못된 입력입니다.." << std::endl;
			break;
		}
	}
}

void chap10_Ex16() {
	GraphicEditor ge;
	ge.run();
}