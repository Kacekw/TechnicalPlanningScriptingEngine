package tpse.windows_layer_connector.sap;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class SapConnection {

    private ActiveXComponent SapGui;

    public SapConnection() {
        ComThread.InitSTA();

        ActiveXComponent sapRotWrapper = new ActiveXComponent("SapROTWr.SapROTWrapper");
        try {
            Dispatch rotEntry = sapRotWrapper.invoke("GetROTEntry", "SAPGUI").toDispatch();
            Variant scriptEngine = Dispatch.call(rotEntry, "GetScriptingEngine");
            SapGui = new ActiveXComponent(scriptEngine.toDispatch());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void disconnect() {
        ComThread.Release();
    }

    public ActiveXComponent getSapGui() {
        return SapGui;
    }
}
