from bisect import bisect_right, bisect_left

# 이진 탐색을 쉽게 구현할 수 있도록 bisect 라이브러리 이용하기
# bisect 라이브러리는 "정렬된 배열"에서 특정 원소를 찾을 때 매우 효과적으로 사용된다.


# bisect_left(a, x), bisect_right(a, x) => O(logN)
# - 정렬된 순서를 유지하면서 리스트 a에 데이터 x를 삽입할 가장 왼쪽(오른쪽) 인덱스를 반환
a = [1, 2, 4, 4, 6, 8]
x = 4

print(bisect_left(a, x))
print(bisect_right(a, x))


# 또한 "정렬된 리스트"에서 값이 특정 범위에 속하는 원소의 개수를 구하는 경우에 사용할 수 있다.
# 값이 [left_val, right_val]인 데이터의 개수를 구하는 함수
def count_by_range(a, left_val, right_val):
    r_index = bisect_right(a, right_val)
    l_index = bisect_left(a, left_val)

    return r_index - l_index


# 값이 4인 데이터 개수 출력
print(count_by_range(a, 4, 4))

# 값이 [-1, 5] 범위에 있는 데이터 개수
print(count_by_range(a, -1, 5))
