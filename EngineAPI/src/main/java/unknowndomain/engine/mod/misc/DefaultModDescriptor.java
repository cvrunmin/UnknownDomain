package unknowndomain.engine.mod.misc;

import unknowndomain.engine.mod.ModDependencyEntry;
import unknowndomain.engine.mod.ModDescriptor;
import unknowndomain.engine.util.versioning.Version;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DefaultModDescriptor implements ModDescriptor {

    public static final Version DEFAULT_VERSION = new Version("1.0");

    private final Path source;

    private final String modId;
    private final Version version;
    private final String mainClass;
    private final String name;
    private final String description;
    private final String url;
    private final List<String> authors;
    private final String logoFile;
    private final List<ModDependencyEntry> dependencies;
    private final Map<String, String> properties;

    protected DefaultModDescriptor(Path source, String modId, Version version, String mainClass, String name, String description, String url, List<String> authors, String logoFile, List<ModDependencyEntry> dependencies, Map<String, String> properties) {
        this.source = source;
        this.modId = modId;
        this.version = version;
        this.mainClass = mainClass;
        this.name = name;
        this.description = description;
        this.url = url;
        this.authors = authors;
        this.logoFile = logoFile;
        this.dependencies = dependencies;
        this.properties = properties;
    }

    @Nonnull
    @Override
    public Path getSource() {
        return source;
    }

    @Nonnull
    @Override
    public String getModId() {
        return modId;
    }

    @Nonnull
    @Override
    public Version getVersion() {
        return version;
    }

    public String getMainClass() {
        return mainClass;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getLogoFile() {
        return logoFile;
    }

    public List<ModDependencyEntry> getDependencies() {
        return dependencies;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id = "";
        private Version version = DEFAULT_VERSION;
        private Path source;
        private String mainClass = "";
        private String name = "";
        private String description = "";
        private String url = "";
        private List<String> authors = Collections.emptyList();
        private String logo = "";
        private List<ModDependencyEntry> dependencies = Collections.emptyList();
        private Map<String, String> properties = Collections.emptyMap();

        public Builder modId(String id) {
            this.id = id;
            return this;
        }

        public Builder version(Version version) {
            this.version = version;
            return this;
        }

        public Builder version(String version) {
            this.version = new Version(version);
            return this;
        }

        public Builder source(Path source) {
            this.source = source;
            return this;
        }

        public Builder mainClass(String mainClass) {
            this.mainClass = mainClass;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder authors(List<String> authors) {
            this.authors = authors;
            return this;
        }

        public Builder logo(String logo) {
            this.logo = logo;
            return this;
        }

        public Builder properties(Map<String, String> properties) {
            this.properties = properties;
            return this;
        }

        public Builder dependencies(List<ModDependencyEntry> dependencies) {
            this.dependencies = dependencies;
            return this;
        }

        public DefaultModDescriptor build() {
            return new DefaultModDescriptor(source, id, version, mainClass, name, description, url, authors, logo, dependencies, properties);
        }
    }
}
