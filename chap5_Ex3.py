'''
크래머 공식을 이용하여 연립선형방정식 해 구하기
'''

import numpy as np


# 크래머 공식을 이용한 연립선형방정식 Ax = b 풀이
# 행렬 A의 i열을 b로 대체한 행렬을 M이라 할 때
# x_i = det(M_i) / det(A)
def solveByCramer(A, b):
    x = np.zeros(len(b))
    M = np.copy(A)

    for i in range(0, len(b)):
        for j in range(0, len(b)):
            M[j, i] = b[j]

            if i > 0:
                M[j, i - 1] = A[j, i - 1]

        x[i] = np.linalg.det(M) / np.linalg.det(A)

    return x


if __name__ == "__main__":
    A = np.array([[2, -1, 5, 1], [3, 2, 2, -6], [1, 3, 3, -1], [5, -2, -3, 3]])
    b = np.array([[-3], [-32], [-47], [49]])

    x = solveByCramer(A, b)
    print("A\n", A)
    print("b\n", b)
    print("x^T\n", x)
