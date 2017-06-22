package util.socm;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.cream.administration.api.ImmutableRelation;
import org.wisdom.api.annotations.Service;

import java.io.IOException;

/*ToDo not used in that version of webdisplay*/
@Service(Module.class)
public class ImmutableRelationSerializer extends SimpleModule {

    public ImmutableRelationSerializer(){

        super("Core serializer");
        addSerializer(ImmutableRelation.class, new JsonSerializer<ImmutableRelation>() {
            @Override
            public void serialize(ImmutableRelation immutableRelation, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("name-ToDo", immutableRelation.getState());
                    jsonGenerator.writeArrayFieldStart("ids");
                        for(String source : immutableRelation.getSourcesId()){
                            jsonGenerator.writeString(source);
                        }
                    jsonGenerator.writeEndArray();
                jsonGenerator.writeEndObject();
            }
        });
    }
}
