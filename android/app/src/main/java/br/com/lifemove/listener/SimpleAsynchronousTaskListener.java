package br.com.lifemove.listener;

public interface SimpleAsynchronousTaskListener {

    void onSuccess();
    void onFailure(String reason);
}
