import numpy as np

'''
점 A를 포함하는 법선벡터가 W인 평면과 점 P 사이의 거리 계산하기
'''


# 평면과 점 사이의 거리 = abs(AP dot W) / norm(W)
def distPt2Pl(A, W, P):
    num = np.dot((P - A).T, W)
    deno = np.linalg.norm(W)

    val = np.absolute(num) / deno
    return val


if __name__ == "__main__":
    A = np.array([2, 3, 4])
    W = np.array([1, 2, 3])
    P = np.array([0, 1, 2])

    print("거리: ", distPt2Pl(A, W, P))
