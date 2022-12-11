import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
public class Disk implements Comparable<Disk> {
    private static final AtomicInteger DiskCounter = new AtomicInteger(0);
    @SuppressWarnings("FieldCanBeLocal")
    private final int id;
    private ArrayList<String> folders;
    @SuppressWarnings("FieldMayBeFinal")
    private int freeSpace = 1_000_000;

    public Disk() {
        id = DiskCounter.getAndIncrement();
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    @Override
    public int compareTo(Disk o) {
        return Integer.compare(freeSpace, o.freeSpace);
    }
}
