import math

# math 라이브러리는 수학적인 기능을 제공하는 라이브러리이다.
# 팩토리얼, 제곱근, 최대공약수(GCD) 등

print(math.factorial(5))
print(math.sqrt(10))
print(math.pi)
print(math.e)


def lcm2(a, b):
    return a * b // math.gcd(a, b)


print(math.gcd(21, 14))      # 최대 공약수
print(math.lcm(21, 14))      # 최소 공배수
print(lcm2(21, 14))
