left = []
right = []

with open("input.txt", "r") as fp:
    for line in fp.readlines():
        left.append(int(line.split("  ")[0]))
        right.append(int(line.split("  ")[1]))

count = {}

for num in right:
    if num not in count:
        count[num] = 0
    count[num] += 1

ans = 0

for num in left:
    ans += num * count.get(num,0)

print(ans)
