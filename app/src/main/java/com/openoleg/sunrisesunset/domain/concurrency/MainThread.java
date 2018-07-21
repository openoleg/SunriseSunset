package com.openoleg.sunrisesunset.domain.concurrency;

public interface MainThread {
    void post(Runnable runnable);
}
