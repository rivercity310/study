#include <iostream>

class Shape {
	Shape* next;
protected:
	virtual void draw();
public:
	Shape() { next = NULL; }
	virtual ~Shape() {}
	void paint();
	Shape* add(Shape* p);
	Shape* getNext() { return next; }
};

void Shape::paint() {
	draw();       // 동적 바인딩
}

void Shape::draw() {
	std::cout << "-- Shape -- " << std::endl;
}

Shape* Shape::add(Shape* p) {
	this->next = p;
	return p;
}


class Circle : public Shape {
protected:
	virtual void draw() { std::cout << "Circle" << std::endl; }
};

class Rect : public Shape {
protected:
	virtual void draw() { std::cout << "Rect" << std::endl; }
};

class Line : public Shape {
protected:
	virtual void draw() { std::cout << "Line" << std::endl; }
};


void chap9_Shape() {
	Shape* pStart = NULL;
	Shape* pLast;

	pStart = new Circle();     // 처음에 원 도형 생성
	pLast = pStart;

	pLast = pLast->add(new Rect());
	pLast = pLast->add(new Circle());
	pLast = pLast->add(new Line());
	pLast = pLast->add(new Rect());

	// 연결된 모든 도형을 화면에 그린다
	Shape* p = pStart;
	while (p != NULL) {
		p->paint();
		p = p->getNext();
	}

	// 현재 연결된 모든 도형을 삭제한다
	p = pStart;
	while (p != NULL) {
		Shape* q = p->getNext();    // 다음 도형 주소 기억
		delete p;      // 기본 클래스의 가상 소멸자 호출
		p = q;
	}
}