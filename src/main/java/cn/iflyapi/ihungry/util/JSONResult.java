package cn.iflyapi.ihungry.util;

/**
 * @author: qfwang
 * @date: 2018-11-04 10:50 AM
 */
public class JSONResult {
    private int code;
    private String msg;
    private Boolean success;
    private Object data;

    public JSONResult(int code, String msg, Boolean success, Object data) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public static JSONResult success(String msg, Object data) {
        return new JSONResult(200, "success", true, data);
    }

    public static JSONResult success() {
        return success(null);
    }

    public static JSONResult success(String msg) {
        return success(msg, null);
    }

    public static JSONResult success(Object data) {
        return success("success", data);
    }

    public static JSONResult fail(String msg, Object data) {
        return new JSONResult(400, msg, false, data);
    }

    public static JSONResult fail(String msg) {
        return fail(msg, null);
    }

    public static JSONResult fail() {
        return fail("fail", null);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
