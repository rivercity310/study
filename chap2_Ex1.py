'''
2 by 3 영행렬, 모든 성분이 1인 2 by 2 행렬, 모든 성분이 3인 3 by 2 행렬, 2 by 2 단위행렬을 만들고 출력하라
'''

import numpy as np

a = np.zeros((2, 3))   # 2 x 3 영행렬
print("a = ", a)

b = np.ones((2, 2))    # 모든 성분이 1인 2 x 2 행렬
print("b = ", b)

c = np.full((3, 2), 3)  # 모든 성분이 3인 3 x 2 행렬
print("c = ", c)

d = np.eye(2)           # 2 x 2 단위행렬
print("d = ", d)
