package tpse.windows_layer_connector.sap;

import com.jacob.activeX.ActiveXComponent;

import java.util.HashMap;
import java.util.Map;

public class Sessions {

    private static ActiveXComponent sapGui = SapConnection.getSapGui();
    private Map<String, ActiveXComponent> sessions = new HashMap<>();

    public static Map<ActiveXComponent, String> getAllCurrentSessions() {
        ActiveXComponent connections = new ActiveXComponent(sapGui.invokeGetComponent("Connections"));
        Map<ActiveXComponent, String> tmpMap = new HashMap<>();

        for (int i = 0; i < connections.getPropertyAsInt("Count"); i++) {

            ActiveXComponent children = new ActiveXComponent(sapGui.invoke("Children", i).toDispatch());
            ActiveXComponent sessions = new ActiveXComponent(children.invoke("Sessions").toDispatch());

            for (int s = 0; s < sessions.getPropertyAsInt("Count"); s++) {

                ActiveXComponent session = new ActiveXComponent(children.invoke("Children", s).toDispatch());
                ActiveXComponent window = new ActiveXComponent(session.invoke("findById", "wnd[0]").toDispatch());
                String windowTitle = window.getPropertyAsString("text");
                tmpMap.put(window, windowTitle);
            }
        }

        return tmpMap;
    }
}
