import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class advent9b {
    private static ArrayList<int[]> corners = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private static ArrayList<int[]>[] goodRanges = (ArrayList<int[]>[]) new ArrayList[100_000];

    private static void buildGoodRanges() {
        int maxy = 0;

        for (int[] corner : corners) {
            maxy = Math.max(maxy, corner[0]);
        }

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] crossings = (ArrayList<Integer>[]) new ArrayList[maxy + 1];
        for (int i = 0; i <= maxy; i++) {
            crossings[i] = new ArrayList<>();
        }

        for (int i = 0; i < corners.size(); i++) {
            int[] cornerCurr = corners.get(i);
            int[] cornerNext = corners.get((i + 1) % corners.size());

            if (cornerCurr[1] == cornerNext[1]) {
                int x = cornerCurr[1];
                int yStart = Math.min(cornerCurr[0], cornerNext[0]);
                int yEnd = Math.max(cornerCurr[0], cornerNext[0]);

                for (int y = yStart; y < yEnd; y++) {
                    crossings[y].add(x);
                }
            }
        }

        for (int y = 0; y <= maxy; y++) {
            Collections.sort(crossings[y]);
            goodRanges[y] = new ArrayList<>();
            for (int i = 0; i + 1 < crossings[y].size(); i += 2) {
                goodRanges[y].add(new int[] { crossings[y].get(i), crossings[y].get(i + 1) });
            }
        }
    }

    private static long solution() {
        buildGoodRanges();

        long ans = 0L;
        for (int i = 0; i < corners.size(); i++) {
            for (int j = i + 1; j < corners.size(); j++) {
                int[] cornera = corners.get(i);
                int[] cornerb = corners.get(j);

                boolean isGood = true;
                for (int y = Math.min(cornera[0], cornerb[0]); y <= Math.max(cornera[0], cornerb[0]); y++) {
                    boolean found = false;
                    for (int[] range : goodRanges[y]) {
                        if (range[0] > Math.min(cornera[1], cornerb[1]))
                            continue;
                        if (range[1] < Math.max(cornera[1], cornerb[1]))
                            continue;
                        found = true;
                        break;
                    }
                    if (!found) {
                        isGood = false;
                        break;
                    }
                }

                if (!isGood)
                    continue;

                long area = (long) (Math.abs(cornera[0] - cornerb[0]) + 1)
                        * (long) (Math.abs(cornera[1] - cornerb[1]) + 1);
                ans = Math.max(ans, area);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNextLine()) {
                corners.add(Arrays.stream(fpr.nextLine().split(",")).mapToInt(Integer::parseInt).toArray());
            }

            fpr.close();

            System.out.println(solution());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
