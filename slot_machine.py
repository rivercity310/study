import random

'''
두개 맞으면 200점, 세개 맞으면 300점, 모두 다르면 -100점
'''


def slot_machine():
    point = 0
    while (True):
        a = []
        for i in range(3):
            a.append(random.randint(1, 9))

        print("a = ", a)

        check = 0
        for i in range(len(a)):
            for j in range(i + 1, len(a)):
                if a[i] == a[j]:
                    check += 1

        print("check = ", check)

        if check == 3:
            point += 1000
        elif check == 1:
            point += 200
        else:
            point -= 100

        print("현재 포인트 = ", point)

        retry = int(input("다시 (1/0): "))
        if retry == 0:
            exchange_point(point)
            break


# 1 point = 1 dollor
def exchange_point(point):
    ratio = 1295
    print("환전 금액은 ${}입니다.".format(point))
    print("한화 {}원".format(point * ratio))


if __name__ == "__main__":
    slot_machine()
