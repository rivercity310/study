#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#define swap(type, a, b) do {type t = a; a = b; b = t; } while(0)
#define MAX 7

static int cnt = 1;

static void print_arr(int* arr, int start, int end) {
    for (int i = start; i <= end; i++)
        printf("%-5d", arr[i]);
    putchar('\n');
}

static void quick_sort(int* arr, int start, int end) {
    if (start >= end)
        return;

    int pivot = (start + end) / 2;
    int left = start + 1;
    int right = end;

    printf("------------ 단계 %d ----------\n", cnt++);
    printf("start : %d, end : %d, arr[pivot] : %d\n", start, end, arr[pivot]);

    while (left <= right) {
        while (arr[pivot] >= arr[left] && left <= end)
            left++;

        while (arr[pivot] <= arr[right] && right > start)
            right--;

        // 엇갈렸으면 pivot과 right 교환
        if (left > right) {
            swap(int, arr[pivot], arr[right]);
            printf("arr[pivot]과 arr[right]를 교환: %d <--> %d\n", arr[pivot], arr[right]);
        }
        else {
            swap(int, arr[left], arr[right]);
            printf("arr[left]와 arr[right]를 교환: %d <--> %d\n", arr[left], arr[right]);
        }
    }

    printf("전체 배열\t");
    print_arr(arr, 0,  MAX - 1);

    printf("%d 기준으로 왼쪽으로 분할\t", arr[right]);
    print_arr(arr, 0, right - 1);
    printf("%d 기준으로 오른쪽으로 분할\t", arr[right]);
    print_arr(arr, right + 1, end);
    puts("-----------------------------\n");

    // right 위치에 pivot이 존재하므로 right를 기준으로 분할
    quick_sort(arr, 0, right - 1);
    quick_sort(arr, right + 1, end);
}

void quick_sort_test() {
    srand(time(NULL));
    int* arr = (int*)malloc(sizeof(int) * MAX);

    for (int i = 0; i < MAX; i++)
        arr[i] = rand() % 100;

    puts("[ 생성된 배열 ]");
    print_arr(arr, 0, MAX - 1);

    putchar('\n');

    quick_sort(arr, 0, MAX - 1);

    puts("[ 정렬된 배열 ]");
    print_arr(arr, 0, MAX - 1);

    free(arr);
}