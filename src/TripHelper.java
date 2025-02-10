public class TripHelper {
    // Метод для определения индекса самой дорогой поездки
    public static int getMostExpensiveTripIndex(Trip[] trips) {
        if (trips == null || trips.length == 0) {
            return -1;
        }
        int index = 0;
        double maxCost = trips[0].calculateTotalCost();
        for (int i = 1; i < trips.length; i++) {
            double cost = trips[i].calculateTotalCost();
            if (cost > maxCost) {
                maxCost = cost;
                index = i;
            }
        }
        return index;
    }
}
