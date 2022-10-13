def print_Ary(A):
    for i in range(len(A)):
        print("A[{0}]: {1}".format(i, A[i]), end=" ")
    print()


def bubble_sort(A):
    k = 0
    n = len(A)

    for i in range(n):
        exchg = 0
        for j in range(n - 1, i, -1):
            if (A[j - 1] > A[j]):
                temp = A[j - 1]
                A[j - 1] = A[j]
                A[j] = temp

                exchg += 1

        if (exchg == 0):
            break


A = []
for i in range(5):
    a = int(input("A[{0}]: ".format(i)))
    A.append(a)

print_Ary(A)
bubble_sort(A)
print_Ary(A)
