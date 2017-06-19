package util.socm;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.cream.administration.api.ImmutableSynchroPeriod;
import org.wisdom.api.annotations.Service;

import java.io.IOException;

/*ToDo not used in that version of webdisplay*/
@Service(Module.class)
public class ImmutableSynchroPeriodSerializer extends SimpleModule{

    public ImmutableSynchroPeriodSerializer(){
        super("Synchronization period serializer");
        addSerializer(ImmutableSynchroPeriod.class, new JsonSerializer<ImmutableSynchroPeriod>() {
            @Override
            public void serialize(ImmutableSynchroPeriod immutableSynchroPeriod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeStartObject();
//                jsonGenerator.writeStringField("period", immutableSynchroPeriod.getPeriod());
//                jsonGenerator.writeStringField("unit", immutableSynchroPeriod.getUnit());
                jsonGenerator.writeStringField(immutableSynchroPeriod.getPeriod(), immutableSynchroPeriod.getUnit());
            jsonGenerator.writeEndObject();
            }
        });
        addDeserializer(ImmutableSynchroPeriod.class, new JsonDeserializer<ImmutableSynchroPeriod>() {
            @Override
            public ImmutableSynchroPeriod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                ObjectCodec oc = jsonParser.getCodec();
                JsonNode node = oc.readTree(jsonParser);
                String period = node.get("period").asText();
                String unit = node.get("unit").asText();
                return new ImmutableSynchroPeriod(period, unit);
            }
        });
    }
}
