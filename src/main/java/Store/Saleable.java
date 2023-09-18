package Store;

public interface Saleable {
    public String getName();
    public String getDescription();
    public String getArticul();
    public int getPrice();
    void reserve(int count) throws AvailableProductCountExceededException;
    void unreserve(int count) throws AvailableUnreserveProductCountExceedException;
    void sale(int count) throws ReservedProductCountExceededException;
}
