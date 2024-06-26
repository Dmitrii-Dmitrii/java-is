package ru.Onshin.Clock;

import ru.Onshin.CentralBank.CentralBank;

import java.time.LocalDate;

public class Clock {
    private LocalDate _date;
    private static Clock _clock;

    private Clock() {
        _date = LocalDate.now();
    }

    public static Clock getClock() {
        if (_clock == null)
            _clock = new Clock();

        return _clock;
    }

    public LocalDate getDate() { return _date; }

    /**
     * Осуществляет работу механизма ускорения времени
     *
     * @param daysAmount количество дней, на которое нужно сдвинуть текущую дату
     */
    public void rewindTime(int daysAmount) {
        System.out.println("Old date: " + _date);
        for (int i = 0; i < daysAmount; i++) {
            CentralBank.getCentralBank().calculateDailyInterest();
            _date = _date.plusDays(1);
            if (_date.getDayOfMonth() == 1) {
                CentralBank.getCentralBank().calculateMonthlyInterest();
            }
        }

        System.out.println("Current date: " + _date);
    }
}
