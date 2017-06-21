package impl;

import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;


@Controller
public class test extends DefaultController {


    @View("test")
    Template test;

    @Route(method = HttpMethod.GET, uri = "/test")
    public Result test() {
        return ok(render(test));
    }

    @View("test2")
    Template test2;

    @Route(method = HttpMethod.GET, uri = "/test2")
    public Result test2() {
        return ok(render(test2));
    }

}
