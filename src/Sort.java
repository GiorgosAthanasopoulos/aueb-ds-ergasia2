public class Sort {
    public static Integer[] quicksort(Integer[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
            quicksort(arr, p, q);
            quicksort(arr, q+1, r);
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
}
