from collections import deque
from collections import Counter

# collections 라이브러리는 코테에서 유용하게 사용되는 자료구조인 deque과 Counter를 포함한다.


# 보통 파이썬에서는 deque을 사용해 큐를 구현한다.
# (삽입: append(), 팝: popleft() 두 함수만 이용)
data = deque([2, 3, 4])
data.appendleft(1)
data.append(5)

print(data)
print(list(data))


# Counter는 iterable 객체에서 해당 원소의 등장 횟수를 세는 기능을 제공한다.
# (원소별 등장 횟수를 세는 기능이 필요할 때 사용)
counter = Counter(["red", "blue", "red", "green", "blue", "blue"])

print(counter["blue"])
print(counter["green"])
print(dict(counter))
