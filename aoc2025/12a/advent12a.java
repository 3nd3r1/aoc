import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class advent12a {
    record Size(int n, int m) {
    }

    private static final int GIFT_COUNT = 6;
    private static int[] gifts = new int[GIFT_COUNT];

    private static List<Size> regionSizes = new ArrayList<>();
    private static List<List<Integer>> regionGiftCounts = new ArrayList<>();

    private static long solution() {
        long ans = 0L;

        for (int i = 0; i < regionSizes.size(); i++) {
            int n = regionSizes.get(i).n;
            int m = regionSizes.get(i).m;

            int giftCapacity = (n / 3) * (m / 3);

            if (regionGiftCounts.get(i).stream().mapToInt(Integer::intValue).sum() <= giftCapacity) {
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            for (int i = 0; i < GIFT_COUNT; i++) {
                fpr.nextLine();

                int mask = 0;
                for (int j = 0; j < 3; j++) {
                    String row = fpr.nextLine();
                    for (int k = 0; k < 3; k++) {
                        if (row.charAt(k) == '#')
                            mask |= (1 << (j * 3 + k));
                    }
                }
                gifts[i] = mask;
                fpr.nextLine();
            }

            while (fpr.hasNextLine()) {
                String line = fpr.nextLine();

                int n = Integer.parseInt(line.split(": ")[0].split("x")[1]);
                int m = Integer.parseInt(line.split(": ")[0].split("x")[0]);

                regionSizes.add(new Size(n, m));
                regionGiftCounts.add(Arrays.stream(line.split(": ")[1].split(" ")).map(Integer::parseInt)
                        .collect(Collectors.toList()));
            }

            fpr.close();

            System.out.println(solution());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
