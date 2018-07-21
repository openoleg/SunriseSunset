package com.openoleg.sunrisesunset.presentation.base.mvp;

public interface BaseContract {
    interface View {}

    interface Presenter<V extends View> {
        void attachView(V view);
        void detachView();
    }
}
