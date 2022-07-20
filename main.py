from itertools import product
from itertools import combinations_with_replacement
from collections import Counter
import math

# itertools - product, combinations_with_replacement
data = ["A", "B", "C"]

result = list(product(data, repeat=2))
print(len(result))
print(result)

result = list(combinations_with_replacement(data, 2))
print(len(result))
print(result)


# collections - Counter
data = Counter(["red", "blue", "red", "green", "blue", "blue"])
print(data["blue"])
print(data["green"])
print(dict(data))


# math
def lcm2(a, b):
    return a * b // math.gcd(a, b)


print(math.gcd(21, 14))      # 최대 공약수
print(math.lcm(21, 14))      # 최소 공배수
print(lcm2(21, 14))
