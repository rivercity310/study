import sys

# 괄호 문제
# Python에서 Stack은 list를 사용하거나 deque를 사용한다
# (from collections import deque)

input = sys.stdin.readline

T = int(input())
while T:
    # 문자열 입력시 rstrip()을 통해 \n 제거
    # 공백을 제거하지 않으면 반복문이 \n까지 한번 더 돌게 됨
    s = input().rstrip()
    stk = []

    flag = True
    for c in s:
        if c == '(':
            stk.append(c)
        else:
            if len(stk):
                stk.pop()
            else:
                flag = False
                break

    if len(stk) == 0 and flag:
        print("YES")
    else:
        print("NO")

    T -= 1
