import { readFileSync } from "node:fs";

const divmod = (a: number, b: number): [number, number] => {
    return [Math.floor(a / b), a % b];
};

const solution = (
    a: [number, number],
    b: [number, number],
    prize: [number, number],
): number => {
    const d = a[1] * b[0] - a[0] * b[1];
    if (d == 0) return 0;

    const [bc, remb] = divmod(a[1] * prize[0] - a[0] * prize[1], d);
    if (remb != 0) return 0;

    const [ac, rema] = divmod(prize[0] - b[0] * bc, a[0]);
    if (rema != 0) return 0;

    return ac * 3 + bc;
};

const file = readFileSync("./input.txt", "utf-8");
const lines = file.toString().split("\n");

let ans = 0;

for (let i = 2; i < lines.length; i += 4) {
    const a: [number, number] = [
        Number(lines[i - 2].split(", ")[0].split("+")[1]),
        Number(lines[i - 2].split(", ")[1].split("+")[1]),
    ];
    const b: [number, number] = [
        Number(lines[i - 1].split(", ")[0].split("+")[1]),
        Number(lines[i - 1].split(", ")[1].split("+")[1]),
    ];
    const prize: [number, number] = [
        Number(lines[i].split(", ")[0].split("=")[1])+10000000000000,
        Number(lines[i].split(", ")[1].split("=")[1])+10000000000000,
    ];

    ans += solution(a, b, prize);
}

console.log(ans);
