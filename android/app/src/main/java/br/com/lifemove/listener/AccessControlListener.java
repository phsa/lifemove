package br.com.lifemove.listener;

public interface AccessControlListener extends SimpleAsynchronousTaskListener {

    void handleUsernameCheck(String checkedUsername, boolean allowed);
}
