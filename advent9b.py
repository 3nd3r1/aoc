def convert(disks):
    ret = []
    for disk in disks:
        if disk[0] == -1:
            ret += [0 for _ in range(disk[1])]
        else:
            ret += [disk[0] for _ in range(disk[1])]
    return ret


def solution(diskmap: str):
    disks = []
    diskid = 0
    for i in range(len(diskmap)):
        if i % 2 == 0:
            disks.append((diskid, int(diskmap[i])))
            diskid += 1
        else:
            disks.append((-1, int(diskmap[i])))

    while diskid >= 0:
        i = len(disks) - 1
        while i > 0 and disks[i][0] != diskid:
            i -= 1

        j = 0
        while j < i and (disks[j][0] != -1 or disks[j][1] < disks[i][1]):
            j += 1
        if j != i:
            disks[j] = (disks[j][0], disks[j][1] - disks[i][1])
            disks = (
                disks[:j]
                + [(-1, 0), disks[i]]
                + disks[j:i]
                + [(-1, disks[i][1])]
                + disks[i + 1 :]
            )
        diskid -= 1

    ans = 0
    for i, v in enumerate(convert(disks)):
        ans += v * i
    return ans


with open("input.txt", "r") as fp:
    print(solution(fp.read().strip("\n")))
