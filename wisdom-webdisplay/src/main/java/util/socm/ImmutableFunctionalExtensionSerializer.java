package util.socm;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.cream.administration.api.ImmutableContextState;
import fr.liglab.adele.cream.administration.api.ImmutableFunctionalExtension;
import fr.liglab.adele.cream.administration.api.ImmutableRelation;
import org.wisdom.api.annotations.Service;

import java.io.IOException;
import java.util.List;

@Service(Module.class)
public class ImmutableFunctionalExtensionSerializer extends SimpleModule{

    public ImmutableFunctionalExtensionSerializer(){

        super("Functional extension serializer");
        addSerializer(ImmutableFunctionalExtension.class, new JsonSerializer<ImmutableFunctionalExtension>() {
            @Override
            public void serialize(ImmutableFunctionalExtension immutableFunctionalExtension, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeStartObject();

            /*Functional extension*/
                jsonGenerator.writeStringField("id", immutableFunctionalExtension.getId());
                jsonGenerator.writeStringField("mandatory", immutableFunctionalExtension.isMandatory());
                jsonGenerator.writeStringField("instantiated", immutableFunctionalExtension.isInstantiate());
                jsonGenerator.writeStringField("status", immutableFunctionalExtension.getState());

            /*Context specifications*/
                List<String> availableServices = immutableFunctionalExtension.getManagedSpecifications();
                jsonGenerator.writeObjectFieldStart("services");
                for(String spec : immutableFunctionalExtension.getImplementedSpecifications()){
                    jsonGenerator.writeStringField(spec, availableServices.contains(spec)? "provided":"not provided");
                }
                jsonGenerator.writeEndObject();

            /*Context states - without synchro (with synchro, use automatic json serialization)*/
                jsonGenerator.writeObjectFieldStart("states");
                for(ImmutableContextState state : immutableFunctionalExtension.getContextStates()){
                    jsonGenerator.writeStringField(state.getId(), state.getValue());
                }
                jsonGenerator.writeEndObject();

            /*Relations*/
                jsonGenerator.writeArrayFieldStart("connections");
                for(ImmutableRelation relation : immutableFunctionalExtension.getRelations()){
                    for(String id : relation.getSourcesId()){
                        jsonGenerator.writeString(id);
                    }
                }
                jsonGenerator.writeEndArray();

            /*Alternative configurations*/
                String activatedConfiguration = immutableFunctionalExtension.getSelectedImplementation();
                jsonGenerator.writeObjectFieldStart("alternative_configurations");
                for(String config : immutableFunctionalExtension.getAlternativeConfigurations()){
                    jsonGenerator.writeStringField(config, config.equals(activatedConfiguration)?"activated":"not activated");
                }
                jsonGenerator.writeEndObject();

                jsonGenerator.writeEndObject();
            }
        });
    }
}