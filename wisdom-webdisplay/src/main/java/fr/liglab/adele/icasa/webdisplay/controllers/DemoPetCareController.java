package fr.liglab.adele.icasa.webdisplay.controllers;

import fr.liglab.adele.icasa.apps.demo.pet.care.context.controllers.PetController;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.content.Json;

@Controller
@SuppressWarnings("unused")
public class DemoPetCareController extends DefaultController {
    @Requires
    @SuppressWarnings("unused")
    private Json json;

    /*PET CARE*/
    @Requires
    private PetController petController;


}
