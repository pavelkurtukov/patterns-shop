package Store;

// Первоначальное наполнение магазина товарами и услугами
public class StoreFiller {
    public static void fill(Store store) {
        // Будем использовать сквозной генератор на все категории товаров (хотя можно использовать и раздельные)
        ArticulGenerator productArticulGenerator = new ArticulGenerator("ITEM");

        // Создаём категории товаров
        Category categoryGPU = new Category("Комплектующие ПК/Видеокарты");
        Category categoryCPU = new Category("Комплектующие ПК/CPU");

        categoryGPU.addProduct(new Product("Видеокарта MSI GeForce RTX 4070", productArticulGenerator, 75_000, 10));
        categoryGPU.addProduct(new Product("Видеокарта MSI GeForce RTX 4070Ti", productArticulGenerator, 110_000, 3));
        categoryGPU.addProduct(new Product("Видеокарта Nitro AMD RX570", productArticulGenerator, 15_999, 7));

        categoryCPU.addProduct(new Product("Процессор Intel Core i5 12400", productArticulGenerator, 25_000, 1));
        categoryCPU.addProduct(new Product("Процессор AMD Ryzen 5 7600X BOX", productArticulGenerator, 28_000, 2));

        store.addCategory(categoryCPU);
        store.addCategory(categoryGPU);

        // Добавляем услуги
        ArticulGenerator serviceArticulGenerator = new ArticulGenerator("SRV");

        store.addService(new Service("Дополнительная гарантия (1 год)", serviceArticulGenerator, 1000));
        store.addService(new Service("Сборка", serviceArticulGenerator, 2000));
        store.addService(new Service("Диагностика", serviceArticulGenerator, 500));
        store.addService(new Service("Протереть тряпочкой", serviceArticulGenerator, 200));
        store.addService(new Service("Запаковать в пупырчатую плёнку", serviceArticulGenerator, 100));
    }
}
