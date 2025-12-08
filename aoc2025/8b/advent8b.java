import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class advent8b {
    private static ArrayList<int[]> boxes = new ArrayList<>();

    private static long solution() {
        ArrayList<int[]> connections = new ArrayList<>();

        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                int distance = (int) Math.sqrt(
                        Math.pow(boxes.get(i)[0] - boxes.get(j)[0], 2) +
                                Math.pow(boxes.get(i)[1] - boxes.get(j)[1], 2) +
                                Math.pow(boxes.get(i)[2] - boxes.get(j)[2], 2));
                connections.add(new int[] { i, j, distance });
            }
        }

        connections.sort((int[] conna, int[] connb) -> conna[2] - connb[2]);

        int[] groupCount = new int[boxes.size()];
        int[] group = new int[boxes.size()];

        for (int i = 0; i < boxes.size(); i++) {
            group[i] = i;
            groupCount[i] = 1;
        }

        int i = 0;
        while (groupCount[0] < boxes.size()) {
            int a = connections.get(i)[0];
            int b = connections.get(i)[1];

            int groupa = group[a];
            int groupb = group[b];

            while (groupa != group[groupa])
                groupa = group[groupa];

            while (groupb != group[groupb])
                groupb = group[groupb];

            if (groupa < groupb) {
                groupCount[groupa] += groupCount[groupb];
                groupCount[groupb] = 0;
                group[groupb] = groupa;
            } else if (groupa > groupb) {
                groupCount[groupb] += groupCount[groupa];
                groupCount[groupa] = 0;
                group[groupa] = groupb;
            }
            i++;
        }

        int[] boxa = boxes.get(connections.get(i-1)[0]);
        int[] boxb = boxes.get(connections.get(i-1)[1]);

        return (long)boxa[0] * (long)boxb[0];
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNextLine()) {
                boxes.add(Arrays.stream(fpr.nextLine().split(",")).mapToInt(Integer::parseInt).toArray());
            }

            fpr.close();

            System.out.println(solution());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
