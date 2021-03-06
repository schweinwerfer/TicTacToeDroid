package de.ora.neural.core.net;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

import de.ora.neural.core.activation.ActivationFunction;

public class ActivationFunctionSerializer extends JsonSerializer<ActivationFunction> {
    @Override
    public void serialize(ActivationFunction o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(o.getClass().getCanonicalName());
    }
}
