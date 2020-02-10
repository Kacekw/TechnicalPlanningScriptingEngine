package tpse.windows_layer_connector.sap;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class SapConnection {

    private static ActiveXComponent SapGui;


    public static void disconnect() {
        ComThread.Release();
    }

    public static ActiveXComponent getSapGui() {
        ComThread.InitSTA();
        try {
            ActiveXComponent sapRotWrapper = new ActiveXComponent("SapROTWr.SapROTWrapper");
            Dispatch rotEntry = sapRotWrapper.invoke("GetROTEntry", "SAPGUI").toDispatch();
            Variant scriptEngine = Dispatch.call(rotEntry, "GetScriptingEngine");
            SapGui = new ActiveXComponent(scriptEngine.toDispatch());
            return SapGui;
        } catch (ComFailException cfe) {
            System.out.println(cfe.getMessage());
            return SapGui;
        }

    }
}
