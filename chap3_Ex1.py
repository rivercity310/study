'''
vstack 함수는 주어진 벡터들을 행으로 갖는 행렬을 만들고, column_stack 함수는 주어진 벡터들을 열로 갖는 행렬을 만든다.
'''

import numpy as np

if __name__ == "__main__":
    print("벡터의 결합에 의한 행렬 생성")
    v1 = np.array([1, 2, 3])
    v2 = np.array([4, 5, 6])
    v3 = np.array([7, 8, 9])

    A = np.vstack([v1, v2, v3])
    print("A = ", A)

    B = np.column_stack([v1, v2, v3])
    print("B = ", B)

    C = np.array([[1, 2], [3, 4], [5, 6]])
    print("C = ", C)

    D = np.column_stack([C, v3])
    print("D = ", D)

    print("행렬의 성분 접근")
    E = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]])

    print("E[0, 3] = ", E[0, 3])   # 1행 4열
    print("E[1, 2] = ", E[1, 2])   # 2행 3열

    print("E[0:2, 2] = ", E[0:2, 2])         # E의 1~2행의 3열에 해당하는 부분행렬
    print("E[0:2, 2:4] = ", E[0:2, 2:4])     # E의 1~2행의 3~4열에 해당하는 부분행렬
    print("E[2, :] = ", E[2, :])             # E의 3행에 해당하는 부분행렬

    print("성분의 변경")
    print("E = ", E)

    print("E[0, 0] = ", E[0, 0])
    E[0, 0] = -1
    print(E)
    print("E[0, 0] = ", E[0, 0])
