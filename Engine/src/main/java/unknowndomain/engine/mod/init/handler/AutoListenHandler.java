package unknowndomain.engine.mod.init.handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import unknowndomain.engine.mod.ModContainer;
import unknowndomain.engine.mod.annotation.AutoListen;
import unknowndomain.engine.mod.init.ModInitializationHandler;
import unknowndomain.engine.mod.init.ModInitializer;
import unknowndomain.engine.util.JsonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

public class AutoListenHandler implements ModInitializationHandler {
    @Override
    public void handle(ModInitializer initializer, ModContainer mod) {
        try {
            Optional<InputStream> stream = mod.getAssets().openStream("META-INF", "data", "AutoListen.json");
            if (stream.isEmpty()) {
                mod.getLogger().warn("Not found \"AutoListen.json\" file, skip AutoListen stage.");
                return;
            }

            try (Reader reader = new InputStreamReader(stream.get())) {
                JsonArray listeners = JsonUtils.DEFAULT_JSON_PARSER.parse(reader).getAsJsonArray();
                for (JsonElement listener : listeners) {
                    try {
                        Class<?> listenerClass = Class.forName(listener.getAsString(), false, mod.getClassLoader());
                        AutoListen anno = listenerClass.getAnnotation(AutoListen.class);
                        Object instance = listenerClass.getDeclaredConstructor().newInstance();
                        switch (anno.value()) {
                            case ENGINE:
                                initializer.getEngine().getEventBus().register(instance);
                                break;
                            case MOD:
                                mod.getEventBus().register(instance);
                                break;
                        }
                    } catch (Exception e) {
                        mod.getLogger().warn(String.format("Cannot register listener %s.", listener.getAsString()), e);
                    }
                }
            }
        } catch (IOException e) {
            mod.getLogger().warn("Cannot open \"AutoListen.json\" file, skip AutoListen stage.", e);
        } catch (Exception e) {
            mod.getLogger().warn("Caught exception when auto register listener, skip AutoListen stage.", e);
        }
    }
}
