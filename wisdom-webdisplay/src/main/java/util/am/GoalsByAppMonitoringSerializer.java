package util.am;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.liglab.adele.icasa.context.manager.api.specific.ContextAPIEnum;
import fr.liglab.adele.icasa.context.manager.api.web.administration.GoalsByAppMonitoring;
import org.wisdom.api.annotations.Service;

import java.io.IOException;
import java.util.Map;

@Service(Module.class)
public class GoalsByAppMonitoringSerializer extends SimpleModule{

    public GoalsByAppMonitoringSerializer(){
        super("Goals By App Monitoring serializer");
        addSerializer(GoalsByAppMonitoring.class, new JsonSerializer<GoalsByAppMonitoring>() {
            @Override
            public void serialize(GoalsByAppMonitoring goalsByAppMonitoring, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                    throws IOException, JsonProcessingException {
                jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("app_name", goalsByAppMonitoring.getApp());
                    jsonGenerator.writeObjectFieldStart("context_api_config");
                        for(Map.Entry<ContextAPIEnum, Boolean> state : goalsByAppMonitoring.getGoals().entrySet()){
                            jsonGenerator.writeStringField(state.getKey().getInterfaceName(),
                                    state.getValue()?"provided":"not provided");
                        }
                    jsonGenerator.writeEndObject();
                jsonGenerator.writeEndObject();
            }
        });
    }
}
