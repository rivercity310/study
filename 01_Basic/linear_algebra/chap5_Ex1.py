import numpy as np

'''
임의의 크기의 정방행렬에 대한 행렬식을 계산하는 프로그램 작성
'''


# 행렬 A의 i행과 j열을 제거한 행렬 생성
def getMinorMatrix(A, i, j):
    n = len(A)
    M = np.zeros((n - 1, n - 1))

    for a in range(0, n - 1):
        k = a if (a < i) else a + 1
        for b in range(0, n - 1):
            l = b if (b < j) else b + 1
            M[a, b] = A[k, l]

    return M


# 행렬식 계산
def determinant(M):
    if (len(M) == 2):           # 2 by 2 행렬이라면
        return M[0, 0] * M[1, 1] - M[0, 1] * M[1, 0]

    # 여인수를 이용하여 행렬식 계산하기 [ C_ij = {-1 ^ (i + j)} * A_ij ]
    # determinant = (a_11 * c_11) + (a_12 * c_12) .....
    detVal = 0
    for c in range(len(M)):
        detVal += ((-1)**c) * M[0, c] * determinant(getMinorMatrix(M, 0, c))

    return detVal


if __name__ == "__main__":
    A = np.array([[-4, 0, 2, -1, 0], [1, 3, -3, -1, 4], [2, 0, 1, 3, 0],
                  [-2, 1, -3, -1, 5], [1, -5, 1, 0, 5]])
    print("<행렬 A>\n", A)
    print("det(A) = ", determinant(A))
    print("A = {0:.2f}".format(np.linalg.det(A)))

    print()

    B = np.array([[1, 3], [2, 4]])
    print("<행렬 B>\n", B)
    print("det(B) = ", determinant(B))
    print("B = {0:.2f}".format(np.linalg.det(B)))

    print()

    C = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
    print("<행렬 C>\n", C)
    print("det(C) = ", determinant(C))

    print("C = {0:.2f}".format(np.linalg.det(C)))
