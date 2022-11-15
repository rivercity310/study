#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

static int binary_search(vector<int>& arr, int n) {
    int left = 0;
    int right = *max_element(arr.begin(), arr.end()) + 1;
    
    while (left < right) {
        int mid = (left + right) / 2;

        int cnt = 0;
        for (int val : arr)
            cnt += val / mid;
        
        if (cnt < n) right = mid;
        else left = mid + 1;
    }

    return left - 1;
}

int main() {
    int k, n;
    cin >> k >> n;

    vector<int> arr(k);
    for (int i = 0; i < k; i++)
        cin >> arr[i];

    /* Make Same Length Cable n Pieces */
    int ans = binary_search(arr, n);
    cout << ans;
}