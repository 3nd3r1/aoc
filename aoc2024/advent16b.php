<?php
$map = [];
$dist = [];
$path = [];
$pq = new Pq();
$INF = 100000000000;

class Pq extends SplPriorityQueue {
    public function compare($a, $b): int {
        if ($a === $b) return 0;
        return $a > $b ? -1 : 1;
    }
}

function doMoves(int $i, int $j, int $side): void {
    global $map, $dist, $pq, $path;
    
    $ni = $i;
    $nj = $j;

    if ($side == 0) {
        $ni = $i-1;
    } else if ($side == 1) {
        $nj = $j+1;
    } else if ($side == 2) {
        $ni = $i+1;
    } else if ($side == 3) {
        $nj = $j-1;
    }

    if ($ni<count($map) && $ni >= 0 && $nj < count($map[0]) && $nj >= 0 && $map[$ni][$nj] != "#" && $dist[$ni][$nj][$side] >= $dist[$i][$j][$side] + 1) {
        array_push($path[$ni][$nj][$side], [$i, $j, $side]);
        if ($dist[$ni][$nj][$side] > $dist[$i][$j][$side] + 1) {
            $dist[$ni][$nj][$side] = $dist[$i][$j][$side] + 1;
            $pq->insert([$ni, $nj, $side], $dist[$ni][$nj][$side]);
        }
    }
    if ($dist[$i][$j][($side+1)%4] >= $dist[$i][$j][$side] + 1000) {
        array_push($path[$i][$j][($side+1)%4], [$i, $j, $side]);
        if ($dist[$i][$j][($side+1)%4] > $dist[$i][$j][$side] + 1000) {
            $dist[$i][$j][($side+1)%4] = $dist[$i][$j][$side] + 1000;
            $pq->insert([$i, $j, ($side+1)%4], $dist[$i][$j][($side+1)%4]);
        }
    }
    if ($dist[$i][$j][($side+3)%4] >= $dist[$i][$j][$side] + 1000) {
        array_push($path[$i][$j][($side+3)%4], [$i, $j, $side]);
        if ($dist[$i][$j][($side+3)%4] > $dist[$i][$j][$side] + 1000) {
            $dist[$i][$j][($side+3)%4] = $dist[$i][$j][$side] + 1000;
            $pq->insert([$i, $j, ($side+3)%4], $dist[$i][$j][($side+3)%4]);
        }
    }
}

function solution(): int {
    global $map, $dist, $pq, $INF, $path;

    $dist = array_fill(0, count($map), array_fill(0, count($map[0]), array_fill(0, 4, $INF)));
    $path = array_fill(0, count($map), array_fill(0, count($map[0]), array_fill(0, 4, [])));

    for ($i = 0; $i < count($map); $i++) {
        for ($j = 0; $j < count($map[$i]); $j++) {
            if ($map[$i][$j] == "S") {
                $dist[$i][$j][1] = 0;
                $pq->insert([$i, $j, 1], 0);
            }
        }
    }
    
    $q = [];
    while (!$pq->isEmpty()) {
        $top = $pq->extract();
        $i = $top[0];
        $j = $top[1];
        $side = $top[2];

        if ($map[$i][$j] == "E" && (count($q) == 0 || ($dist[$i][$j][$side] == $dist[$q[0][0]][$q[0][1]][$q[0][2]]) && $q[0][2] != $side)) {
            array_push($q, [$i, $j, $side]);
        }

        doMoves($i, $j, $side);
    }

    $ans = 0;
    while (count($q) > 0) {
        $f = array_pop($q);
        $map[$f[0]][$f[1]] = "O";
        if ($dist[$f[0]][$f[1]][0] != -1) {
            $ans++;
            $dist[$f[0]][$f[1]][0] = -1;
        }
        foreach($path[$f[0]][$f[1]][$f[2]] as $crd) {
            array_push($q, $crd);
        }
    }

    foreach ($map as $line) {
        echo join("", $line)."\n";
    }

    return $ans;
}

$fp = fopen("input.txt", "r") or die();
$map = explode("\n",fread($fp, filesize("input.txt")));
fclose($fp);

for ($i = 0; $i < count($map); $i++) {
    $map[$i] = str_split($map[$i]);
}

echo solution()."\n";
?>
