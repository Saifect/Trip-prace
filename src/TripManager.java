import java.util.ArrayList;
import java.util.List;

public class TripManager {
    private final List<Trip> trips = new ArrayList<>();
    private Double fuelPriceDefault = null; // Константное значение цены бензина

    // Добавление поездки
    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    // Удаление поездки по индексу
    public void removeTrip(int index) {
        if (index > 0 && index <= trips.size()) {
            trips.remove(index - 1);
            System.out.println("Поездка удалена.");
        } else {
            System.out.println("Неверный номер поездки.");
        }
    }

    // Получение общего количества поездок
    public int getTripsCount() {
        return trips.size();
    }

    // Метод для получения массива объектов Trip (демонстрация работы с массивом)
    public Trip[] getTripsArray() {
        return trips.toArray(new Trip[trips.size()]);
    }

    // Показать список поездок (использование массива объектов)
    public void showTrips() {
        if (trips.isEmpty()) {
            System.out.println("Список поездок пуст.");
            return;
        }
        Trip[] tripsArray = getTripsArray();
        for (int i = 0; i < tripsArray.length; i++) {
            System.out.printf("%d. %s%n", i + 1, tripsArray[i].toString());
        }
    }

    // Новый метод для демонстрации обработки строк
    public void showTripsSummary() {
        if (trips.isEmpty()) {
            System.out.println("Список поездок пуст.");
            return;
        }
        StringBuilder summary = new StringBuilder();
        summary.append("Сводка поездок:\n");
        for (Trip trip : trips) {
            summary.append(String.format("Поездка: расстояние = %.2f км, итоговая стоимость = %.2f руб.\n",
                    trip.getDistance(), trip.calculateTotalCost()));
        }
        // Пример обработки строки: преобразование сводки в верхний регистр
        String result = summary.toString().toUpperCase();
        System.out.println(result);
    }

    // Очистка консоли
    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Управление константной ценой бензина
    public void setFuelPriceDefault(Double price) {
        this.fuelPriceDefault = price;
    }

    public void clearFuelPriceDefault() {
        this.fuelPriceDefault = null;
    }

    public Double getFuelPriceDefault() {
        return fuelPriceDefault;
    }
}
