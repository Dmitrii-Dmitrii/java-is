package ru.Onshin.Services;

import ru.Onshin.Clock.Clock;

public class ClockService {
    private Clock _clock;

    public ClockService() {
        _clock = Clock.getClock();
    }

    public void rewindDate(int daysAmount) {
        _clock.rewindTime(daysAmount);
    }
}
