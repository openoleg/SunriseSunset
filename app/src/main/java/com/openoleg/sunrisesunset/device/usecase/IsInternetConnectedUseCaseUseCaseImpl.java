package com.openoleg.sunrisesunset.device.usecase;

import com.openoleg.sunrisesunset.domain.concurrency.MainThread;
import com.openoleg.sunrisesunset.domain.usecase.IsInternetConnectedUseCase;

import java.net.InetAddress;

public class IsInternetConnectedUseCaseUseCaseImpl implements IsInternetConnectedUseCase {
    private MainThread mainThread;

    public IsInternetConnectedUseCaseUseCaseImpl(MainThread mainThread) {
        this.mainThread = mainThread;
    }

    @Override
    public void run(IsInternetConnectedObserver observer) {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            if (!ipAddr.equals("")) {
                mainThread.post(observer::onConnected);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mainThread.post(observer::onDisconnected);
        }
    }
}
