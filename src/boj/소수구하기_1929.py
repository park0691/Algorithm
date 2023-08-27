import sys, math
M, N = map(int, sys.stdin.readline().split())

prime = [True for _ in range(N + 1)]
prime[1] = False

for i in range(2, int(math.sqrt(N)) + 1):
    if not prime[i]:
        continue
    j = i * 2
    while j <= N:
        prime[j] = False
        j += i

for i in range(M, N + 1):
    if prime[i]:
        print(i)