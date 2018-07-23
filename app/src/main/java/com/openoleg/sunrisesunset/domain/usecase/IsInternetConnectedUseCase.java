package com.openoleg.sunrisesunset.domain.usecase;

public interface IsInternetConnectedUseCase {
    interface IsInternetConnectedObserver {
        void onConnected();
        void onDisconnected();
    }

    void run(IsInternetConnectedObserver observer);
}
