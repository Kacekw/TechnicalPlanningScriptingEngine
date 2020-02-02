package tpse.windows_layer_connector.sap;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class SapConnection {

    private ActiveXComponent SapGui;


    public void disconnect() {
        ComThread.Release();
    }

    public ActiveXComponent getSapGui() throws NoSuchFieldException {
        ComThread.InitSTA();
        try {
            ActiveXComponent sapRotWrapper = new ActiveXComponent("SapROTWr.SapROTWrapper");
            Dispatch rotEntry = sapRotWrapper.invoke("GetROTEntry", "SAPGUI").toDispatch();
            Variant scriptEngine = Dispatch.call(rotEntry, "GetScriptingEngine");
            SapGui = new ActiveXComponent(scriptEngine.toDispatch());
            return SapGui;
        } catch (ComFailException cfe) {
            System.out.println(cfe.getMessage());
            throw new NoSuchFieldException();
        }

    }
}
