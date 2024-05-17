package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.json.JSONObject;

@Getter
@Setter
public class UserMsg {
    public static final String UA_UPDATE = "update";
    public static final String UA_DELETE = "delete";

    private String action;
    private Long userId;
    private String traceId;

    public UserMsg(){

    }

    public UserMsg(String action, Long userId, String traceId){
        this.action = action;
        this.userId = userId;
        this.traceId = traceId;
    }

    @SneakyThrows
    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("action", getAction());
        object.put("userId", getUserId());
        object.put("traceId", getTraceId());
        return object.toString();
    }
}
