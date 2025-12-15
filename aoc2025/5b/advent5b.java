import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class advent5b {
    record Range(long left, long right) {
    }

    private static List<Range> ranges = new ArrayList<>();
    private static List<Long> queries = new ArrayList<>();

    private static long solution() {
        long ans = 0L;

        ranges.sort((Range a, Range b) -> {
            if (a.left == b.left)
                return Long.compare(a.right, b.right);
            return Long.compare(a.left, b.left);
        });

        List<Range> mergedRanges = new ArrayList<>();

        for (Range range : ranges) {
            if (mergedRanges.size() > 0) {
                Range lastRange = mergedRanges.get(mergedRanges.size() - 1);
                if (range.left <= lastRange.right) {
                    mergedRanges.set(mergedRanges.size() - 1,
                            new Range(lastRange.left, Math.max(lastRange.right, range.right)));
                    continue;
                }
            }
            mergedRanges.add(range);
        }

        for (Range range : mergedRanges) {
            ans += range.right - range.left + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (true) {
                String line = fpr.nextLine();
                if (line == "")
                    break;

                long left = Long.parseLong(line.split("-")[0]);
                long right = Long.parseLong(line.split("-")[1]);
                ranges.add(new Range(left, right));
            }

            while (fpr.hasNextLine()) {
                queries.add(Long.parseLong(fpr.nextLine()));
            }

            fpr.close();

            System.out.println(solution());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
