package Store;

public class AvailableProductCountExceededException extends Exception {
    public AvailableProductCountExceededException(int available, int count) {
        super("Невозможно зарезервировать товар в количестве " + count + " шт. (доступно " + available + " шт.)");
    }
}
