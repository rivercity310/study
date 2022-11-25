'''
numpy를 이용하여 행렬 A와 벡터 v를 작성하고
이를 출력하는 프로그램 작성
'''

import numpy as np

A = np.array([[1, 2, 3],
             [4, 5, 6],
             [7, 8, 9]])

v = np.array([[1],
             [2],
             [3]])

print("A =", A)
print("v =", v)

print("A.shape =", A.shape)  # 행렬 A의 크기
print("v.shape =", v.shape)  # 벡터 v의 크기

w = np.array([1, 2, 3])
print("w =", w)
print("w.shape =", w.shape)

B = np.array([[1, 2, 3], [4, 5, 6]])
print("B =", B)
print("B.shape =", B.shape)
