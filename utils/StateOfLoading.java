package com.example.tin.pulselive.utils;


public class StateOfLoading {

    public static class stateCodes {

        public static final int LOADING = 0;
        public static final int LOADING_COMPLETE = 1;
        public static final int LOADING_ERROR = 2;

        public final int code;
        public final String message;

        public stateCodes(int i, String loadingState) {

            this.code = i;
            this.message = loadingState;
        }
    }
}
