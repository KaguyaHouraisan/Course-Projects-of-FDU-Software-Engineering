package id;

public class SmartID implements Id {
    private String id;

    public SmartID(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object k) {
        if (this == k) {
            return true;
        }
        if (k instanceof SmartID) {
            return id.equals(((SmartID)k).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
