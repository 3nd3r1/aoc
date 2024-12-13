def solution(diskmap: str):
    disk = []
    diskid = 0
    for i in range(len(diskmap)):
        if i % 2 == 0:
            for _ in range(int(diskmap[i])):
                disk.append(diskid)
            diskid += 1
        else:
            for _ in range(int(diskmap[i])):
                disk.append(-1)
    l = 0
    r = len(disk) - 1
    while l < r:
        while l < r and disk[l] != -1:
            l += 1
        disk[l], disk[r] = disk[r], disk[l]
        r -= 1

    ans = 0
    for i in range(len(disk)):
        if disk[i] == -1:
            break
        ans += i * disk[i]
    return ans


with open("input.txt", "r") as fp:
    print(solution(fp.read().strip("\n")))
