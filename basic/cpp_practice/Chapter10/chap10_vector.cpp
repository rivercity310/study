#include <iostream>
#include <vector>
#include <string>

using std::string;

void chap10_vector() {
	std::vector<int> v;

	v.push_back(1);
	v.push_back(2);
	v.push_back(3);

	for (int i = 0; i < v.size(); i++)
		std::cout << v.at(i) << " ";
	std::cout << std::endl;

	v[0] = 10;
	int m = v[2];
	v.at(2) = 5;

	for (int i = 0; i < v.size(); i++)
		std::cout << v[i] << " ";
	std::cout << std::endl;
}

void chap10_vector2() {
	std::vector<string> sv;
	string name;

	std::cout << "이름: ";
	for (int i = 0; i < 5; i++) {
		std::cout << i + 1 << " : ";
		std::getline(std::cin, name);
		sv.push_back(name);
	}

	name = sv.at(0);
	for (int i = 1; i < sv.size(); i++) 
		if (name < sv[i]) name = sv[i];

	std::cout << "사전에서 가장 뒤에 나오는 이름은 " << name << std::endl;
}