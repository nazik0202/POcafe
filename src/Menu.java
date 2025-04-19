import java.util.*;

class Menu {
    private Map<String, List<Item>> menuCategories;

    public Menu() {
        this.menuCategories = new LinkedHashMap<>();
        makeMenu(); // Автоматично ініціалізуємо меню
    }

    private void makeMenu() {
        menuCategories.clear();


        menuCategories.put("Перша страва", Arrays.asList(
                new Food(150.0, "Борщ", false, false),
                new Food(160.0, "Грибний суп", false, false),
                new Food(140.0, "Картопляний суп", false, false)
        ));


        menuCategories.put("Салати", Arrays.asList(
                new Food(220.0, "Цезар", false, false),
                new Food(180.0, "Грецький салат", true, false),
                new Food(150.0, "Олів'є", false, false),
                new Food(120.0, "Салат з буряка з горіхами", true, false)
        ));


        menuCategories.put("Гарячі закуски", Arrays.asList(
                new Food(160.0, "Вареники з картоплею", true, false),
                new Food(180.0, "Вареники з м'ясом", false, false),
                new Food(140.0, "Деруни зі сметаною", true, false),
                new Food(150.0, "Млинці з м'ясом", false, false)
        ));


        menuCategories.put("Основні страви", Arrays.asList(
                new Food(250.0, "Котлета по-київськи", false, false),
                new Food(200.0, "Свиняча відбивна", false, false),
                new Food(180.0, "Куряче філе гриль", false, false),
                new Food(220.0, "Печеня по-домашньому", false, false)
        ));


        menuCategories.put("Гарніри", Arrays.asList(
                new Food(80.0, "Картопляне пюре", true, false),
                new Food(90.0, "Смажена картопля", true, false),
                new Food(70.0, "Рис відварний", true, false),
                new Food(70.0, "Гречана каша", true, false)
        ));


        menuCategories.put("Десерти", Arrays.asList(
                new Food(120.0, "Сирник", true, false),
                new Food(100.0, "Штрудель з яблуками", true, false),
                new Food(80.0, "Морозиво", true, false)
        ));


        menuCategories.put("Напої", Arrays.asList(
                new Drink(50.0, "Узвар", false, 350),
                new Drink(60.0, "Сік", false, 300),
                new Drink(70.0, "Кава", false, 500),
                new Drink(40.0, "Чай", false, 200)
        ));
        menuCategories.put("Додатково", Arrays.asList(
                new Food(10.0, "Хліб", true, false),
                new Food(20.0, "Сметана", true, false)
        ));
    }

    public Map<String, List<Item>> getMenuCategories() {
        return menuCategories;
    }

}