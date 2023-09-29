t = int(input())
def mult(x, y):    
    result = [[0, 0], [0, 0]]
    for i in range(len(x)):
    # iterate through columns of Y
        for j in range(len(y[0])):
            # iterate through rows of Y
            for k in range(len(y)):
                result[i][j] += (x[i][k] * y[k][j]) % m
                result[i][j] %= m
    return result
def bin_pow(x, p, m):
    if p == 1: return x
    if p % 2 == 0: return bin_pow(mult(x, x), p//2, m)
    return mult(x, bin_pow(mult(x, x), p//2, m))
for q in range(t):     
    n, k, m = input().split()
    n, k, m = int(n), int(k), int(m)
    if n == 1:
        print(k % m)
        continue
    matrix = [
        [k - 1, k - 1],
        [1, 0]
    ]
    print(bin_pow(matrix, n, m)[0][0])