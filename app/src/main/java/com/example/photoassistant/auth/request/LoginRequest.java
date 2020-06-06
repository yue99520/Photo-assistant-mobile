package com.example.photoassistant.auth.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.photoassistant.config.App;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static String logTag = LoginRequest.class.getSimpleName();

    private Data data;

    public LoginRequest(Data data, LoginListener loginListener) {
        super(Method.POST, App.LOGIN_URL, new ResponseListener(loginListener), new ErrorListener());
        this.data = data;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        String json = "{\n" +
                "\t\"email\": \"" + data.email + " \",\n" +
                "\t\"password\": \"" + data.password + "\",\n" +
                "\t\"device_name\": \"" + data.deviceName + "\"\n" +
                "}";

        return json.getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put("Connection", "keep-alive");
        params.put("Accept", "application/json");
        params.put("Content-Type", "application/json");

        return params;
    }

    public interface LoginListener {

        void loginResult(LoginRequest.Result result);
    }

    public static class Data {
        public String email = "";
        public String password = "";
        public String deviceName = "";
    }

    public static class Result {
        private String token = "";
        private boolean success = false;
        private String msg = "";

        public String getToken() {
            return this.token;
        }

        public void setToken(String token) {
            if (token != null && token.length() != 0) {
                this.token = "Bearer " + token.split("\\|")[1].trim();
            }
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    static class ResponseListener implements Response.Listener<String> {

        private Result result;

        private LoginListener loginListener;

        ResponseListener(LoginListener loginListener) {
            this.result = new Result();
            this.loginListener = loginListener;
        }

        @Override
        public void onResponse(String response) {

            try {
                JSONObject jsonObject = new JSONObject(response);
                result.setSuccess(jsonObject.getBoolean("success"));
                result.setToken(jsonObject.getString("token"));
                result.setMsg(jsonObject.getString("message"));

                loginListener.loginResult(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    static class ErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
//            Log.d(logTag, new String(error.networkResponse.data));
        }
    }
}
