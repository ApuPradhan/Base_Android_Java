package com.apu.basejava.Interface;

public interface BaseCallBack<T> {
    void onComplete(boolean IsSuccess, T result);
}

