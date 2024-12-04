def solution(ss):
    n = len(ss)
    m = len(ss[0])
    ans = 0
    for i in range(n):
        for j in range(m):
            if ss[i][j] == 'X':
                if j+3 < m and ss[i][j:j+4] == "XMAS":
                    ans += 1
                if i+3 < n and ''.join(list(s[j] for s in ss[i:i+4])) == "XMAS":
                    ans += 1
                if i+3 < n and j+3 < m and ''.join(list(ss[i+k][j+k] for k in range(4))) == "XMAS":
                    ans += 1
                if i+3 < n and j-3 >= 0 and ''.join(list(ss[i+k][j-k] for k in range(4))) == "XMAS":
                    ans += 1
            if ss[i][j] == 'S':
                if j+3 < m and ss[i][j:j+4] == "SAMX":
                    ans += 1
                if i+3 < n and ''.join(list(s[j] for s in ss[i:i+4])) == "SAMX":
                    ans += 1
                if i+3 < n and j+3 < m and ''.join(list(ss[i+k][j+k] for k in range(4))) == "SAMX":
                    ans += 1
                if i+3 < n and j-3 >= 0 and ''.join(list(ss[i+k][j-k] for k in range(4))) == "SAMX":
                    ans += 1
    return ans

with open("input.txt", "r") as fp:
    print(solution(list(s.strip("\n") for s in fp.readlines())))
