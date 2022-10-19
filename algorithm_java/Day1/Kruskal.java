package Day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class edge implements Comparable<edge> {
    public int src;
    public int dst;
    public int weight;

    public edge(int src, int dst, int weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    @Override
    public int compareTo(edge e) {
        return (e.weight > weight) ? 1 : 0;
    }
}


public class Kruskal {
    public static ArrayList<edge> grp;
    public static BufferedReader br;
    public static StringBuilder sb;
    private static int[] parent;

    public static void do_kruskal() {
        dset_init();
        int rst = 0;


    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        String input[] = br.readLine().split(" ");
        int v = Integer.parseInt(input[0]);
        int e = Integer.parseInt(input[1]);

        grp = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            String str[] = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);

            grp.add(new edge(a, b, c));
        }

        grp.sort();

        sb = new StringBuilder();


        System.out.println(sb);
        br.close();
    }

    public static void dset_init() {
        parent = new int[grp.size()];
        for (int i = 0; i < grp.size(); i++)
            parent[i] = i;
    }

    public static int find_parent(int x) {
        if (parent[x] != x)
            return find_parent(parent[x]);
        return x;
    }

    public static void union_parent(int a, int b) {
        int ra = find_parent(a);
        int rb = find_parent(b);

        if (ra < rb) parent[rb] = ra;
        else parent[ra] = rb;
    }
}
