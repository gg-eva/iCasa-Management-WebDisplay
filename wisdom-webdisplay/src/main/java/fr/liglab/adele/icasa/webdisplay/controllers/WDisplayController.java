package fr.liglab.adele.icasa.webdisplay.controllers;

import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;


@Controller
public class WDisplayController extends DefaultController {


    @View("status")
    Template status;

    @Route(method = HttpMethod.GET, uri = "/status")
    public Result status() {
        return ok(render(status));
    }

}
