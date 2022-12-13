import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
public class Disk implements Comparable<Disk> {
    private static final AtomicInteger DiskCounter = new AtomicInteger(0);
    @SuppressWarnings("FieldCanBeLocal")
    private final int id;
    private final ArrayList<Integer> folders;
    @SuppressWarnings("FieldMayBeFinal")
    private int freeSpace = 1_000_000;

    public Disk() {
        folders = new ArrayList<>();
        id = DiskCounter.getAndIncrement();
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public void addFolder(Integer folderSize) {
        folders.add(folderSize);
        freeSpace -= folderSize;
    }

    public int id() {
        return id;
    }

    public ArrayList<Integer> folders() {
        return folders;
    }

    @Override
    public int compareTo(Disk o) {
        return Integer.compare(freeSpace, o.freeSpace);
    }
}
