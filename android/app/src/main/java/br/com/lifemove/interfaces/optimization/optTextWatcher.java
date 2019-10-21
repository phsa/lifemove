package br.com.lifemove.interfaces.optimization;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class optTextWatcher implements TextWatcher {

    @Override
    public abstract void onTextChanged(CharSequence charSequence, int i, int i1, int i2);

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) { }
}
