package util.socm;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.cream.administration.api.ImmutableContextState;
import fr.liglab.adele.cream.administration.api.ImmutableSynchroPeriod;
import org.wisdom.api.annotations.Service;

import java.io.IOException;

/*ToDo not used in that version of webdisplay*/
@Service(Module.class)
public class ImmutableContextStateSerializer extends SimpleModule{

    public ImmutableContextStateSerializer(){

        super("Context state serializer");
        addSerializer(ImmutableContextState.class, new JsonSerializer<ImmutableContextState>() {
            @Override
            public void serialize(ImmutableContextState immutableContextState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("name", immutableContextState.getId());
                jsonGenerator.writeStringField("value", immutableContextState.getValue());
                jsonGenerator.writeObjectField("synchro", immutableContextState.getSynchroPeriod());
                jsonGenerator.writeEndObject();
            }
        });
    }
}