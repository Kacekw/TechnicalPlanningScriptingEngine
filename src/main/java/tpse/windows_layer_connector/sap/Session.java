package tpse.windows_layer_connector.sap;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.SafeArray;
import com.jacob.com.Variant;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

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
        refreshSessionInfo();
        return sessionInfo.getPropertyAsString("transaction");
    }

    public Integer getScreenNumber() {
        refreshSessionInfo();
        return sessionInfo.getPropertyAsInt("ScreenNumber");
    }

    public void refreshSessionInfo() {
        if (sessionInfo != null) sessionInfo.safeRelease();

        sessionInfo = new ActiveXComponent(session.invoke("info").toDispatch());
    }

    private byte[] getPreviewAsByteArray() {
        Variant variant = window.getProperty("HardCopyToMemory");
        SafeArray safeArray = variant.toSafeArray();

        byte[] byteAr = safeArray.toByteArray();

        safeArray.safeRelease();
        variant.safeRelease();

        return byteAr;
    }

    public Image getPreviewImageOfSapSession() {
        return new Image(new ByteArrayInputStream(getPreviewAsByteArray()));
    }

}
