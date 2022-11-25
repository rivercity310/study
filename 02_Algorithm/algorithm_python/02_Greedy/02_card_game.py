import sys

# [숫자 카드 게임]
'''
여러 개의 숫자 카드 중에서 조건에 맞는 가장 높은 숫자가 쓰인 카드 한장을 뽑는 게임
단, 룰은 다음과 같다.

1. 숫자가 쓰인 카드들이 N x M 형태로 놓여있다.
2. 먼저 뽑고자 하는 카드가 포함된 행을 선택한다.
3. 그다음 선택된 행에 포함된 카드들 중 가장 숫자가 낮은 카드를 뽑는다.
4. 따라서 처음 행을 선택할 때, 이후 해당 행에서 가장 숫자가 낮은 카드를 뽑을 것을 고려하여
   최종적으로 가장 높은 숫자의 카드를 뽑을 수 있도록 전략을 세워야 한다.
'''

# Idea
# "각 행마다 가장 작은 수를 찾은 뒤, 그 숫자 중에서 가장 큰 수를 찾는다"

N, M = map(int, sys.stdin.readline().rstrip().split())

# N x M 행렬 입력받기
arr = list()
for _ in range(N):
    row = list(map(int, sys.stdin.readline().rstrip().split()))
    arr.append(row)

min_list = list()
for i in range(N):
    min_list.append(min(arr[i]))

print(max(min_list))


# 방법 2
result = 0
for _ in range(N):
    data = map(int, sys.stdin.readline().rstrip().split())
    min_value = min(data)
    result = max(result, min_value)

print(result)
