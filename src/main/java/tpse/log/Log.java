package tpse.log;

import java.sql.Timestamp;

public class Log {

    //TODO task list update url is going to be used with scheduled_tab
    public static final String TASK_LIST_UPDATE_URL = "http://n41763.vestas.net/tasklist/";
    public static final String LOG_UPDATE_URL = "http://n41763.vestas.net/log/";

    private long id;
    private String user;
    private String module;
    private String backendStep;
    private Timestamp timestamp;

    private int orderNo;
    private String orderType;

    private LogTypes type;
    private LogSubTypes subType;

    private String additionalDescription;

    public Log() {
    }

    public Log(String user, String module, String backendStep, int orderNo, String orderType, LogTypes type, LogSubTypes subType, String additionalDescription) {
        this.user = user;
        this.module = module;
        this.backendStep = backendStep;
        this.orderNo = orderNo;
        this.orderType = orderType;
        this.type = type;
        this.subType = subType;
        this.additionalDescription = additionalDescription;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getBackendStep() {
        return backendStep;
    }

    public void setBackendStep(String backendStep) {
        this.backendStep = backendStep;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public LogTypes getType() {
        return type;
    }

    public void setType(LogTypes type) {
        this.type = type;
    }

    public LogSubTypes getSubType() {
        return subType;
    }

    public void setSubType(LogSubTypes subType) {
        this.subType = subType;
    }

    public String getAdditionalDescription() {
        return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
    }

    public void setId(long id) {
        this.id = id;
    }
}
