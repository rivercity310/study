#include <iostream>
#include <map>
#include <string>

using std::string;

void chap10_map() {
	std::map<string, string> dic;

	dic.insert(std::make_pair("love", "사랑"));
	dic.insert(std::make_pair("apple", "사과"));
	dic["cherry"] = "체리";

	std::cout << "저장된 단어 개수 " << dic.size() << std::endl;
	string eng;

	while (true) {
		std::cout << "찾고 싶은 단어 >> ";
		std::getline(std::cin, eng);
		if (eng == "exit") break;

		if (dic.find(eng) == dic.end())
			std::cout << "없음" << std::endl;
		else
			std::cout << dic[eng] << std::endl;
	}
}