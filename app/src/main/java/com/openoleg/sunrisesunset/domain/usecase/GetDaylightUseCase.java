package com.openoleg.sunrisesunset.domain.usecase;

import com.openoleg.sunrisesunset.domain.concurrency.MainThread;
import com.openoleg.sunrisesunset.domain.model.Daylight;
import com.openoleg.sunrisesunset.domain.repository.DaylightRepository;

import javax.inject.Inject;

public class GetDaylightUseCase {
    public interface GetDaylightUseCaseObserver {
        void onResult(Daylight daylight);
    }

    private DaylightRepository daylightRepository;
    private MainThread mainThread;

    @Inject
    public GetDaylightUseCase(DaylightRepository daylightRepository, MainThread mainThread) {
        this.daylightRepository = daylightRepository;
        this.mainThread = mainThread;
    }

    public void run(float lat, float lng, GetDaylightUseCaseObserver observer) {
        Daylight daylight = daylightRepository.getDaylight(lat, lng);
        mainThread.post(() -> observer.onResult(daylight));
    }
}
