package fr.liglab.adele.icasa.webdisplay.controllers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fr.liglab.adele.icasa.apps.demo.global.DemoApp;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Parameter;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.content.Json;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;

import java.util.List;


@Controller
@SuppressWarnings("unused")
public class DemoAppsController extends DefaultController{
    @Requires
    @SuppressWarnings("unused")
    private Json json;

    @Requires(optional = true, specification = DemoApp.class)
    @SuppressWarnings("all")
    private List<DemoApp> demoApps;

    @Route(method = HttpMethod.GET, uri = "/apps")
    @SuppressWarnings("unused")
    public Result getApps(){
        ArrayNode result = json.newArray();

        if(demoApps != null){
            for(DemoApp app : demoApps){
                result.add(json.toJson(app));
            }
        }

        return ok(result);
    }

    @Route(method = HttpMethod.GET, uri = "/apps/toggle/{appName}")
    @SuppressWarnings("unused")
    public Result toggleApp(@Parameter("appName") String appName){
        ArrayNode result = json.newArray();

        if(appName != null && demoApps != null){
            for(DemoApp app : demoApps){
                if(appName.equals(app.getAppName())){
                    app.toggleRegistration();
                    result.add(json.toJson(app));
                }
            }
        }

        return ok(result);
    }

}
