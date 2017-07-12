package fr.liglab.adele.icasa.webdisplay.util.socm;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.cream.administration.api.ImmutableContextEntity;
import fr.liglab.adele.cream.administration.api.ImmutableFunctionalExtension;
import org.wisdom.api.annotations.Service;

import java.io.IOException;

@Service(Module.class)
public class ImmutableContextEntitySerializer extends SimpleModule{

    public ImmutableContextEntitySerializer(){
        super("Context entity serializer");
        addSerializer(ImmutableContextEntity.class, new JsonSerializer<ImmutableContextEntity>() {
            @Override
            public void serialize(ImmutableContextEntity immutableContextEntity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeStartObject();

            /*Context entity instance*/
            jsonGenerator.writeStringField("id", immutableContextEntity.getId());
            jsonGenerator.writeStringField("status", immutableContextEntity.getState());

            /*Functional core*/
            jsonGenerator.writeObjectField("functional_core", immutableContextEntity.getCore());

            /*Functional extensions*/
            jsonGenerator.writeArrayFieldStart("functional_extensions");
            for(ImmutableFunctionalExtension extension : immutableContextEntity.getExtensions()){
                jsonGenerator.writeObject(extension);
            }
            jsonGenerator.writeEndArray();

            jsonGenerator.writeEndObject();
            }
        });
    }
}
