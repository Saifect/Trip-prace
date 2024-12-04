import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TripManager manager = new TripManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            manager.clearConsole();
            System.out.println("1. Новая поездка");
            System.out.println("2. Показать список поездок");
            System.out.println("3. Удалить поездку");
            System.out.println("4. Установить константную цену бензина");
            System.out.println("5. Удалить константную цену бензина");
            System.out.println("6. Выход");

            int choice = safeIntInput(scanner, "Введите целое число: ", true);
            switch (choice) {
                case 1:
                    if (!addNewTrip(scanner, manager)) {
                        System.out.println("Добавление поездки отменено.");
                    }
                    waitForEnter(scanner);
                    break;

                case 2:
                    manager.clearConsole();
                    manager.showTrips();
                    waitForEnter(scanner);
                    break;

                case 3:
                    manager.clearConsole();
                    if (!removeTrip(scanner, manager)) {
                        System.out.println("Удаление поездки отменено.");
                    }
                    waitForEnter(scanner);
                    break;

                case 4:
                    setDefaultFuelPrice(scanner, manager);
                    waitForEnter(scanner);
                    break;

                case 5:
                    manager.clearFuelPriceDefault();
                    System.out.println("Константная цена бензина удалена.");
                    waitForEnter(scanner);
                    break;

                case 6:
                    System.out.println("Выход из программы...");
                    return;

                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
                    waitForEnter(scanner);
            }
        }
    }

    private static boolean addNewTrip(Scanner scanner, TripManager manager) {
        System.out.println("Для отмены операции введите 'отмена' на любом этапе.");

        Double distance = safeDoubleInput(scanner, "Введите расстояние (км): ", true);
        if (distance == null) return false;

        Double fuelConsumption = safeDoubleInput(scanner, "Введите средний расход топлива (л/100 км): ", true);
        if (fuelConsumption == null) return false;

        Double fuelPrice;
        if (manager.getFuelPriceDefault() != null) {
            fuelPrice = manager.getFuelPriceDefault();
            System.out.printf("Используется константная цена бензина: %.2f руб./л%n", fuelPrice);
        } else {
            fuelPrice = safeDoubleInput(scanner, "Введите стоимость топлива (руб./л): ", true);
            if (fuelPrice == null) return false;
        }

        Double extraExpenses = safeDoubleInput(scanner, "Введите дополнительные расходы (руб.): ", true);
        if (extraExpenses == null) return false;

        Trip trip = new Trip(distance, fuelConsumption, fuelPrice, extraExpenses);
        manager.addTrip(trip);
        System.out.printf("Поездка добавлена. Итоговая стоимость: %.2f руб.\n", trip.calculateTotalCost());
        return true;
    }

    private static void setDefaultFuelPrice(Scanner scanner, TripManager manager) {
        Double price = safeDoubleInput(scanner, "Введите константную цену бензина (руб./л): ", false);
        manager.setFuelPriceDefault(price);
        System.out.printf("Установлена константная цена бензина: %.2f руб./л%n", price);
    }

    private static void waitForEnter(Scanner scanner) {
        System.out.println("Нажмите Enter для продолжения...");
        scanner.nextLine(); // Ожидание нажатия Enter
    }

    private static Integer safeIntInput(Scanner scanner, String prompt, boolean allowCancel) {
        while (true) {
            System.out.print(prompt);
            if (allowCancel && scanner.hasNext("отмена")) {
                scanner.nextLine(); // Очистка ввода
                return null;
            }
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // Очистка потока после ввода
                return value;
            } else {
                scanner.nextLine(); // Очистка некорректного ввода
                System.out.println("Ошибка ввода. Введите целое число.");
            }
        }
    }

    private static boolean removeTrip(Scanner scanner, TripManager manager) {
        manager.showTrips();
        if (manager.getTripsCount() == 0) {
            return true;
        }

        System.out.println("Для отмены операции введите 'отмена'.");
        Integer index = safeIntInput(scanner, "Введите номер поездки для удаления: ", true);
        if (index == null) return false;

        manager.removeTrip(index); // Вызов removeTrip через manager
        return true;
    }

    private static Double safeDoubleInput(Scanner scanner, String prompt, boolean allowCancel) {
        while (true) {
            System.out.print(prompt);
            if (allowCancel && scanner.hasNext("отмена")) {
                scanner.nextLine(); // Очистка ввода
                return null;
            }
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine(); // Очистка потока после ввода
                return value;
            } else {
                scanner.nextLine(); // Очистка некорректного ввода
                System.out.println("Ошибка ввода. Введите число с плавающей точкой.");
            }
        }
    }
}
