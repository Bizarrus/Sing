package com.smule.samples.player;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;

public enum SimPlayerError implements IError {
    COULD_NOT_INITIALIZE(1, "Could not initialize"),
    COULD_NOT_START_PLAYER(2, "Could not start player"),
    COULD_NOT_STOP_PLAYER(3, "Could not stop player"),
    COULD_NOT_CLOSE_PLAYER(4, "Could not close player");
    
    private int f18387e;
    private int f18388f;
    private String f18389g;

    private SimPlayerError(int i, String str) {
        this.f18388f = i;
        this.f18389g = str;
        this.f18387e = ErrorHelper.m17582a("SIM_PLAYER_ERROR_CODE_OFFSET");
    }

    public String mo6239a() {
        return this.f18389g;
    }
}
