package br.com.lifemove;

public interface SimpleAsynchronousTaskListener {

    void onSuccess();
    void onFailure(String reason);
}
