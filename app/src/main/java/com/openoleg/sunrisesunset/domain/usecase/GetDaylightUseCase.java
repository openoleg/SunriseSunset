package com.openoleg.sunrisesunset.domain.usecase;

import com.openoleg.sunrisesunset.domain.concurrency.MainThread;
import com.openoleg.sunrisesunset.domain.model.Daylight;
import com.openoleg.sunrisesunset.domain.repository.DaylightRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public void run(float lat, float lng, Date date, GetDaylightUseCaseObserver observer) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateISO = simpleDateFormat.format(date);
        Daylight daylight = daylightRepository.getDaylight(lat, lng, dateISO);
        mainThread.post(() -> observer.onResult(daylight));
    }
}
