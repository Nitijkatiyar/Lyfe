package com.bst.networkutils;

import android.util.Log;

import org.json.JSONException;

import java.io.PrintStream;

/**
 * Created by Akhilesh on 26/10/2015.
 */
public class JsonParsingException extends JSONException {
    private String message = null;

    public JsonParsingException(String message) {
        super(message);
        this.message = message;
        logMessage(message);
    }

    private void logMessage(String TAG, String error) {
        Log.e(TAG, error);
    }

    private void logMessage(String error) {
        Log.e("JsonParsingException::", error);
    }

    private void logMessage(String message, Throwable cause) {
        Log.e(message, cause.getMessage());
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void printStackTrace(PrintStream err) {
        super.printStackTrace(err);
    }
}
