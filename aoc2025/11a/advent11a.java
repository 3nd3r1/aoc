import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class advent11a {
    private static int N = 17576;
    private static List<List<Integer>> graph = new ArrayList<>();

    static {
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
    }

    private static int serverId(String s) {
        int ret = 0;

        ret += (s.charAt(0) - 'a') * 676;
        ret += (s.charAt(1) - 'a') * 26;
        ret += (s.charAt(2) - 'a');

        return ret;
    }

    private static long dfs(int v) {
        if (v == serverId("out")) {
            return 1L;
        }
        
        long ret = 0L;

        for (int to : graph.get(v)) {
            ret += dfs(to);
        }

        return ret;
    }

    private static long solution() {
        return dfs(serverId("you"));
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNextLine()) {
                String[] parts = fpr.nextLine().split(": ");
                String serverFromString = parts[0];
                String[] serversToStrings = parts[1].split(" ");

                for (String serverToString : serversToStrings) {
                    graph.get(serverId(serverFromString)).add(serverId(serverToString));
                }
            }

            fpr.close();

            System.out.println(solution());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
