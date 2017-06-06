package util.socm;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.cream.administration.api.ImmutableContextState;
import fr.liglab.adele.cream.administration.api.ImmutableFunctionalExtension;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.annotations.Service;
import org.wisdom.api.content.Json;

import java.io.IOException;

@Service(Module.class)
public class ImmutableFunctionalExtensionSerializer extends SimpleModule{

    public ImmutableFunctionalExtensionSerializer(){
        super("Functional extension serializer");
        addSerializer(ImmutableFunctionalExtension.class, new JsonSerializer<ImmutableFunctionalExtension>() {
            @Override
            public void serialize(ImmutableFunctionalExtension immutableFunctionalExtension, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("id", immutableFunctionalExtension.getId());
                jsonGenerator.writeStringField("state", immutableFunctionalExtension.getState());
                jsonGenerator.writeStringField("is-instantiate", immutableFunctionalExtension.isInstantiate());
                jsonGenerator.writeStringField("is-mandatory", immutableFunctionalExtension.isMandatory());

                jsonGenerator.writeArrayFieldStart("managed-specifications");
                for(String spec : immutableFunctionalExtension.getManagedSpecifications()){
                    jsonGenerator.writeString(spec);
                }
                jsonGenerator.writeEndArray();

                jsonGenerator.writeArrayFieldStart("implemented-specifications");
                for(String spec : immutableFunctionalExtension.getImplementedSpecifications()){
                    jsonGenerator.writeString(spec);
                }
                jsonGenerator.writeEndArray();

                jsonGenerator.writeArrayFieldStart("alternative-configurations");
                for(String config : immutableFunctionalExtension.getAlternativeConfigurations()){
                    jsonGenerator.writeString(config);
                }
                jsonGenerator.writeEndArray();

                jsonGenerator.writeArrayFieldStart("context-states");
                for(ImmutableContextState state : immutableFunctionalExtension.getContextStates()){
                    jsonGenerator.writeObject(state);
                }
                jsonGenerator.writeEndArray();

                jsonGenerator.writeEndObject();
            }
        });
    }
}
