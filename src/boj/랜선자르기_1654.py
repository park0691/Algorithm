import sys

input = sys.stdin.readline

K, N = map(int, input().split())
arr = []
left = 1
right = -1
ans = -1

for _ in range(K):
    n = int(input())
    arr.append(n)
    right = max(right, n)

while left <= right:
    mid = (left + right) // 2

    cnt = 0
    for n in arr:
        cnt += (n // mid)

    if cnt >= N:
        left = mid + 1
        ans = max(ans, mid)
    else:
        right = mid - 1

print(ans)
