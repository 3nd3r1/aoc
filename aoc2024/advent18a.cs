using System.IO;
using System;
using System.Collections.Generic;

public class Program
{
    private static List<(int X, int Y)> bytes;
    private static int n = 71;
    private static int m = 71;
    private static int byteCount = 1024;

    private static bool[,] map;
    private static int[,] dist;

    public static int Solution() {
        int inf = 10000000;
        map = new bool[n,m];
        dist = new int[n,m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i,j] = inf;
            }
        }

        for (int i = 0; i < Math.Min(byteCount, bytes.Count); i++) {
            map[bytes[i].X,bytes[i].Y] = true;
        }

        Queue<(int X, int Y)> q = new Queue<(int X, int Y)>();
        q.Enqueue((X: 0, Y: 0));
        dist[0,0] = 0;

        while (q.Count > 0) {
            var first = q.Dequeue();

            if (first.X == n-1 && first.Y == m-1) {
                return dist[first.X,first.Y];
            }

            var moves = new (int X, int Y)[]{(0,-1),(0,1),(-1,0),(1,0)};
            foreach (var move in moves) {
                int newX = first.X+move.X;
                int newY = first.Y+move.Y;
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && !map[newX,newY] && dist[first.X,first.Y]+1 < dist[newX,newY]) {
                    dist[newX,newY] = dist[first.X,first.Y] + 1;
                    q.Enqueue((X: newX, Y: newY));
                }
            }
        }

        return dist[n-1,m-1];
    }
    public static void Main(string[] args) {
        try {
            String[] lines = File.ReadAllLines("input.txt");
            bytes = new List<(int X, int Y)>();

            foreach(var line in lines) {
                int x = int.Parse(line.Split(",")[0].Trim());
                int y = int.Parse(line.Split(",")[1].Trim());
                bytes.Add((x,y));
            }

            Console.WriteLine(Solution());
        } catch(Exception e) {
            Console.WriteLine(e.Message);
        }
    }
}
