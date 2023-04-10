#include <iostream>
#include <string>

class Ex2_Person {
	int id;
	double weight;
	std::string name;
public:
	Ex2_Person(int id = 1, std::string name = "Grace", double weight = 20.5) {
		this->id = id;
		this->name = name;
		this->weight = weight;
	}
	void show() { std::cout << id << " " << weight << " " << name << std::endl; }
};

void chap6_Ex2() {
	Ex2_Person grace, ashley(2, "Ashley"), helen(3, "Helen", 32.5);
	grace.show();
	ashley.show();
	helen.show();
}