#include <iostream>
#include <string>
using namespace std;
class Console {
public:
    static void start();
    static int menu();
    static int time();
    static int num();
    static string name();
};
void Console::start() {
    cout << "***** 한성항공에 오신것을 환영합니다 *****" << endl;
}
int Console::menu() {
    cout << endl << endl;
    cout << "예약:1, 취소:2, 보기:3, 끝내기:4>> ";
    int n;
    cin >> n;
    return n;
}
int Console::time() {
    cout << "07시:1, 12시:2, 17시:3>> ";
    int t;
    cin >> t;
    return t;
}
int Console::num() {
    cout << "좌석 번호>> ";
    int num;
    cin >> num;
    return num;
}
string Console::name() {
    cout << "이름 입력>> ";
    string name;
    cin >> name;
    return name;
}
class Seat {
    string seatName;
public:
    Seat() { seatName = "---"; }
    string getName() { return seatName; }
    void setName(string name) { seatName = name; }
};
class Schedule {
    Seat* seat;
    string time;
public:
    Schedule() { seat = new Seat[8]; }
    ~Schedule() { delete[] seat; }
    void setTime(string t) { time = t; }
    void show() {
        cout << time << " :";
        for (int i = 0; i < 8; ++i)
            cout << seat[i].getName() << "\t";
        cout << endl;
    }
    void reservationSeat(int num, string name) {
        seat[num - 1].setName(name);
    }
    void cancleSeat(int num) {
        seat[num - 1].setName("---");
    }
};
class AirlineBook {
    Schedule* schedule;
public:
    AirlineBook() {
        schedule = new Schedule[3];
        schedule[0].setTime("07시");
        schedule[1].setTime("12시");
        schedule[2].setTime("17시");
    }
    ~AirlineBook() { delete[] schedule; }
    void reservation(int t) {
        schedule[t - 1].show();
        int num = Console::num();
        string name = Console::name();
        schedule[t - 1].reservationSeat(num, name);
    }
    void cancle(int t) {
        schedule[t - 1].show();
        int num = Console::num();
        schedule[t - 1].cancleSeat(num);
    }
    void showAll() {
        for (int i = 0; i < 3; ++i)
            schedule[i].show();
    }
};

void chap8_Ex9() {
    AirlineBook airlinebook;
    Console::start();
    while (true) {
        int n = Console::menu();

        switch (n) {
        case 1:
        {
            int t = Console::time();
            airlinebook.reservation(t);
            break;
        }
        case 2:
        {
            int t = Console::time();
            airlinebook.cancle(t);
            break;
        }
        case 3:
            airlinebook.showAll();
            break;
        case 4:
            cout << "예약 시스템을 종료합니다." << endl;
            return;
        default:
            std::cout << "잘못된 입력입니다" << std::endl;
            break;
        }
    }
}