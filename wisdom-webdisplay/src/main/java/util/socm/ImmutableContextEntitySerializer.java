package util.socm;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.cream.administration.api.ImmutableContextEntity;
import fr.liglab.adele.cream.administration.api.ImmutableContextState;
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
                jsonGenerator.writeStringField("id", immutableContextEntity.getId());
                jsonGenerator.writeStringField("state", immutableContextEntity.getState());

                jsonGenerator.writeArrayFieldStart("context-states");
                for(ImmutableContextState state : immutableContextEntity.getContextStates()){
                    jsonGenerator.writeObject(state);
                }
                jsonGenerator.writeEndArray();

                jsonGenerator.writeArrayFieldStart("functional-extensions");
                for(ImmutableFunctionalExtension extension : immutableContextEntity.getExtensions()){
                    jsonGenerator.writeObject(extension);
                }
                jsonGenerator.writeEndArray();

                jsonGenerator.writeArrayFieldStart("implemented-specifications");
                for(String spec : immutableContextEntity.getImplementedSpecifications()){
                    jsonGenerator.writeString(spec);
                }
                jsonGenerator.writeEndArray();

                jsonGenerator.writeEndObject();
            }
        });
    }
}
