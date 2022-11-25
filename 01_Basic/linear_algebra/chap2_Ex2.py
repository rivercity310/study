# 가우스-조단 소거법을 수행하는 함수를 정의하고, 다음 연립선형방정식의 해를 구하라
# - 2x + 2y + 4z = 18
# - x + 3y + 2z = 13
# - 3x + y + 3z = 14

import numpy as np

# 행렬 A를 출력하는 함수


def pprint(msg, A):
    print("-----", msg, "-----")
    (n, m) = A.shape
    for i in range(0, n):
        line = ""
        for j in range(0, m):
            line += "{0:.2f}".format(A[i, j]) + "\t"
            if (j == n - 1):
                line += "| "
        print(line)
    print("")


# 가우스 - 조단 소거법을 수행하는 함수
def gauss(A):
    (n, m) = A.shape

    for i in range(0, min(n, m)):
        # 1번째 열에서 절댓값이 최대인 성분의 행 선택
        maxEl = abs(A[i, i])
        maxRow = i
        for k in range(i + 1, n):
            if (abs(A[k, i]) > maxEl):
                maxEl = abs(A[k, i])
                maxRow = k

        # 현재 1번째 행과 최댓값을 갖는 행 maxRow를 교환
        for k in range(i, m):
            temp = A[maxRow, k]
            A[maxRow, k] = A[i, k]
            A[i, k] = temp

        # 추축성분(피벗)을 1로 만들기
        piv = A[i, i]
        for k in range(i, m):
            A[i, k] = A[i, k] / piv

        # 현재 i번째 열의 i번째 행을 제외한 모든 성분을 0으로 만들기
        for k in range(0, n):
            if k != i:
                c = A[k, i] / A[i, i]
                for j in range(i, m):
                    if i == j:
                        A[k, j] = 0
                    else:
                        A[k, j] = A[k, j] - c * A[i, j]

        # 중간과정 출력
        pprint(str(i + 1) + "번째 반복", A)

    # Ax = b의 해 반환
    x = np.zeros(m - 1)
    for i in range(0, m - 1):
        x[i] = A[i, m - 1]

    return x


if __name__ == "__main__":
    # 주어진 연립선형방정식에 대한 첨가행렬
    # A = np.array([[2., 2., 4., 18.], [1., 3., 2., 13.], [3., 1., 3., 14.]])
    # A = np.array([[1., -1., 0., 0., 100.], [0., 1., -1., 0., 150.],
    #             [0., 0., 1., -1., 30.], [-1., 0., 0., 1., 20.]])
    A = np.array([[1., -1., 1., 0.], [3., 4., 0., 3.], [0., 4., 2., 2.]])

    pprint("주어진 문제", A)
    x = gauss(A)

    # 출력 생성
    (n, m) = A.shape
    line = "해:\t"
    for i in range(0, m-1):
        line += "{0:.2f}".format(x[i]) + "\t"

    print(line)
