package Repository;

public interface Keeper {
    Boolean save(String log);
    String load();
}
