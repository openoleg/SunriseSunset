package com.openoleg.sunrisesunset.presentation.base.mvp;

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {
    protected V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
