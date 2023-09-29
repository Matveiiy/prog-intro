import random
def stress(n, adiap, bdiap):
    arr = []
    for i in range(n): arr.append([random.randint(adiap[0], adiap[1]), random.randint(bdiap[0], bdiap[1])])
    arr.sort()
    ans = solve(arr)
    if ans[0] != -1: return
    for i in range(n):
        for j in range(n):
            if i == j: continue
            if i > j: continue
            if arr[i][0] < arr[j][0] and arr[i][1] < arr[j][1]:
                print("ERROR: pair not found\n")
                print(arr)
                raise RuntimeError()
def check(arr, n, l, j):
    for i in range(j):
        if arr[i][0] < arr[j][0] and arr[i][1] < arr[j][1]:
            return [i, j]
    raise RuntimeError("check failed")
def solve(arr):
    n = len(arr)
    arr.sort()
    prefix = []
    cur_prefix = 10 ** 9
    for i in range(n):
        cur_prefix = min(cur_prefix, arr[i][1])
        prefix.append(cur_prefix)
    l = n - 1
    for i in range(n - 1, -1, -1):
        while l >= 0 and arr[l][0] == arr[i][0]: l -= 1
        if l >= 0 and arr[i][1] > prefix[l]:
            return check(arr, n, l, i)
    return [-1,-1]

#print(solve([[1, 4], [2, 2], [2, 3],[3, 2]]))
for i in range(10000):
    stress(1000, [-20, 20], [-20, 20])
    if i % 1000 == 0: print("TEST: ", i)
