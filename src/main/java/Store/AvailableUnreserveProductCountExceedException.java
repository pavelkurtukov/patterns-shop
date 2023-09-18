package Store;

public class AvailableUnreserveProductCountExceedException extends Exception {
    public AvailableUnreserveProductCountExceedException(int available, int count) {
        super("Невозможно вернуть из резерва товар в количестве " + count + " шт. (доступно " + available + " шт.)");
    }
}
