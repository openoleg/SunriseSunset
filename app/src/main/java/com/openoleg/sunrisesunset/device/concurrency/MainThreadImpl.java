package com.openoleg.sunrisesunset.device.concurrency;

import android.os.Handler;
import android.os.Looper;

import com.openoleg.sunrisesunset.domain.concurrency.MainThread;

public class MainThreadImpl implements MainThread {
    private Handler handler;

    public MainThreadImpl() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
