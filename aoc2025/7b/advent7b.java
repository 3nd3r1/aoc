import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class advent7b {
    private static List<String> lines = new ArrayList<>();
    private static long[][] mem = new long[1000][1000];
    static {
        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[i].length; j++) {
                mem[i][j] = -1;
            }
        }
    }

    private static long dfs(int i, int j) {
        if (i == lines.size() - 1) {
            return 1;
        }
        if (j < 0 || j >= lines.get(0).length())
            return 0;

        if (mem[i][j] != -1)
            return mem[i][j];

        if (lines.get(i).charAt(j) == '^') {
            mem[i][j] = dfs(i, j - 1) + dfs(i, j + 1);
            return mem[i][j];
        }

        mem[i][j] = dfs(i + 1, j);
        return mem[i][j];
    }

    private static long solution() {
        int starti = 0;
        int startj = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == 'S') {
                    starti = i;
                    startj = j;
                    break;
                }
            }
        }

        return dfs(starti, startj);
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNext()) {
                lines.add(fpr.nextLine());
            }
            fpr.close();

            System.out.println(solution());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
