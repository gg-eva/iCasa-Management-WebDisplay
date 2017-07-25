package fr.liglab.adele.icasa.webdisplay.controllers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fr.liglab.adele.icasa.apps.demo.global.context.controllers.LocationRelationsController;
import fr.liglab.adele.icasa.apps.demo.global.context.controllers.ZoneController;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Parameter;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.content.Json;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;

@Controller
@SuppressWarnings("unused")
public class DemoGlobalController extends DefaultController {
    @Requires
    @SuppressWarnings("unused")
    private Json json;

    /*ZONE*/
    @Requires
    private ZoneController zoneController;

    @Route(method = HttpMethod.GET, uri = "/env/zones")
    @SuppressWarnings("unused")
    public Result getZones(){
        ArrayNode result = json.newArray();
        for(String zone : zoneController.getZones()){
            result.add(zone);
        }
        return ok(result);
    }

    @Route(method = HttpMethod.GET, uri = "/env/zones/add/{id}")
    @SuppressWarnings("unused")
    public Result createZone(@Parameter("id") String id){
        zoneController.addZone(id);
        return ok(getZones());
    }

    @Route(method = HttpMethod.GET, uri = "/env/zones/remove/{id}")
    @SuppressWarnings("unused")
    public Result deleteZone(@Parameter("id") String id){
        zoneController.removeZone(id);
        return ok(getZones());
    }

    /*LOCATION*/
    @Requires
    private LocationRelationsController locationRelationsController;

    @Route(method = HttpMethod.GET, uri = "/env/locations")
    @SuppressWarnings("unused")
    public Result getLocations(){
        ArrayNode result = json.newArray();
        for(String relation : locationRelationsController.getLocatedObjectRelations()){
            result.add(relation);
        }
        return ok(result);
    }

    @Route(method = HttpMethod.GET, uri = "/env/locations/add/{zoneId}/{objectId}")
    @SuppressWarnings("unused")
    public Result createLocation(@Parameter("zoneId") String zoneId, @Parameter("objectId") String objectId){
        locationRelationsController.attachLocatedObjectToZone(zoneId, objectId);
        return ok(getLocations());
    }

    @Route(method = HttpMethod.GET, uri = "/env/locations/remove/{zoneId}/{objectId}")
    @SuppressWarnings("unused")
    public Result deleteLocation(@Parameter("zoneId") String zoneId, @Parameter("objectId") String objectId){
        locationRelationsController.detachLocatedObjectFromZone(zoneId, objectId);
        return ok(getLocations());
    }
}
