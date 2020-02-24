package tpse.windows_layer_connector.sap;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class SapConnection {

    /**
     * Class obtains a SapScripting object from the ROT table, further operations are done using that object
     */

    private static ActiveXComponent SapGui;

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

    /**
     * disconnect() method provides option to simply release the thread and end connection to SAP ERP system so that it would end properly
     **/
    public static void disconnect() {
        ComThread.Release();
    }
}
