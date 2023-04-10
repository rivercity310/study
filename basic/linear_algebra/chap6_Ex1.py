import numpy as np

'''
방향과 크기가 주어진 R^2 공간의 두 힘을 결합한 힘의 방향과 크기를 구하라
(30도 방향으로 100N, 60도 방향으로 120N)
'''


# 주어진 크기와 방향에 대응하는 벡터 생성
def getVector(mag, deg):
    vec = np.zeros(2)

    # 1 라디안(호도법: 호의 길이가 1이 되는 각, 약 57.3도) = 180 / PI
    vec[0] = mag * np.cos(deg * np.pi / 180)
    vec[1] = mag * np.sin(deg * np.pi / 180)

    return vec


# 벡터의 크기와 방향 계산
def getMagDeg(vec):
    mag = np.sqrt(vec[0] * vec[0] + vec[1] * vec[1])
    deg = np.arctan(vec[1] / vec[0]) * (180 / np.pi)

    return mag, deg


if __name__ == "__main__":
    F1 = getVector(100, 30)
    F2 = getVector(120, 60)

    print("F1: ", F1)
    print("F2: ", F2)

    Fsum = F1 + F2
    print("Fsum: ", Fsum)

    (magn, angle) = getMagDeg(Fsum)

    print("결합한 힘의 크기: %.2f" % magn)
    print("결합한 힘의 방향: %.2f" % angle)
