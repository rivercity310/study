'''
행렬 곱은 numpy의 matmul() 함수를, 행렬의 거듭제곱은 linalg.matrix_power() 함수를, 블록행렬을 생성할 때는 block() 함수를 사용한다.
'''

import numpy as np


# 행렬 A를 출력하는 함수
def pprint(msg, A):
    print("--- ", msg, " ---")
    (n, m) = A.shape

    for i in range(0, n):
        line = ""
        for j in range(0, m):
            line += "{0:.2f}".format(A[i, j]) + "\t"
        print(line)
    print("")


A = np.array([[1., 2.], [3., 4.]])
B = np.array([[2., 2., ], [1., 3.]])
C = np.array([[4., 5., 6., ], [7., 8., 9.]])
v = np.array([[10.], [20.]])

# 행렬의 합차
pprint("A + B", A+B)
pprint("A - B", A-B)

# 행렬의 스칼라배
pprint("3A", 3*A)
pprint("2v", 2*v)

# 행렬의 곱
pprint("matmul(A, B)", np.matmul(A, B))
pprint("matmul(A, C)", np.matmul(A, C))
pprint("A * v", A * v)      # 행렬과 벡터의 곱 Av

# 행렬의 거듭제곱
pprint("matrix_power(A, 2)", np.linalg.matrix_power(A, 2))
pprint("matrix_power(A, 3)", np.linalg.matrix_power(A, 3))

# 행렬의 성분별 곱셈, 나눗셈, 거듭제곱
pprint("A * B", A * B)
pprint("A / B", A / B)
pprint("A ** 2", A ** 2)

# 전치행렬
pprint("A.T", A.T)
pprint("v.T", v.T)

# 대각행렬 diag(1, 2, 3) 생성
M = np.diag([1, 2, 3])
pprint("diag(1, 2, 3)", M)

D11 = np.array([[1, 2], [3, 4]])
D12 = np.array([[5], [6]])
D21 = np.array([[7, 7]])
D22 = np.array([[8]])
D = np.block([[D11, D12], [D21, D22]])
pprint("block matrix", D)
