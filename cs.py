import random


def print_ary():
    for i in range(10):
        print("{0}번째 출력".format(i + 1), end=" ")
    print()


def data_structure():
    a = (1, 2, 3, 4, 5, 6, 1)   # tuple
    b = {1, 2, 3, 4, 5, 6, 1}   # set
    c = [1, 2, 3, 4, 5, 6, 1]   # list
    d = {"a": 1, "b": 2}        # dictionary

    print(a)
    print(b)
    print(c)
    print(d)
    print(d["a"])


def string_fn():
    prince = "my Life is very monotonous, the fox said"
    print(prince.count("a"))

    ary = []
    for i in range(97, 97 + 27):
        ary.append(chr(i))

    for char in ary:
        print(char + ":" + str(prince.count(char)), end="\t")
    print()

    print(prince.capitalize())
    print(prince.upper())
    print(prince.lower())
    print(prince)          # prince(원본)은 바뀌지 않는다.

    string = " hwang seung su"
    print(string.strip())


def list_fn():
    a = [5, 4, 3, 2, 1]
    a.sort()        # 원본이 바뀜
    print(a)

    a.reverse()
    print(a)

    print(a.count(5))   # 5의 개수

    a.remove(3)         # 3 제거
    print(a)

    b = a.pop(3)        # a[3] pop
    print(a)
    print(b)


def rand_fn():
    for i in range(10):
        star = random.randint(1, 10)        # 1 ~ 10사이 랜덤 정수
        print(star, end="\t")


if __name__ == "__main__":
    rand_fn()
