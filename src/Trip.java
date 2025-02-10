public class Trip {
    private double distance;          // Расстояние в км
    private double fuelConsumption;   // Расход топлива (л/100 км)
    private double fuelPrice;         // Цена топлива (руб./л)
    private double additionalCosts;   // Дополнительные расходы

    private static int tripCounter = 0;  // Статический счётчик созданных поездок

    // Конструктор
    public Trip(double distance, double fuelConsumption, double fuelPrice, double additionalCosts) {
        this.distance = distance;
        this.fuelConsumption = fuelConsumption;
        this.fuelPrice = fuelPrice;
        this.additionalCosts = additionalCosts;
        tripCounter++;  // Увеличение счётчика при создании нового объекта
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

    public double getExtraExpenses() {
        return additionalCosts;
    }

    // Статический метод для возврата общего количества созданных поездок
    public static int getTripCount() {
        return tripCounter;
    }

    // Расчёт стоимости поездки
    public double calculateTotalCost() {
        double fuelCost = (distance * fuelConsumption / 100) * fuelPrice;
        return fuelCost + additionalCosts;
    }

    @Override
    public String toString() {
        // Использование оператора this для доступа к полям текущего объекта
        return String.format("Поездка: Расстояние: %.2f км, Расход: %.2f л/100 км, Цена: %.2f руб./л, Доп. расходы: %.2f руб., Итог: %.2f руб.",
                this.distance, this.fuelConsumption, this.fuelPrice, this.additionalCosts, this.calculateTotalCost());
    }
}
