import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class advent3a {
    static private ArrayList<int[]> banks = new ArrayList<>();

    private static long solution() {
        long ans = 0L; 
        for (int[] bank : banks) {
            int maxLeftIndex = 0;
            for (int i = 0; i<bank.length-1; i++) {
                if (bank[i] > bank[maxLeftIndex]) {
                    maxLeftIndex = i;
                }
            }

            int maxRightIndex = maxLeftIndex+1;
            for (int i = maxLeftIndex+1; i<bank.length; i++) {
                if (bank[i] > bank[maxRightIndex]) {
                    maxRightIndex = i;
                }
            }
            ans += bank[maxLeftIndex] * 10 + bank[maxRightIndex];
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
