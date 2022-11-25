#include <iostream>

class BaseMemory {
	char* mem;
protected:
	BaseMemory(int size) { mem = new char[size]; }
	~BaseMemory() { delete[] mem; }
	
	void setMem(const char* x, int length) {
		for (int i = 0; i < length; i++) {
			*(mem + i) = *(x + i);
		}
	}
	void setMem(int idx, char crt) {
		*(mem + idx) = crt;
	}
public:
	char read(int idx) {
		return *(mem + idx);
	}
};

class ROM : public BaseMemory {
public:
	ROM(int memSize, const char* x, int length): BaseMemory(memSize) {
		setMem(x, length);
	}
};

class RAM : public BaseMemory {
public:
	RAM(int memSize) : BaseMemory(memSize) {}
	void write(int idx, char crt) {
		setMem(idx, crt);
	};
};

void chap8_Ex7() {
	std::string intro = "hello, my name is seungsu";
	
	int length = intro.length();
	const char* x = new char[length];
	x = intro.c_str();

	ROM biosROM(1024 * 10, x, length + 1);
	RAM mainMemory(1024 * 1024);

	// 0 ~ 4번지까지 bioseROM에서 읽어 mainMemory에 복사
	for (int i = 0; i < length; i++) mainMemory.write(i, biosROM.read(i));
	for (int i = 0; i < length; i++) std::cout << mainMemory.read(i);

	delete[] x;
	
}