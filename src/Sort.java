import java.io.IOException;
import java.util.Optional;

public class Sort {
    public static Integer[] quicksort(Integer[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
            quicksort(arr, p, q);
            quicksort(arr, q + 1, r);
        }

        return arr;
    }

    private static int partition(Integer[] arr, int p, int r) {
        int pivot = arr[p];
        int i = p;
        int j = r;

        while (true) {
            while (arr[i] > pivot) {
                i++;
            }

            while (arr[j] < pivot) {
                j--;
            }

            if (i < j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            } else {
                return j;
            }
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public static int algorithm2(String filename, boolean output) throws IOException {
        Optional<Integer[]> folderSizesOptional = Greedy.validInputFile(filename);
        if (folderSizesOptional.isEmpty()) System.exit(1);
        Integer[] folderSizes = folderSizesOptional.get();
        ArrayList<Disk> disks = Greedy.solve(quicksort(folderSizes, 0, folderSizes.length - 1));
        if (output)
            Greedy.printSolution(disks);
        return disks.size();
    }

    // ALGORITHM 2
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing argument: filename");
            System.exit(1);
        }

        algorithm2(args[0], true);
    }
}
