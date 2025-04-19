import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cafe {
    private Menu menu;
    private Order currentOrder;

    public Cafe() {
        currentOrder = new Order();
        makeMenu();
    }


    public static void main(String[] args) {
        Gui gui = new Gui();
    }

    public void consoleRun() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Cafe!");
            System.out.println("1. View Menu");
            System.out.println("2. Add to Order");
            System.out.println("3. View Order");
            System.out.println("4. Finalize Order");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> System.out.println("menu");//menu.displayMenu();
                case 2 -> System.out.println("ne vuyde");//addToOrder(scanner);
                case 3 -> System.out.println("order");//currentOrder.viewOrder();
                case 4 -> System.out.println("nema hroshey");//finalizeOrder();
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
    private void makeMenu() {
        List<Food> firstCourse = new ArrayList<>();
        List<Food> salads = new ArrayList<>();
        List<Food> hotAppetizers = new ArrayList<>();
        List<Food> mainCourses = new ArrayList<>();
        List<Food> sides = new ArrayList<>();
        List<Food> desserts = new ArrayList<>();
        List<Food> additional = new ArrayList<>();
        List<Drink> drinks = new ArrayList<>();

        // ПЕРША СТРАВА (First Course)
        firstCourse.add(new Food(150.0, "Борщ", false, false)); // Assuming not spicy
        firstCourse.add(new Food(160.0, "Грибний суп", false, false)); // Assuming not spicy
        firstCourse.add(new Food(140.0, "Картопляний суп", false, false)); // Assuming not spicy

        // САЛАТИ (Salads)
        salads.add(new Food(220.0, "Цезар", false, false));
        salads.add(new Food(180.0, "Грецький салат", true, false)); // Assuming vegetarian
        salads.add(new Food(150.0, "Олів'є", false, false));
        salads.add(new Food(120.0, "Салат з буряка з горіхами", true, false)); // Assuming vegetarian

        // ГАРЯЧІ ЗАКУСКИ (Hot Appetizers)
        hotAppetizers.add(new Food(160.0, "Вареники з картоплею", true, false)); // Assuming vegetarian
        hotAppetizers.add(new Food(180.0, "Вареники з м'ясом", false, false));
        hotAppetizers.add(new Food(140.0, "Деруни зі сметаною", true, false)); // Assuming vegetarian
        hotAppetizers.add(new Food(150.0, "Млинці з м'ясом", false, false));

        // ОСНОВНІ СТРАВИ (Main Courses)
        mainCourses.add(new Food(250.0, "Котлета по-київськи", false, false));
        mainCourses.add(new Food(200.0, "Свиняча відбивна", false, false));
        mainCourses.add(new Food(180.0, "Куряче філе гриль", false, false));
        mainCourses.add(new Food(220.0, "Печеня по-домашньому", false, false));

        // ГАРНІРИ (Sides)
        sides.add(new Food(80.0, "Картопляне пюре", true, false)); // Assuming vegetarian
        sides.add(new Food(90.0, "Смажена картопля", true, false)); // Assuming vegetarian
        sides.add(new Food(70.0, "Рис відварний", true, false)); // Assuming vegetarian
        sides.add(new Food(70.0, "Гречана каша", true, false)); // Assuming vegetarian

        // ДЕСЕРТИ (Desserts)
        desserts.add(new Food(120.0, "Сирник", true, false)); // Assuming vegetarian
        desserts.add(new Food(100.0, "Штрудель з яблуками", true, false)); // Assuming vegetarian
        desserts.add(new Food(80.0, "Морозиво", true, false)); // Assuming vegetarian

        // НАПОЇ (Drinks)
        drinks.add(new Drink(50.0, "Узвар", false,350));
        drinks.add(new Drink(60.0, "Сік", false,300));
        drinks.add(new Drink(70.0, "Кава", false, 500));
        drinks.add(new Drink(40.0, "Чай", false,200));

        // ДОДАТКОВО (Additional)
        additional.add(new Food(10.0, "Хліб", true, false)); // Assuming vegetarian
        additional.add(new Food(20.0, "Сметана", true, false)); // Assuming vegetarian


//        this.menu = new Menu(firstCourse, salads, hotAppetizers, mainCourses, sides, desserts, additional, drinks);
    }
}