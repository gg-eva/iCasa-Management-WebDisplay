package fr.liglab.adele.icasa.webdisplay.util.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.icasa.apps.demo.global.DemoApp;
import org.wisdom.api.annotations.Service;

import java.io.IOException;

@Service(Module.class)
public class DemoAppSerializer extends SimpleModule{
    public DemoAppSerializer(){
        super("Demo app serializer");
        addSerializer(DemoApp.class, new JsonSerializer<DemoApp>() {

            public void serialize(DemoApp demoApp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("app_name", demoApp.getAppName());
                jsonGenerator.writeBooleanField("registered", demoApp.getRegistrationState());
                jsonGenerator.writeStringField("state", demoApp.getState());
                jsonGenerator.writeEndObject();
            }
        });
    }
}
