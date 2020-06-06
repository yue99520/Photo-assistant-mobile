package com.example.photoassistant.auth.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.photoassistant.config.App;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CheckLoginRequest extends StringRequest {

    private static String logTag = CheckLoginRequest.class.getSimpleName();

    private String token;

    public CheckLoginRequest(String token, CheckLoginRequest.CheckLoginListener listener) {
        super(Method.POST, App.LOGIN_CHECK_URL, new ResponseListener(listener), null);
        this.token = token;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put("Connection", "keep-alive");
        params.put("Accept", "application/json");
        params.put("Content-Type", "application/json");
        params.put("Authorization", token);

        return params;
    }

    public interface CheckLoginListener {
        void checkResult(boolean bool);
    }

    public static class ResponseListener implements Response.Listener<String> {

        private CheckLoginListener checkLoginListener;

        public ResponseListener(CheckLoginListener checkLoginListener) {
            this.checkLoginListener = checkLoginListener;
        }

        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);

                boolean result = jsonObject.getBoolean("success");

                checkLoginListener.checkResult(result);
            } catch (JSONException e) {
                e.printStackTrace();
                checkLoginListener.checkResult(false);
            }
        }
    }
}
