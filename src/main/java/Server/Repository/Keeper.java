package Server.Repository;

public interface Keeper {
    Boolean save(String log);
    String load();
}
