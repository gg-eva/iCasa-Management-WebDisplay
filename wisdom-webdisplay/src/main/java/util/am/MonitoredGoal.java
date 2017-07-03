package util.am;

import fr.liglab.adele.icasa.context.manager.api.config.ContextAPIEnum;

import java.util.Map;

public class MonitoredGoal {

    private String app;
    private Map<ContextAPIEnum, Boolean> goals;

    public MonitoredGoal(String app, Map<ContextAPIEnum, Boolean> goals) {
        this.app = app;
        this.goals = goals;
    }

    public String getApp() {
        return app;
    }

    public Map<ContextAPIEnum, Boolean> getGoals() {
        return goals;
    }
}
