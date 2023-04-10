import random

print("%d %s %c" % (10, "황승수", 97))
print("{} {} {}".format(10, "황승수", 65))

for i in range(97, 97 + 26):
    print("%c" % i, end=" ")
print()

for i in range(65, 65 + 26):
    print("{}".format(chr(i)), end=" ")


def print_ary(A):
    count = 0
    n = len(A)
    for i in range(n):
        count += 1
        print("%-5d" % A[i], end="")

        if count % 10 == 0:
            print()
    print()


def bubble_sort(A):
    n = len(A)
    for i in range(n):
        for j in range(n - 1, 0, -1):
            if (A[j - 1] > A[j]):
                tmp = A[j - 1]
                A[j - 1] = A[j]
                A[j] = tmp

    return A


def lottery():
    A = list()

    while (len(A) < 6):
        num = random.randint(1, 45)

        flag = 1
        for j in range(len(A)):
            if (num == A[j]):
                flag = 0

        if (flag != 0):
            A.append(num)

    return A


def equal_ary(A, B, n):
    for i in range(n):
        if (A[i] != B[i]):
            return -1

    return 0


A = list()
for i in range(10):
    A.append(random.randint(0, 1000))

print("\n\n")
print("[정렬 전]")
print_ary(A)

bubble_sort(A)

print("[정렬 후]")
print_ary(A)


print("\n\n")
print("[이번주 로또 번호]")
LOTTO = lottery()
print_ary(LOTTO)

B = [1, 2, 3, 4, 5, 6]
while (True):
    LOTTO = bubble_sort(lottery())

    if (equal_ary(LOTTO, B, 6) == 0):
        print("맙소사.......")
        print("1, 2, 3, 4, 5, 6이 나오다니")
        print_ary(LOTTO)
        break
