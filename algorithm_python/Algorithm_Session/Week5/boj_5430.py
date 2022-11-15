import sys
from collections import deque

# AC (덱) - 미해결 (시간초과...)

input = sys.stdin.readline

t = int(input())

while t:
    func_list = list(input().rstrip())
    n = int(input())
    
    raw_arr = input().rstrip().split(",")
    raw_arr[0] = raw_arr[0].removeprefix("[")
    raw_arr[-1] = raw_arr[-1].removesuffix("]")

    if raw_arr[0] == '':
        print("error")
        continue

    arr = deque(raw_arr)
    flag = True

    for func in func_list:
        if func == 'R':
            arr.reverse()
        
        elif func == 'D':
            if len(arr) == 0:
                flag = False
                break

            arr.popleft()


    # 에러가 발생한 경우
    if not flag:
        print("error")
    else:
        ans = "["
        for i in range(len(arr)):
            ans += arr[i]
            if i != len(arr) -1:
                ans += ","
        ans += "]"

        print(ans)

    t -= 1