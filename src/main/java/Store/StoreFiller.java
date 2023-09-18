package Store;

// Первоначальное наполнение магазина товарами
public class StoreFiller {
    public static void fill(Store store) {
        // Будем использовать сквозной генератор на все категории (хотя, можно использовать раздельные)
        ArticulGenerator articulGenerator = new ArticulGenerator("DEMO");

        // Создаём категории
        Category categoryGPU = new Category("Комплектующие ПК/Видеокарты");
        Category categoryCPU = new Category("Комплектующие ПК/CPU");

        categoryGPU.addProduct(new Product("Видеокарта MSI GeForce RTX 4070", articulGenerator, 75_000, 10));
        categoryGPU.addProduct(new Product("Видеокарта MSI GeForce RTX 4070Ti", articulGenerator, 110_000, 3));
        categoryGPU.addProduct(new Product("Видеокарта Nitro AMD RX570", articulGenerator, 15_999, 7));

        categoryCPU.addProduct(new Product("Процессор Intel Core i5 12400", articulGenerator, 25_000, 1));
        categoryCPU.addProduct(new Product("Процессор AMD Ryzen 5 7600X BOX", articulGenerator, 28_000, 2));

        store.addCategory(categoryCPU);
        store.addCategory(categoryGPU);
    }
}
