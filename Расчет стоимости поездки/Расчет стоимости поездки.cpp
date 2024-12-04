#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>

// Прототипы функций
void showMainMenu();
int safeIntInput(const char* prompt, int allowCancel);
double safeDoubleInput(const char* prompt, int allowCancel);
void waitForEnter();
void addNewTrip();
void showTrips();
void removeTrip();
void setDefaultFuelPrice();
void clearDefaultFuelPrice();

// Структуры
struct Trip {
    double distance;        // Расстояние (км)
    double fuelConsumption; // Расход топлива (л/100 км)
    double fuelPrice;       // Цена топлива (руб./л)
    double additionalCosts; // Дополнительные расходы
};

// Константы
const int MAX_TRIPS = 100;
Trip trips[MAX_TRIPS];
int tripCount = 0;
double fuelPriceDefault = -1.0; // -1 означает, что цена топлива не задана

int main() {
    setlocale(LC_ALL, "Rus");

    while (1) {
        showMainMenu();
        int choice = safeIntInput("Введите целое число: ", 1);
        switch (choice) {
        case 1:
            addNewTrip();
            waitForEnter();
            break;
        case 2:
            showTrips();
            waitForEnter();
            break;
        case 3:
            removeTrip();
            waitForEnter();
            break;
        case 4:
            setDefaultFuelPrice();
            waitForEnter();
            break;
        case 5:
            clearDefaultFuelPrice();
            waitForEnter();
            break;
        case 6:
            printf("Выход из программы...\n");
            return 0;
        default:
            printf("Некорректный ввод. Попробуйте снова.\n");
            waitForEnter();
        }
    }
    return 0;
}


// Функции
void showMainMenu() {
    system("cls"); // Очистка консоли
    printf("1. Новая поездка\n");
    printf("2. Показать список поездок\n");
    printf("3. Удалить поездку\n");
    printf("4. Установить константную цену бензина\n");
    printf("5. Удалить константную цену бензина\n");
    printf("6. Выход\n");
}

int safeIntInput(const char* prompt, int allowCancel) {
    char input[100];
    int value;

    while (1) {
        printf("%s", prompt);
        fgets(input, sizeof(input), stdin);

        if (allowCancel && strcmp(input, "отмена\n") == 0) {
            return -1; // Код для отмены
        }

        if (sscanf(input, "%d", &value) == 1) {
            return value;
        }
        else {
            printf("Ошибка ввода. Введите целое число.\n");
        }
    }
}

double safeDoubleInput(const char* prompt, int allowCancel) {
    char input[100];
    double value;

    while (1) {
        printf("%s", prompt);
        fgets(input, sizeof(input), stdin);

        if (allowCancel && strcmp(input, "отмена\n") == 0) {
            return -1.0; // Код для отмены
        }

        if (sscanf(input, "%lf", &value) == 1) {
            return value;
        }
        else {
            printf("Ошибка ввода. Введите число с плавающей точкой.\n");
        }
    }
}

void waitForEnter() {
    printf("Нажмите Enter для продолжения...\n");
    getchar();
}

void addNewTrip() {
    if (tripCount >= MAX_TRIPS) {
        printf("Достигнут максимальный лимит поездок.\n");
        return;
    }

    printf("Для отмены операции введите 'отмена' на любом этапе.\n");

    double distance = safeDoubleInput("Введите расстояние (км): ", 1);
    if (distance < 0) {
        printf("Добавление поездки отменено.\n");
        return;
    }

    double fuelConsumption = safeDoubleInput("Введите средний расход топлива (л/100 км): ", 1);
    if (fuelConsumption < 0) {
        printf("Добавление поездки отменено.\n");
        return;
    }

    double fuelPrice;
    if (fuelPriceDefault >= 0) {
        fuelPrice = fuelPriceDefault;
        printf("Используется константная цена бензина: %.2f руб./л\n", fuelPrice);
    }
    else {
        fuelPrice = safeDoubleInput("Введите стоимость топлива (руб./л): ", 1);
        if (fuelPrice < 0) {
            printf("Добавление поездки отменено.\n");
            return;
        }
    }

    double additionalCosts = safeDoubleInput("Введите дополнительные расходы (руб.): ", 1);
    if (additionalCosts < 0) {
        printf("Добавление поездки отменено.\n");
        return;
    }

    trips[tripCount] = { distance, fuelConsumption, fuelPrice, additionalCosts };
    double totalCost = (distance * fuelConsumption / 100) * fuelPrice + additionalCosts;
    printf("Поездка добавлена. Итоговая стоимость: %.2f руб.\n", totalCost);
    tripCount++;
}

void showTrips() {
    if (tripCount == 0) {
        printf("Список поездок пуст.\n");
        return;
    }

    for (int i = 0; i < tripCount; i++) {
        double totalCost = (trips[i].distance * trips[i].fuelConsumption / 100) * trips[i].fuelPrice + trips[i].additionalCosts;
        printf("%d. Расстояние: %.2f км, Расход топлива: %.2f л/100 км, Цена топлива: %.2f руб./л, Доп. расходы: %.2f руб., Итог: %.2f руб.\n",
            i + 1, trips[i].distance, trips[i].fuelConsumption, trips[i].fuelPrice, trips[i].additionalCosts, totalCost);
    }
}

void removeTrip() {
    showTrips();
    if (tripCount == 0) {
        return;
    }

    printf("Для отмены операции введите 'отмена'.\n");
    int index = safeIntInput("Введите номер поездки для удаления: ", 1);
    if (index == -1) {
        printf("Удаление поездки отменено.\n");
        return;
    }

    if (index < 1 || index > tripCount) {
        printf("Неверный номер поездки.\n");
        return;
    }

    for (int i = index - 1; i < tripCount - 1; i++) {
        trips[i] = trips[i + 1];
    }
    tripCount--;
    printf("Поездка удалена.\n");
}

void setDefaultFuelPrice() {
    double price = safeDoubleInput("Введите константную цену бензина (руб./л): ", 0);
    fuelPriceDefault = price;
    printf("Установлена константная цена бензина: %.2f руб./л\n", price);
}

void clearDefaultFuelPrice() {
    fuelPriceDefault = -1.0;
    printf("Константная цена бензина удалена.\n");
}
