#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#define swap(type, a, b) do {type t = a; a = b; b = t; } while(0)

static void print_arr(int* arr, int n) {
    for (int i = 0; i < n; i++)
        printf("%-5d", arr[i]);
    putchar('\n');
}

static void selection_sort(int* arr, int n) {
    for (int i = n - 1; i > 0; i--) {
        int max_index = i;
        int max_value = -1e9;

        for (int j = 0; j <= i; j++) {
            if (arr[j] > max_value) {
                max_value = arr[j];
                max_index = j;
            }
        }

        printf("단계 %d: %d <---> %d\t\t", n - i, arr[max_index], arr[i]);
        print_arr(arr, n);
        swap(int, arr[max_index], arr[i]);
    }
}

void selection_sort_test() {
    int n;
    scanf("%d", &n);

    srand(time(NULL));
    int* arr = (int*)malloc(sizeof(int) * n);

    for (int i = 0; i < n; i++)
        arr[i] = rand() % 100;

    puts("[ 생성된 배열 ]");
    print_arr(arr, n);

    selection_sort(arr, n);

    puts("[ 정렬된 배열 ]");
    print_arr(arr, n);

    free(arr);
}