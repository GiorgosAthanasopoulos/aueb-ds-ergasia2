import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;

public class Greedy {
    public static Optional<Integer[]> validInputFile(String filename) throws IOException {
        File file = new File(filename);

        try {
            Paths.get(filename);
        } catch (Exception __) {
            System.out.println("Input path is not valid!");
            return Optional.empty();
        }

        if (file.isDirectory()) {
            System.out.println("Input path is a directory!");
            return Optional.empty();
        }

        if (!file.exists()) {
            System.out.println("Input file does not exist!");
            return Optional.empty();
        }

        if (file.length() == 0) {
            System.out.println("Input file is empty!");
            return Optional.empty();
        }

        @SuppressWarnings("resource") long lineCount = Files.lines(Path.of(filename)).filter(line -> !(line.trim().strip().equals("") || line.trim().strip().equals(" "))).count();

        if (lineCount < 1) {
            System.out.println("Input file format is invalid: valid line count < 1 (required)!");
            return Optional.empty();
        }

        Integer[] res = new Integer[(int) lineCount];
        int counter = 0, lineCounter = 1;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                int input = Integer.parseInt(sc.nextLine().strip().trim());
                if (input < 0 || input > 1_000_000) {
                    System.out.println("Error in line " + lineCounter + " of file " + filename + ": Number must be greater or equal to 0 and less than or equal to 1.000.000");
                    return Optional.empty();
                }
                res[counter++] = input;
                lineCounter++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file!");
            return Optional.empty();
        }

        return Optional.of(res);
    }

    public static ArrayList<Disk> solve(Integer[] folderSizes) {
        MaxPQ<Disk> disks = new MaxPQ<>(new DiskComparator());

        for (Integer folderSize : folderSizes) {
            if (disks.peek() == null || disks.peek().getFreeSpace() < folderSize) {
                disks.add(new Disk());
                disks.update();
            }
            disks.peek().addFolder(folderSize);
            disks.update();
        }

        ArrayList<Disk> res = new ArrayList<>();
        while (disks.peek() != null)
            res.add(disks.getMax());
        return res;
    }

    public static void printSolution(ArrayList<Disk> disks) {
        int sum = 0;
        int folderCounter = 0;
        for (Disk disk : disks) {
            sum += 1_000_000 - disk.getFreeSpace();
            folderCounter += disk.folders().size();
        }

        System.out.println("Sum of all folders = " + sum / 1_000_000 + "." + sum % 1_000_000 + " TB");
        System.out.println("Total number of disks used = " + disks.size());

        if (folderCounter <= 100) {
            while (!disks.isEmpty()) {
                Disk max = disks.get(0);
                int maxFreeSpace = max.getFreeSpace();
                for (int j = 1; j < disks.size(); j++) {
                    if (disks.get(j).getFreeSpace() > maxFreeSpace) {
                        max = disks.get(j);
                        maxFreeSpace = max.getFreeSpace();
                    }
                }
                System.out.print("id " + max.id() + " " + max.getFreeSpace() + ": ");
                for (Integer folder : max.folders()) {
                    System.out.print(folder + " ");
                }
                System.out.println();
                disks.remove(max);
            }
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public static int algorithm1(String filename, boolean output) throws IOException {
        Optional<Integer[]> folderSizesOptional = validInputFile(filename);
        if (folderSizesOptional.isEmpty()) System.exit(1);
        ArrayList<Disk> disks = solve(folderSizesOptional.get());
        if (output)
            printSolution(disks);
        return disks.size();
    }

    // ALGORITHM 1
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing argument: filename");
            System.exit(1);
        }

        algorithm1(args[0], true);
    }
}