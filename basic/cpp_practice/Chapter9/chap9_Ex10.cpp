#include <iostream>
#include <string>

using std::cout;
using std::endl;
using std::cin;
using std::string;

class UI { // 화면 출력 및 키 입력 함수
    static int n; // 무슨 기능을 할지 입력 받은 숫자
public:
    static void start(); // 첫줄에 나오는 그래픽 에디터 입니다 출력
    static int menu(); // 삽입 삭제 모두보기 종료
    static int insert(); // 도형 삽입
    static int del(); // 도형 삭제
};

int UI::n = 0;
void UI::start() {
    cout << "그래픽 에디터입니다." << endl;
}
int UI::menu() { // 메뉴 출력 및 입력
    cout << "삽입:1, 삭제:2, 모두보기:3, 종료:4 >> ";
    cin >> n;
    return n;
}
int UI::insert() { // 도형 삽입에 대한 메뉴 출력 및 입력
    cout << "선:1, 원:2, 사각형:3 >> ";
    cin >> n;
    return n;
}
int UI::del() { // 도형 삭제에 대한 메뉴 출력 및 입력
    cout << "삭제하고자 하는 도형의 인덱스 >> ";
    cin >> n;
    return n;
}

class Shape {
	Shape* next;
protected:
	virtual void draw() = 0;
public:
	Shape() { next = NULL; }
	virtual ~Shape() {}
	Shape* add(Shape* p) {
		this->next = p;
		return p;
	}
	void paint() {
		draw();
	}
	Shape* getNext() { return next; }
	void setNext(Shape* p) { this->next = p->next; }
};

class Line : public Shape {
protected:
	virtual void draw() {
		cout << "Line" << endl;
	}
};

class Circle : public Shape {
protected:
	virtual void draw() {
		cout << "Circle" << endl;
	}
};

class Rect : public Shape {
protected:
	virtual void draw() {
		cout << "Rectangle" << endl;
	}
};

class GraphicEditor {
private:
	int node_size;
	Shape* pStart;
	Shape* pLast;
public:
	GraphicEditor() {
		pStart = pLast = NULL;
		node_size = 0;
	}

	void run() {
		UI::start();

		while (true) {
			int num = UI::menu();

			switch (num) {
			case 1:
				num = UI::insert();
				insertShape(num);
				break;
			case 2:
				if (node_size == 0) {
					cout << "List Empty!!!" << endl;
				}
				else {
					num = UI::del();
					del(num);
				}
				break;
			case 3:
				show();
				break;
			case 4:
				exit(0);
			default:
				cout << "잘못된 입력입니다." << endl;
				break;
			}
		}
	}

	void insertShape(int num) {
		switch (num) {
		case 1:
			if (node_size == 0) {
				pStart = new Line();
				pLast = pStart;
			}
			else
				pLast = pLast->add(new Line());

			node_size++;
			break;
		case 2:
			if (node_size == 0) {
				pStart = new Rect();
				pLast = pStart;
			}
			else
				pLast = pLast->add(new Rect());

			node_size++;
			break;
		case 3:
			if (node_size == 0) {
				pStart = new Circle();
				pLast = pStart;
			}
			else
				pLast = pLast->add(new Circle());

			node_size++;
			break;
		default:
			cout << "잘못된 선택입니다." << endl;
			return;
		}
	}

	void del(int num) {
		Shape* p = pStart;
		Shape* del = pStart;
		
		if (node_size > num) {
			for (int i = 0; i < num; i++) {
				p = del;
				del = del->getNext();
			}

			if (num == 0) pStart = p->getNext();
			else p->setNext(del);

			node_size--;
			if (node_size == 1) pLast = pStart;
			delete del;
		}
		else cout << "잘못된 인덱스입니다.." << endl;
	}

	void show() {
		Shape* p = pStart;
		for (int i = 0; i < node_size; i++) {
			cout << i << " : ";
			p->paint();
			p = p->getNext();
		}
	}
};

void chap9_Ex10() {
	GraphicEditor* g_editor = new GraphicEditor();
	//g_editor->run();
	 
	cout << 47 * 11 + 543 << endl;
	delete g_editor;
}