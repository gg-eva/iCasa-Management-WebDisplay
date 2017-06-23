package impl;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.liglab.adele.cream.model.introspection.EntityProvider;
import fr.liglab.adele.cream.model.introspection.RelationProvider;
import fr.liglab.adele.icasa.context.manager.api.generic.models.ExternalFilterModelAccess;
import fr.liglab.adele.icasa.context.manager.api.generic.models.goals.ContextAPIConfig;
import fr.liglab.adele.icasa.context.manager.api.generic.models.goals.GoalModelAccess;
import fr.liglab.adele.icasa.context.manager.api.specific.ContextAPIEnum;
import fr.liglab.adele.icasa.context.manager.api.web.administration.ContextManagerWebMonitoring;
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
    private ContextManagerWebMonitoring contextManagerWebMonitoring;

    @Requires(optional = true)
    @SuppressWarnings("unused")
    private GoalModelAccess goalModel;

    @Requires
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

    /*ToDo MODIFY MODELS TO BE DISPLAYED*/

    @Route(method = HttpMethod.GET, uri = "/manager/resources")
    public Result getResources() {
        ObjectNode result = json.newObject();
        ObjectNode entityProviders = json.newObject();
        ObjectNode relationProviders = json.newObject();

        if(contextManagerWebMonitoring != null){
            for(Map.Entry<EntityProvider, Set<String>> entityProviderSetEntry: contextManagerWebMonitoring.getResourceCreatorsByEntityProvider().entrySet()){
                ObjectNode entities = json.newObject();
                for(String creatorName : entityProviderSetEntry.getValue()){
                    entities.put(creatorName, contextManagerWebMonitoring.getInstancesByCreator(creatorName).toString());
                }
                if(entities.size()!=0){
                    String provider = entityProviderSetEntry.getKey().getName();
                    entityProviders.putObject(provider);
                    entityProviders.set(provider, entities);
                }
            }
        }
        result.putObject("Entities");
        result.set("Entities", entityProviders);

        if(contextManagerWebMonitoring != null){
            for(Map.Entry<RelationProvider, Set<String>> relationProviderSetEntry: contextManagerWebMonitoring.getResourceCreatorsByRelationProvider().entrySet()){
                ObjectNode relations = json.newObject();
                for(String creator : relationProviderSetEntry.getValue()){
                    relations.put(creator, contextManagerWebMonitoring.getInstancesByCreator(creator).toString());
                }
                if(relations.size()!=0){
                    String provider = relationProviderSetEntry.getKey().getName();
                    relationProviders.putObject(provider);
                    relationProviders.set(provider, relations);
                }
            }
        }
        result.putObject("Relations");
        result.set("Relations", relationProviders);

        return ok(result);
    }

    @Route(method = HttpMethod.GET, uri = "/manager/abstractions")
    public Result getAbstractions() {
        ObjectNode result = json.newObject();
        ObjectNode entityProviders = json.newObject();
        ObjectNode relationProviders = json.newObject();

        if(contextManagerWebMonitoring != null){
            for(Map.Entry<EntityProvider, Set<String>> entityProviderSetEntry: contextManagerWebMonitoring.getAbstractionCreatorsByEntityProvider().entrySet()){
                ObjectNode entities = json.newObject();
                for(String creator : entityProviderSetEntry.getValue()){
                    entities.put(creator, contextManagerWebMonitoring.getInstancesByCreator(creator).toString());
                }
                if(entities.size()!=0){
                    String provider = entityProviderSetEntry.getKey().getName();
                    entityProviders.putObject(provider);
                    entityProviders.set(provider, entities);
                }
            }
        }
        result.putObject("Entities");
        result.set("Entities", entityProviders);

        if(contextManagerWebMonitoring != null){
            for(Map.Entry<RelationProvider, Set<String>> relationProviderSetEntry: contextManagerWebMonitoring.getAbstractionCreatorsByRelationProvider().entrySet()){
                ObjectNode relations = json.newObject();
                for(String creator : relationProviderSetEntry.getValue()){
                    relations.put(creator, contextManagerWebMonitoring.getInstancesByCreator(creator).toString());
                }
                if(relations.size()!=0){
                    String provider = relationProviderSetEntry.getKey().getName();
                    relationProviders.putObject(provider);
                    relationProviders.set(provider, relations);
                }
            }
        }
        result.putObject("Relations");
        result.set("Relations", relationProviders);

        return ok(result);
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
