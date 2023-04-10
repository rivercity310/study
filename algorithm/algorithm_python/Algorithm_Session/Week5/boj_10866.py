import sys

input = sys.stdin.readline

n = int(input())

deque = []

for _ in range(n):
    tmp = input().split()

    # push 명령어인 경우
    if (tmp[0].startswith("push")):
        a = tmp[0]
        b = int(tmp[1])

        if a == "push_front":
            deque.insert(0, b)

        elif a == "push_back":
            deque.append(b)
    
    # 나머지 명령어인 경우
    else:
        a = tmp[0]

        if a == "pop_front":
            if len(deque) == 0:
                print(-1)
            else:
                print(deque.pop(0))

        elif a == "pop_back":
            if len(deque) == 0:
                print(-1)
            else:
                print(deque.pop())

        elif a == "size":
            print(len(deque))
        
        elif a == "empty":
            if len(deque) == 0:
                print(1)
            else:
                print(0)

        elif a == "front":
            if len(deque) == 0:
                print(-1)
            else:
                print(deque[0])
        
        elif a == "back":
            if len(deque) == 0:
                print(-1)
            else:
                print(deque[-1])
                

