def solution(deps: dict, updates: list):
    ans = 0

    for update in updates:
        present = {}
        i = 0
        for u in update:
            present[u] = False

        correct = True
        while i < len(update):
            if all(present.get(x, True) for x in deps.get(update[i], [])):
                present[update[i]] = True
                i += 1
            else:
                correct = False
                update.append(update.pop(i))

        if not correct:
            ans += update[len(update) // 2]

    return ans


with open("input.txt", "r") as fp:
    deps = {}
    updates = []
    depsDone = False
    for line in fp.readlines():
        line = line.strip("\n")
        if len(line) == 0:
            depsDone = True
            continue
        if not depsDone:
            x, y = [int(v) for v in line.split("|")]
            if y not in deps:
                deps[y] = []
            deps[y].append(x)
        else:
            updates.append([int(x) for x in line.split(",")])
    print(solution(deps, updates))
