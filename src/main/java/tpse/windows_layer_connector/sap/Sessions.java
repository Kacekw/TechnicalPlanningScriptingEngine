package tpse.windows_layer_connector.sap;

import com.jacob.activeX.ActiveXComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sessions {

    private ActiveXComponent sapGui;
    private List<Map<Integer, ActiveXComponent>> sessions = new ArrayList<>();
    ;

    public Sessions(ActiveXComponent sapGui) {
        this.sapGui = sapGui;
    }

    public void printConnections() {
        ActiveXComponent connections = new ActiveXComponent(sapGui.invoke("Connections").toDispatch());
        System.out.println(connections.getProperty("Count"));
        for (int i = 0; i < connections.getPropertyAsInt("Count"); i++) {
            ActiveXComponent children = new ActiveXComponent(sapGui.invoke("Children", i).toDispatch());
            ActiveXComponent sessions = new ActiveXComponent(children.invoke("Sessions").toDispatch());
            System.out.println(sessions.getProperty("Count"));
            for (int s = 0; s < sessions.getPropertyAsInt("Count"); s++) {
                ActiveXComponent session = new ActiveXComponent(children.invoke("Children", s).toDispatch());
                ActiveXComponent window = new ActiveXComponent(session.invoke("findById", "wnd[0]").toDispatch());
                System.out.println(window.getPropertyAsString("text"));
            }
        }
    }

    private List<Map> getAllCurrentSessions() {
        ActiveXComponent connections = new ActiveXComponent(sapGui.invoke("Connections").toDispatch());

        for (int i = 0; i < connections.getPropertyAsInt("Count"); i++) {

            Map<Integer, ActiveXComponent> tmpMap = new HashMap<>();

            ActiveXComponent children = new ActiveXComponent(sapGui.invoke("Children", i).toDispatch());
            ActiveXComponent sessions = new ActiveXComponent(children.invoke("Sessions").toDispatch());

            for (int s = 0; s < sessions.getPropertyAsInt("Count"); s++) {

                ActiveXComponent session = new ActiveXComponent(children.invoke("Children", s).toDispatch());
                ActiveXComponent window = new ActiveXComponent(session.invoke("findById", "wnd[0]").toDispatch());

                System.out.println(window.getPropertyAsString("text"));
            }
        }
    }
