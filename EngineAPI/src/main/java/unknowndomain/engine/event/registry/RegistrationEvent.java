package unknowndomain.engine.event.registry;

import unknowndomain.engine.event.Event;
import unknowndomain.engine.event.GenericEvent;
import unknowndomain.engine.registry.Registry;
import unknowndomain.engine.registry.RegistryEntry;
import unknowndomain.engine.registry.RegistryManager;

import javax.annotation.Nonnull;
import java.lang.reflect.Type;

public abstract class RegistrationEvent implements Event {
    private final RegistryManager manager;

    private RegistrationEvent(RegistryManager registryManager) {
        manager = registryManager;
    }

    public RegistryManager getRegistryManager() {
        return manager;
    }

    public static class Start extends RegistrationEvent {

        public Start(RegistryManager registryManager) {
            super(registryManager);
        }
    }

    public static class Finish extends RegistrationEvent {

        public Finish(RegistryManager registryManager) {
            super(registryManager);
        }
    }

    public static class Register<T extends RegistryEntry<T>> implements GenericEvent<T> {

        private final Registry<T> registry;

        public Register(Registry<T> registry) {
            this.registry = registry;
        }

        @Override
        public Type getGenericType() {
            return registry.getEntryType();
        }

        public T register(@Nonnull T obj) {
            return registry.register(obj);
        }

        public void registerAll(@Nonnull T... objs) {
            registry.registerAll(objs);
        }
    }
}
