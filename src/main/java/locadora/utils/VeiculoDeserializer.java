package locadora.utils;

import com.google.gson.*;
import locadora.model.Caminhao;
import locadora.model.Carro;
import locadora.model.Moto;
import locadora.model.Veiculo;

import java.lang.reflect.Type;

public class VeiculoDeserializer implements JsonDeserializer<Veiculo> {
    @Override
    public Veiculo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject.has("tipo")) {
            String tipo = jsonObject.get("tipo").getAsString();
            switch (tipo) {
                case "Caminhão":
                    return context.deserialize(jsonElement, Caminhao.class);
                case "Carro":
                    return context.deserialize(jsonElement, Carro.class);
                case "Moto":
                    return context.deserialize(jsonElement, Moto.class);
            }
        }
        return null;
    }

}
