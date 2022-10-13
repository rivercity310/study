# 정수 N이 입력되면 00시 00분 00초부터 N시 59분 59초까지의 모든 시각 중에서
# 3이 하나라도 포함되는 모든 경우의 수 구하기

N = int(input())

cnt = 0
cnt2 = 0

for hour in range(N + 1):
    for min in range(60):
        for sec in range(60):
            if hour // 10 == 3 or hour % 10 == 3:
                cnt += 1

            elif min // 10 == 3 or min % 10 == 3:
                cnt += 1

            elif sec // 10 == 3 or sec % 10 == 3:
                cnt += 1

            # 또는 다음과 같이 간단하게 조건을 체크할 수 있다.
            if '3' in str(hour) + str(min) + str(sec):
                cnt2 += 1


print(cnt)
print(cnt2)
