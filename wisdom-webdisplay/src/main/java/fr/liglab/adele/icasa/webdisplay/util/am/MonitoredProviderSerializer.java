package fr.liglab.adele.icasa.webdisplay.util.am;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.wisdom.api.annotations.Service;

import java.io.IOException;

/*ToDo*/
@Service(Module.class)
public class MonitoredProviderSerializer extends SimpleModule{
    public MonitoredProviderSerializer(){
        super("Monitored provider serializer");
        addSerializer(MonitoredProvider.class, new JsonSerializer<MonitoredProvider>() {

            public void serialize(MonitoredProvider monitoredProvider, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

            }
        });
    }
}
