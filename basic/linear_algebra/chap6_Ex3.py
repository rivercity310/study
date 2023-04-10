import numpy as np

'''
스칼라 삼중적의 절댓값 = 평행육면체의 부피
AB dot (AC x AD)
'''


# u dot (v x w)
def tripleProduct(u, v, w):
    M = np.zeros((3, 3))
    M[0:] = u
    M[1:] = v
    M[2:] = w

    val = np.linalg.det(M)
    print("M\n", M)
    print("val : ", val)

    return val


if __name__ == "__main__":
    A = np.array([1, 2, 3])
    B = np.array([0, 5, 2])
    C = np.array([2, 2, 4])
    D = np.array([2, 4, 1])

    u = B - A         # AB 벡터
    v = C - A         # AC 벡터
    w = D - A         # AD 벡터

    val = tripleProduct(u, v, w)
    print("부피 : ", np.absolute(val))
