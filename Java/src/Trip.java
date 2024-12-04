// Класс Trip хранит информацию о поездке
public class Trip {
    private double distance; // Расстояние в км
    private double fuelConsumption; // Расход топлива (л/100 км)
    private double fuelPrice; // Цена топлива (руб./л)
    private double additionalCosts; // Дополнительные расходы

    // Конструктор
    public Trip(double distance, double fuelConsumption, double fuelPrice, double additionalCosts) {
        this.distance = distance;
        this.fuelConsumption = fuelConsumption;
        this.fuelPrice = fuelPrice;
        this.additionalCosts = additionalCosts;
    }

    // Геттеры для всех полей
    public double getDistance() {
        return distance;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public double getFuelPrice() {
        return fuelPrice;
    }

    public double getExtraExpenses() { // Этот метод был пропущен
        return additionalCosts;
    }

    // Расчёт стоимости поездки
    public double calculateTotalCost() {
        double fuelCost = (distance * fuelConsumption / 100) * fuelPrice;
        return fuelCost + additionalCosts;
    }
}
