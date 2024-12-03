def solution(s):
    ans = 0
    do = True
    for i in range(len(s)):
        if s[i:i+4] == "do()":
            do = True
            continue
        if s[i:i+7] == "don't()":
            do = False
            continue

        if not do:
            continue
        if s[i:i+4] != "mul(":
            continue
        end = i+4+s[i+4:].find(")")
        if end ==len(s):
            continue
        if "," not in s[i+4:end] or len(s[i+4:end].split(",")) > 2:
            continue
        left, right = s[i+4:end].split(",")
        if len(left) < 1 or len(right) < 1:
            continue
        if len(left) > 3 or len(right) > 3:
            continue
        if not all(c.isdigit() for c in left) or not all(c.isdigit() for c in right):
            continue
        ans += int(left)*int(right)
    return ans

with open("input.txt", "r") as fp:
    print(solution(fp.read()))
