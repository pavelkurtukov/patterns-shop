package Store;

public class ArticulGenerator {
    private int currentArticulNumber;
    private final String articulPrefix;

    public ArticulGenerator(String articulPrefix, int currentArticulNumber) {
        this.articulPrefix = articulPrefix.toUpperCase();
        this.currentArticulNumber = currentArticulNumber;
    }

    public ArticulGenerator(String articulPrefix) {
        this.articulPrefix = articulPrefix.toUpperCase();
        this.currentArticulNumber = 1;
    }

    public int getCurrentArticulNumber() {
        return currentArticulNumber;
    }

    public void setCurrentArticulNumber(int currentArticulNumber) {
        this.currentArticulNumber = currentArticulNumber;
    }

    // Генерация артикула
    public String generateNext() {
        return String.format("%s-%03d", articulPrefix, currentArticulNumber++);
    }
}
