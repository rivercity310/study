import sys
from collections import deque

# AC (덱) - 미해결 (시간초과...)

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    
    func_list = list(input().rstrip())
    n = int(input())
    
    # arr = deque(input().rstrip().removesuffix("]").removeprefix("[").split(","))
    arr = deque(input().rstrip()[1:-1].split(","))

    if arr[0] == '':
        print("error")
        continue
    
    isError = False
    rvs = False

    for func in func_list:
        if func == 'R':
            rvs = not rvs

        elif func == 'D':
            if len(arr) == 0:
                isError = True
                break
            
            if rvs:
                arr.pop()
            else:
                arr.popleft()            

      
    # 에러가 발생한 경우
    if isError:
        print("error")
    else:
        if rvs:
            arr.reverse()

        print("[", end="")
        for i, val in enumerate(arr):
            print(val, end="")
            if i < len(arr) - 1:
                print(",", end="")
        print("]", end="")