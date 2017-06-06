package util.am;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.icasa.context.manager.api.generic.ContextAPIConfig;
import fr.liglab.adele.icasa.context.manager.api.specific.ContextAPIEnum;
import org.wisdom.api.annotations.Service;

import java.io.IOException;

@Service(Module.class)
public class ContextAPIConfigSerializer extends SimpleModule{

    public ContextAPIConfigSerializer(){
        super("Context API Config serializer");
        addSerializer(ContextAPIConfig.class, new JsonSerializer<ContextAPIConfig>() {
            @Override
            public void serialize(ContextAPIConfig contextAPIConfig, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                    throws IOException, JsonProcessingException {
//                jsonGenerator.writeStartObject();
//                jsonGenerator.writeArrayFieldStart("ctxt-api-config");
                jsonGenerator.writeStartArray();
                for(ContextAPIEnum api : contextAPIConfig.getConfig()){
                    jsonGenerator.writeString(api.getInterfaceName());
                }
                jsonGenerator.writeEndArray();
//                jsonGenerator.writeEndObject();
            }
        });
    }
}
