package Store;

public class Product implements Saleable {
    private String name; // Наименование товара
    private String description; // Описание
    private String articul; // Артикул
    private int price; // Цена
    private int count; // Кол-во штук в наличии
    private int available; // Кол-во доступных к заказу товаров

    // Базовое создание товара с указанием всех параметров вручную
    public Product(String name, String description, String articul, int price, int count) throws IllegalArgumentException {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена товара должна быть больше нуля");
        }

        if (count <= 0) {
            throw new IllegalArgumentException("Количество товара должно быть больше нуля");
        }

        if (name.isEmpty() || articul.isEmpty()) {
            throw new IllegalArgumentException("Наименование и артикул должны быть обязательно заполнены");
        }

        this.name = name;
        this.description = description;
        this.articul = articul;
        this.price = price;
        this.count = count;
        this.available = count;
    }

    // Создание продукта с генератором артикулов
    public Product(String name, ArticulGenerator articulGenerator, int price, int count) {
        this(name, "Описание для " + name, articulGenerator.generateNext(), price, count);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getArticul() {
        return articul;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAvailable() {
        return available;
    }

    // Резервирование товара (для корзины)
    public void reserve(int count) throws AvailableProductCountExceededException, IllegalArgumentException {
        if (count <= 0) {
            throw new IllegalArgumentException("Количество резервируемых товаров должно быть больше 0");
        }
        if (this.available >= count) {
            this.available -= count;
        } else {
            throw new AvailableProductCountExceededException(this.available, count);
        }
    }

    // Возврат товаров из резерва (удаление из корзины)
    public void unreserve(int count) throws AvailableUnreserveProductCountExceedException, IllegalArgumentException {
        if (count <= 0) {
            throw new IllegalArgumentException("Превышено количество товара, которое можно вернуть из резерва");
        }
        if (count <= this.count - this.available) {
            this.available += count;
        } else {
            throw new AvailableUnreserveProductCountExceedException(this.available, count);
        }
    }

    // Продажа товара (списание из зарезервинованных)
    public void sale(int count) throws ReservedProductCountExceededException, IllegalArgumentException {
        if (count <= 0) {
            throw new IllegalArgumentException("Количество заказываемых товаров должно быть больше 0");
        }
        int reserved = count - available;
        if (reserved >= count) {
            available -= count;
            this.count -= count;
        } else {
            throw new ReservedProductCountExceededException(reserved, count);
        }
    }

    @Override
    public String toString() {
        return String.format("Товар {Артикул: '%s', Наименование: '%s', Описание: '%s', Цена: %s, Количество: %d, Доступно: %d}", articul, name, description, price, count, available);
    }
}
