import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Review {
    private static void generateDataFiles(int[] N, int amount) throws IOException {
        Random r = new Random();
        for (int n : N) {
            for (int i = 0; i < amount; i++) {
                FileWriter fw = new FileWriter("./data/n" + n + "i" + i);
                for (int j = 0; j < n; j++) {
                    fw.write(r.nextInt(0, 1_000_001) + "\n");
                }
                fw.close();
            }
        }
    }

    private static void review(int[] N, int amount) throws IOException {
        int[] sumGreedy = new int[N.length];
        int[] sumSort = new int[N.length];
        for (int j=0; j<N.length; j++)
            for (int i=0; i<amount; i++) {
                String filename = "./data/n" + N[j] + "i" + i;
                sumGreedy[j] += Greedy.algorithm1(filename, false);
                sumSort[j] += Sort.algorithm2(filename, false);
            }

        for (int i=0; i<sumGreedy.length; i++) {
            sumGreedy[i] /= amount;
            sumSort[i] /= amount;
        }
        System.out.println(Arrays.toString(sumGreedy));
        System.out.println(Arrays.toString(sumSort));
    }

    public static void main(String[] args) throws IOException {
        int[] N = new int[]{100, 500, 1000};
        int amount = 10;

        generateDataFiles(N, amount);
        review(N, amount);
    }
}