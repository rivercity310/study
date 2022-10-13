'''
수반행렬 adj(A)를 이용한 역행렬 계산 방법 구현하기
'''

import numpy as np


# 여인수 계산
def cofactor(A, i, j):
    (n, m) = A.shape
    M = np.zeros((n - 1, m - 1))

    for a in range(0, n - 1):
        k = a if (a < i) else a + 1
        for b in range(0, m - 1):
            l = b if (b < j) else b + 1
            M[a, b] = A[k, l]

    print("여인수 C[{0}, {1}] = {2:.2F}".format(
        i, j, (-1) ** (i + j) * np.linalg.det(M)))

    return (-1) ** (i + j) * np.linalg.det(M)


# 수반행렬을 이용한 A의 역행렬 계산
def inverseByAdjointMatrix(A):
    detA = np.linalg.det(A)
    (n, m) = A.shape
    adjA = np.zeros((n, m))

    for i in range(n):
        for j in range(m):
            adjA[j, i] = cofactor(A, i, j)

    print("수반행렬 adj(A)")
    print(adjA)

    # inverse A = (1 / det(A)) * adjA
    if detA != 0.0:
        return (1. / detA) * adjA
    else:
        return 0


# 전치행렬 구하기
def transpose_matrix(A):
    (n, m) = A.shape
    trans_mat = np.zeros((m, n))

    for i in range(n):
        for j in range(m):
            trans_mat[j, i] = A[i, j]

    return trans_mat


if __name__ == "__main__":
    A = np.array([[-4, 0, 2, -1, 0], [1, 3, -3, -1, 4],
                 [2, 0, 1, 3, 0], [-2, 1, -3, -1, 5], [1, -5, 1, 0, 5]])
    print("행렬 A")
    print(A)

    Ainv = inverseByAdjointMatrix(A)
    print("A inverse")
    print(Ainv)

    B = np.array([[1, 2], [3, 4]])
    print("행렬 B")
    print(B)

    Binv = inverseByAdjointMatrix(B)
    print("B inverse")
    print(Binv)

    B_transpose = transpose_matrix(B)
    print("B transpose")
    print(B_transpose)
    print(np.transpose(B))
