package com.example.tin.pulselive.utils;

/**
 * Created by Tin on 19/08/2018.
 */

public class StateOfLoading {

    public static class stateCodes {

        public static final int LOADING = 0;
        public static final int LOADING_COMPLETE = 1;
        public static final int LOADING_ERROR = 2;

        public int code;
        public String message;

        public stateCodes(int i, String loadingState) {

            this.code = i;
            this.message = loadingState;
        }
    }
}
