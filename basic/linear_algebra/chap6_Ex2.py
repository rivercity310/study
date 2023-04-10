import numpy as np

'''
두 벡터의 사잇각과 A의 B 위로의 정사영 proj_A B 구하기
정사영은 (A dot A / B dot B)B 로 구한다
'''


# 두 벡터의 사잇각 계산
def angle2vectors(v, w):
    vnorm = np.linalg.norm(v)
    wnorm = np.linalg.norm(w)

    vwdot = np.dot(v.T, w)     # v.T = 열벡터 v를 행벡터로 전치, np.dot 내적
    angle = np.arctan(vwdot / (vnorm * wnorm)) * 360 / np.pi

    # vwdot / (vnorm * wnorm) = 두 벡터 v와 w가 이루는 사이각의 코사인값

    return angle


# 정사영
def orthProj(u, x):
    xu_dot = np.dot(x.T, u)
    uu_dot = np.dot(u.T, u)

    proj_ux = (xu_dot / uu_dot) * u
    return proj_ux


if __name__ == "__main__":
    A = np.array([[2], [4], [1]])
    B = np.array([[1], [-1], [3]])

    angle = angle2vectors(A, B)
    proj_AB = orthProj(B, A)

    print("A와 B의 사잇각 : ", angle)
    print("[A의 b 위로의 정사영]\n", proj_AB)
