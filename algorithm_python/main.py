import random

N = 10000000

a = 0
b = 0
c = 0

for i in range(N):
    k = random.randint(1, 3)

    if k == 1:
        a += 1
    elif k == 2:
        b += 1
    else:
        c += 1

s = a + b + c

print(f"[ 가위바위보 {format(N, ',d')}번 테스트 결과 ]" )
print(f" -> 가위 : {format(a, ',d')}회 ({(a / s) * 100}%)")
print(f" -> 바위 : {format(b, ',d')}회 ({(b / s) * 100}%)")
print(f" -> 보 :   {format(c, ',d')}회 ({(c / s) * 100}%)")
