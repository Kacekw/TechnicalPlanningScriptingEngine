package tpse.windows_layer_connector.sap;

import com.jacob.activeX.ActiveXComponent;

public class Session {

    private ActiveXComponent session;
    private ActiveXComponent sessionInfo;
    private ActiveXComponent window;
    private String windowTitle;

    public Session(ActiveXComponent session) {
        this.session = session;
        this.window = new ActiveXComponent(session.invoke("findById", "wnd[0]").toDispatch());
        windowTitle = window.getPropertyAsString("text");
    }

    public String getWindowTitle() {
        windowTitle = window.getPropertyAsString("text");
        return window.getPropertyAsString("text");
    }

    public String getSessionId() {
        return window.getPropertyAsString("id");
    }

    public String getTransactionName() {
        if (sessionInfo != null) sessionInfo.safeRelease();

        sessionInfo = new ActiveXComponent(session.invoke("info").toDispatch());
        return sessionInfo.getPropertyAsString("transaction");
    }


}
