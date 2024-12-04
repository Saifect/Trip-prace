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

    // Показать список поездок
    public void showTrips() {
        if (trips.isEmpty()) {
            System.out.println("Список поездок пуст.");
            return;
        }
        for (int i = 0; i < trips.size(); i++) {
            Trip trip = trips.get(i);
            System.out.printf("%d. Расстояние: %.2f км, Расход топлива: %.2f л/100 км, Цена топлива: %.2f руб./л, Доп. расходы: %.2f руб., Итог: %.2f руб.%n",
                    i + 1, trip.getDistance(), trip.getFuelConsumption(), trip.getFuelPrice(), trip.getExtraExpenses(), trip.calculateTotalCost());
        }
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

    public int getTripsCount() {
        return trips.size();
    }
}

