import numpy as np

'''
1. 행렬 A의 역행렬을 구한 다음 행렬 A와 구한 역행렬의 곱이 단위행렬 I인지 확인하라.
2. 난수로 3 by 3 행렬 B를 만들고 그 역행렬을 구한 후, B와 B의 역행렬을 곱한 결과를 출력하라.
3. 다음 행렬 C와 D에 대한 행렬방정식 Cx = D의 해를 역행렬을 이용하여 구한다음 해가 맞는지 확인하라.
'''


def pprint(msg, A):
    print("--- ", msg, " ---")
    (n, m) = A.shape
    for i in range(0, n):
        line = ""
        for j in range(0, m):
            line += "{0:.2f}".format(A[i, j]) + "\t"
        print(line)
    print("")


# 1번
A = np.array([[1., 2.], [3., 4.]])
pprint("A", A)

# matrix_power()를 사용한 역행렬 계산
Ainv1 = np.linalg.matrix_power(A, -1)
pprint("linalg.matrix_power(A, -1) => Ainv1", Ainv1)

# inv()를 사용한 역행렬 계산
Ainv2 = np.linalg.inv(A)
pprint("np.linalg.inv(A) => Ainv2", Ainv2)

# 행렬 A와 A의 역행렬 곱
pprint("A x Ainv1", np.matmul(A, Ainv1))
pprint("A x Ainv2", np.matmul(A, Ainv2))


# 2번
B = np.random.rand(3, 3)         # 난수를 이용한 3x3 행렬 B 생성
pprint("B", B)
Binv = np.linalg.inv(B)
pprint("Binv", Binv)
pprint("B x Binv", np.matmul(B, Binv))


# 3번 (Cx = D의 해 계산)
C = np.array([[5, 3, 2, 1], [6, 2, 4, 5], [7, 4, 1, 3], [4, 3, 5, 2]])
D = np.array([[4], [2], [5], [1]])
x = np.matmul(np.linalg.inv(C), D)
pprint("x", x)
pprint("C * x", np.matmul(C, x))
