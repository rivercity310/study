#include <iostream>

class Base {
public:
	virtual ~Base() { std::cout << "~Base() called" << std::endl; }
	virtual void f() { std::cout << "Base::f() called" << std::endl; }
};

class Derived : public Base {
public:
	virtual ~Derived() { std::cout << "~Derived() called" << std::endl; }
	virtual void f() { std::cout << "Derived::f() called " << std::endl; }
};

void chap9_overide() {
	Derived d, * pDer;
	pDer = &d;
	pDer->f();
	pDer->Base::f();

	Base* pBase = pDer;   // 업캐스팅
	pBase->f();           // 동적 바인딩 발생!! Derived::f() 실행
}

void chap9_override2() {
	Base* pBase2 = new Derived();
	pBase2->f();
	delete pBase2;
}