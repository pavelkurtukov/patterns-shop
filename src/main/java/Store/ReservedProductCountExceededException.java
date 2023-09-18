package Store;

public class ReservedProductCountExceededException extends Exception {
    public ReservedProductCountExceededException(int reserved, int count) {
        super("Невозможно списать товар в количестве " + count + " шт. (зарезервировано " + reserved + " шт.)");
    }
}

