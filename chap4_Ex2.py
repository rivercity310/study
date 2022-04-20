'''
임의의 크기의 정방행렬 A를 LU 분해하는 함수를 작성하고, Ax = b의 해를 구하는 프로그램 작성
[ 연산 순서 ]
Ax = b -> LUx = b -> Ly = b -> Ux = y 
'''

import numpy as np


def pprint(msg, A):
    print("--- ", msg, " ---")
    (n, m) = A.shape
    for i in range(0, n):
        line = ""
        for j in range(0, m):
            line += "{0:.2f}".format(A[i, j]) + "\t"
        print(line)
    print("")


# LU 분해 함수

def LU(A):
    (n, m) = A.shape

    # L, U 행렬 초기화
    L = np.zeros((n, n))
    U = np.zeros((n, n))

    # 행렬 L, U 계산 (두리틀 알고리즘)
    for i in range(0, n):
        for j in range(i, n):
            U[i, j] = A[i, j]
            for k in range(0, i):
                U[i, j] -= L[i, k] * U[k, j]

        L[i, i] = 1
        if i < n - 1:
            p = i + 1
            for j in range(0, p):
                L[p, j] = A[p, j]
                for k in range(0, j):
                    L[p, j] -= L[p, k] * U[k, j]
                L[p, j] /= U[j, j]

    return L, U


# LU 분해를 이용한 Ax = b 해 구하기
def LUSolver(A, b):
    L, U = LU(A)
    n = len(L)

    # Ly = b 계산
    y = np.zeros((n, 1))
    for i in range(0, n):
        y[i] = b[i]
        for k in range(0, i):
            y[i] -= y[k] * L[i, k]

    # Ux = y 계산
    x = np.zeros((n, 1))
    for i in range(n-1, -1, -1):
        x[i] = y[i]
        if i < n - 1:
            for k in range(i + 1, n):
                x[i] -= x[k] * U[i, k]

        x[i] /= float(U[i, i])

    return x


A = np.array([[5, 3, 2, 1], [6, 2, 4, 5], [7, 4, 1, 3], [4, 3, 5, 2]])
b = np.array([[4], [2], [5], [1]])

# 행렬 A의 LU 분해
L, U = LU(A)
pprint("A", A)
pprint("L", L)
pprint("U", U)

# LU 분해를 이용한 Ax = b의 해 구하기
x = LUSolver(A, b)
pprint("x", x)
