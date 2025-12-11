import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class advent11b {
    private static int N = 17576;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static long[][][] mem = new long[N][2][2];

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

    private static long dfs(int v, boolean dac, boolean fft) {
        if (v == serverId("out") && dac && fft)
            return 1L;

        if (mem[v][dac ? 1 : 0][fft ? 1 : 0] > 0)
            return mem[v][dac ? 1 : 0][fft ? 1 : 0] - 1;

        if (v == serverId("dac"))
            dac = true;
        if (v == serverId("fft"))
            fft = true;

        long ret = 0L;
        for (int to : graph.get(v)) {
            ret += dfs(to, dac, fft);
        }

        mem[v][dac ? 1 : 0][fft ? 1 : 0] = ret + 1;
        return ret;
    }

    private static long solution() {
        return dfs(serverId("svr"), false, false);
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
