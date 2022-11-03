import sys

input = sys.stdin.readline

n = int(input())

s = set()

for _ in range(n):
    oper = input().split()

    op = oper[0]

    if op != "all" and op != "empty":
        num = int(oper[1])

        if op == "add":
            s.add(num)

        elif op == "remove":
            s.discard(num)

        elif op == "check":
            s2 = set()
            s2.add(num)
            if s.isdisjoint(s2):
                print(0)
            else:
                print(1)

        elif op == "toggle":
            s2 = set()
            s2.add(num)
            if s.isdisjoint(s2):
                s.add(num)
            else:
                s.discard(num)

    else:
        if op == "all":
            s.clear()
            for i in range(1, 21):
                s.add(i)

        elif op == "empty":
            s.clear()
        