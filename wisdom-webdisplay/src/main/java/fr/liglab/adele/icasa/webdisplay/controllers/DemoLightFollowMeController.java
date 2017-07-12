package fr.liglab.adele.icasa.webdisplay.controllers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fr.liglab.adele.icasa.apps.demo.light.follow.me.context.controllers.LightController;
import fr.liglab.adele.icasa.apps.demo.light.follow.me.context.controllers.PushButtonController;
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
public class DemoLightFollowMeController extends DefaultController {
    @Requires
    @SuppressWarnings("unused")
    private Json json;

    /*LIGHTS*/
    @Requires
    private LightController lightController;

    @Route(method = HttpMethod.GET, uri = "/env/lights")
    @SuppressWarnings("unused")
    public Result getLights(){
        ArrayNode result = json.newArray();
        for(String light : lightController.getLights()){
            result.add(light);
        }
        return ok(result);
    }

    @Route(method = HttpMethod.GET, uri = "/env/lights/add/{id}")
    @SuppressWarnings("unused")
    public Result createLight(@Parameter("id") String id){
        lightController.addLight(id);
        return ok(getLights());
    }

    @Route(method = HttpMethod.GET, uri = "/env/lights/remove/{id}")
    @SuppressWarnings("unused")
    public Result deleteLight(@Parameter("id") String id){
        lightController.removeLight(id);
        return ok(getLights());
    }

    /*PUSH BUTTONS*/
    @Requires
    private PushButtonController pushButtonController;

    @Route(method = HttpMethod.GET, uri = "/env/buttons")
    @SuppressWarnings("unused")
    public Result getButtons(){
        ArrayNode result = json.newArray();
        for(String button : pushButtonController.getButtons()){
            result.add(button);
        }
        return ok(result);
    }

    @Route(method = HttpMethod.GET, uri = "/env/buttons/add/{id}")
    @SuppressWarnings("unused")
    public Result createButton(@Parameter("id") String id){
        pushButtonController.addButton(id);
        return ok(getButtons());
    }

    @Route(method = HttpMethod.GET, uri = "/env/buttons/remove/{id}")
    @SuppressWarnings("unused")
    public Result deleteButton(@Parameter("id") String id){
        pushButtonController.removeButton(id);
        return ok(getButtons());
    }

    @Route(method = HttpMethod.GET, uri = "/env/buttons/pushAll")
    @SuppressWarnings("unused")
    public Result pushAllButtons(){
        pushButtonController.pushAllButtons();
        return ok(getButtons());
    }

    @Route(method = HttpMethod.GET, uri = "/env/buttons/push/{id}")
    @SuppressWarnings("unused")
    public Result pushButton(@Parameter("id") String id){
        pushButtonController.pushButton(id);
        return ok(getButtons());
    }

    @Route(method = HttpMethod.GET, uri = "/env/buttons/pushInZone/{id}")
    @SuppressWarnings("unused")
    public Result pushButtonInZone(@Parameter("id") String id){
        pushButtonController.pushButtonInZone(id);
        return ok(getButtons());
    }
}
