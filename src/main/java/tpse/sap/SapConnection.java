package tpse.sap;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class SapConnection {

    private ActiveXComponent SapRotWrapper, SapGui;
    private Dispatch RotEntry;
    private Variant ScriptEngine;

    public SapConnection() {
        ComThread.InitSTA();

        SapRotWrapper = new ActiveXComponent("SapROTWr.SapROTWrapper");
        try {
            RotEntry = SapRotWrapper.invoke("GetROTEntry", "SAPGUI").toDispatch();
            ScriptEngine = Dispatch.call(RotEntry, "GetScriptingEngine");
            SapGui = new ActiveXComponent(ScriptEngine.toDispatch());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ComThread.Release();
        }

    }

    public ActiveXComponent getSapGui() {
        return SapGui;
    }
}
