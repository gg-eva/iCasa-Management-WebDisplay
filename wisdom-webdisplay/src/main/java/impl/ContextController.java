package impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.liglab.adele.cream.administration.api.AdministrationService;
import fr.liglab.adele.cream.administration.api.ImmutableContextEntity;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Parameter;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.content.Json;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;

@Controller
public class ContextController extends DefaultController {

    @Requires
    Json json;

    @Requires(optional = true)
    private AdministrationService contextAdministrationService;

    @Route(method = HttpMethod.GET, uri = "/manager/context")
    public Result getContext(){
        ObjectNode result = json.newObject();

        if(contextAdministrationService != null){
            for(ImmutableContextEntity entity : contextAdministrationService.getContextEntities()){
                result.withArray("context-entities").add(json.toJson(entity));
            }
        }

        return ok(result);
    }

    @Route(method = HttpMethod.GET, uri = "/manager/context/{id}")
    public Result getContextEntity(@Parameter("id") String id){

        ImmutableContextEntity immutableContextEntity = null;

        if(contextAdministrationService != null){
            immutableContextEntity = contextAdministrationService.getContextEntity(id);
        }

        return ok(immutableContextEntity).json();
    }

}
