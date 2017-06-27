package impl;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.liglab.adele.cream.annotations.provider.OriginEnum;
import fr.liglab.adele.cream.model.introspection.EntityProvider;
import fr.liglab.adele.cream.model.introspection.RelationProvider;
import fr.liglab.adele.icasa.context.manager.api.generic.Util;
import fr.liglab.adele.icasa.context.manager.api.generic.models.CapabilityModelAccess;
import fr.liglab.adele.icasa.context.manager.api.generic.models.ExternalFilterModelAccess;
import fr.liglab.adele.icasa.context.manager.api.generic.models.goals.ContextAPIConfig;
import fr.liglab.adele.icasa.context.manager.api.generic.models.goals.GoalModelAccess;
import fr.liglab.adele.icasa.context.manager.api.specific.ContextAPIEnum;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.content.Json;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import util.am.MonitoredGoal;

import java.util.Map;
import java.util.Set;


@Controller
public class ManagerController extends DefaultController {

    @Requires
    @SuppressWarnings("unused")
    private Json json;

    @Requires(optional = true)
    @SuppressWarnings("unused")
    private GoalModelAccess goalModel;

    @Requires(optional = true)
    @SuppressWarnings("unused")
    private CapabilityModelAccess capabilityModel;

    @Requires(optional = true)
    @SuppressWarnings("unused")
    private ExternalFilterModelAccess externalFilterModel;


    @Route(method = HttpMethod.GET, uri = "/manager/goals")
    public Result getGoals() {

        ArrayNode result = json.newArray();

        if(goalModel != null){
            for(Map.Entry<String, ContextAPIConfig> goalByApp: goalModel.getGoalsByApp().entrySet()){
                String app = goalByApp.getKey();
                Map<ContextAPIEnum, Boolean> value = goalModel.getGoalsStateForApp(app);
                result.add(json.toJson(new MonitoredGoal(app, value)));
            }
        }

        return ok(result);
    }

    /*ToDo*/
    @Route(method = HttpMethod.GET, uri = "/manager/capabilities/internal")
    public Result getInternalCapabilities(){
        return ok(getCapabilities(OriginEnum.internal));
    }

    /*ToDo*/
    @Route(method = HttpMethod.GET, uri = "/manager/capabilities/local")
    public Result getLocalCapabilities(){
        return ok(getCapabilities(OriginEnum.local));
    }

    /*ToDo*/
    @Route(method = HttpMethod.GET, uri = "/manager/capabilities/remote")
    public Result getRemoteCapabilities(){
        return ok(getCapabilities(OriginEnum.remote));
    }

    private ObjectNode getCapabilities(OriginEnum origin){
        ObjectNode result = json.newObject();
        ObjectNode entityProviders = json.newObject();
        ObjectNode relationProviders = json.newObject();

        if(capabilityModel != null){
            for(Map.Entry<EntityProvider, Set<String>> entry : capabilityModel.getEntitiesByProvider(origin).entrySet()){
                ObjectNode entities = json.newObject();
                EntityProvider provider = entry.getKey();
                for(String item : entry.getValue()){
                    String creatorName = Util.creatorName(provider, item);
                    entities.put(creatorName, capabilityModel.getInstancesByCreator(creatorName).toString());
                }
                String providerName = provider.getName();
                entityProviders.putObject(providerName);
                entityProviders.set(providerName, entities);
            }
        }
        result.putObject("Entities");
        result.set("Entities", entityProviders);

        if(capabilityModel != null){
            for(Map.Entry<RelationProvider, Set<String>> entry : capabilityModel.getRelationsByProvider(origin).entrySet()){
                ObjectNode entities = json.newObject();
                RelationProvider provider = entry.getKey();
                for(String item : entry.getValue()){
                    String creatorName = Util.creatorName(provider, item);
                    entities.put(creatorName, capabilityModel.getInstancesByCreator(creatorName).toString());
                }
                String providerName = provider.getName();
                relationProviders.putObject(providerName);
                relationProviders.set(providerName, entities);
            }
        }
        result.putObject("Relations");
        result.set("Relations", relationProviders);

        return result;
    }


    @Route(method = HttpMethod.GET, uri = "/manager/filters")
    public Result getExternalFilter() {
        ArrayNode result = json.newArray();

        if(externalFilterModel != null){
            for(String service: externalFilterModel.getLookupFilter()){
                result.add(service);
            }
        }

        return ok(result);
    }
}
