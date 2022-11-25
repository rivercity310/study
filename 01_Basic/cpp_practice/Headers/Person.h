#ifndef PERSON_H
#define PERSON_H

#include <string>
using namespace std;

class Person {
private:
	string name;
	string tel;
public:
	Person() {}
	Person(string name) { this->name = name; }
	void setName(string name) { this->name = name; }
	string getName() { return name; }
	string getTel() { return tel; }
	void set(string name, string tel) {
		this->name = name;
		this->tel = tel;
	}
};

#endif