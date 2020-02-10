package tpse.windows_layer_connector.sap;

import com.jacob.activeX.ActiveXComponent;

import java.util.*;

public class Sessions {

    private static ActiveXComponent sapGui = SapConnection.getSapGui();
    private Map<String, ActiveXComponent> sessions = new HashMap<>();

    public static List<Session> getAllCurrentSessions() {
        ActiveXComponent connections = new ActiveXComponent(sapGui.invokeGetComponent("Connections"));
        List<Session> tmpList = new ArrayList<>();

        for (int i = 0; i < connections.getPropertyAsInt("Count"); i++) {

            ActiveXComponent children = new ActiveXComponent(sapGui.invoke("Children", i).toDispatch());
            ActiveXComponent sessions = new ActiveXComponent(children.invoke("Sessions").toDispatch());

            for (int s = 0; s < sessions.getPropertyAsInt("Count"); s++) {

                Session session = new Session(new ActiveXComponent(children.invoke("Children", s).toDispatch()));
                tmpList.add(session);
            }
        }

        return tmpList;
    }
}
