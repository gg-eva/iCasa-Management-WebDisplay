package util.socm;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.cream.administration.api.ImmutableContextState;
import fr.liglab.adele.cream.administration.api.ImmutableCore;
import fr.liglab.adele.cream.administration.api.ImmutableRelation;
import org.wisdom.api.annotations.Service;

import java.io.IOException;

@Service(Module.class)
public class ImmutableCoreSerializer extends SimpleModule {

    public ImmutableCoreSerializer(){
        super("Core serializer");
        addSerializer(ImmutableCore.class, new JsonSerializer<ImmutableCore>() {
            @Override
            public void serialize(ImmutableCore immutableCore, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeStartObject();

                /*Implementation*/
                jsonGenerator.writeStringField("name",immutableCore.getImplementation());

                /*Context specifications - implemented*/
                jsonGenerator.writeArrayFieldStart("services");
                for(String spec : immutableCore.getImplementedSpecifications()){
                    jsonGenerator.writeString(spec);
                }
                jsonGenerator.writeEndArray();

                /*Context states - without synchro (with synchro, use automatic json serialization)*/
                jsonGenerator.writeObjectFieldStart("states");
                for(ImmutableContextState state : immutableCore.getContextStates()){
                    jsonGenerator.writeStringField(state.getId(), state.getValue());
                }
                jsonGenerator.writeEndObject();

                /*Relations*/
                jsonGenerator.writeArrayFieldStart("connections");
                for(ImmutableRelation relation : immutableCore.getRelations()){
                    for(String id : relation.getSourcesId()){
                        jsonGenerator.writeString(id);
                    }
                }
                jsonGenerator.writeEndArray();

                jsonGenerator.writeEndObject();
            }
        });
    }
}