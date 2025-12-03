import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class advent3b {
    static private ArrayList<int[]> banks = new ArrayList<>();

    private static int max(int a, int b) {
        if (a > b)
            return a;
        return b;
    }

    private static long solution() {
        long ans = 0L;
        for (int[] bank : banks) {
            // System.out.println(Arrays.toString(bank));

            Integer[] bankIndex = new Integer[bank.length];
            for (int i = 0; i < bankIndex.length; i++)
                bankIndex[i] = i;
            Arrays.sort(bankIndex, (i, j) -> Integer.compare(bank[j], bank[i]));

            int[] used = new int[12];
            for (int i = 0; i < used.length; i++)
                used[i] = -1;

            for (int i = 0; i < bankIndex.length; i++) {
                int minIndex = 0;
                for (int j = max(0, bankIndex[i] - (bank.length - used.length)); j < used.length; j++) {
                    minIndex = max(minIndex, used[j]);
                    if (bankIndex[i] < minIndex)
                        continue;
                    if (used[j] != -1)
                        continue;
                    used[j] = bankIndex[i];
                    ans += (long) bank[used[j]] * (long) Math.pow(10, used.length - j - 1);
                    break;
                }
            }

            // for (int i : used) System.out.print(bank[i]);
            // System.out.println();
        }

        return ans;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNextLine()) {
                String bankString = fpr.nextLine();
                banks.add(new int[bankString.length()]);
                for (int i = 0; i < bankString.length(); i++) {
                    banks.get(banks.size() - 1)[i] = Integer.parseInt(String.valueOf(bankString.charAt(i)));
                }
            }

            fpr.close();

            System.out.println(solution());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
