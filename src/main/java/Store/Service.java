package Store;

public class Service implements Saleable {
    private String name; // Наименование услуги
    private String description; // Описание
    private String articul; // Артикул
    private int price; // Цена

    public Service(String name, String description, String articul, int price) {
        this.name = name;
        this.description = description;
        this.articul = articul;
        this.price = price;
    }

    public Service(String name, ArticulGenerator articulGenerator, int price) {
        this(name, "Описание услуги \"" + name + "\"", articulGenerator.generateNext(), price);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getArticul() {
        return this.articul;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int count) throws AvailableProductCountExceededException {

    }

    @Override
    public void unreserve(int count) throws AvailableUnreserveProductCountExceedException {

    }

    @Override
    public void sale(int count) throws ReservedProductCountExceededException {

    }

    @Override
    public String toString() {
        return String.format("Услуга {Артикул: '%s', Наименование: '%s', Описание: '%s', Стоимость: %s}", articul, name, description, price);
    }
}
