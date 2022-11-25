/*
// 개발자가 작성한 클래스
class Sample {
	int a;
public:
	void setA(int x) {
		this->a = x;
	}
};


// 컴파일러에 의해 변환된 클래스
class Sample {
	int a;
public:
	void setA(Sample* this, int x) {
		this->a = x;
	}
};

int main() {
	Sample obj;
	obj.setA(5) => obj.setA(&obj, 5);
}
*/