import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class advent4b {
    private static ArrayList<boolean[]> grid = new ArrayList<boolean[]>();

    private static int[][] moves = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
            { 1, 1 } };

    private static long solution() {
        long ans = 0L;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).length; j++) {
                queue.offer(new int[] { i, j });
            }
        }

        while (!queue.isEmpty()) {
            int i = queue.peek()[0];
            int j = queue.poll()[1];

            if (grid.get(i)[j]) {
                int papers = 0;
                for (int[] move : moves) {
                    int newi = i + move[0];
                    int newj = j + move[1];

                    if (newi < 0 || newi >= grid.size() || newj < 0 || newj >= grid.get(i).length)
                        continue;

                    if (grid.get(newi)[newj])
                        papers++;
                }

                if (papers < 4) {
                    ans++;
                    grid.get(i)[j] = false;

                    for (int[] move : moves) {
                        int newi = i + move[0];
                        int newj = j + move[1];

                        if (newi < 0 || newi >= grid.size() || newj < 0 || newj >= grid.get(i).length)
                            continue;

                        if (grid.get(newi)[newj])
                            queue.offer(new int[] { newi, newj });
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNextLine()) {
                String rowString = fpr.nextLine();
                grid.add(new boolean[rowString.length()]);
                for (int i = 0; i < rowString.length(); i++) {
                    if (rowString.charAt(i) == '@') {
                        grid.get(grid.size() - 1)[i] = true;
                    }
                }
            }

            fpr.close();

            System.out.println(solution());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
