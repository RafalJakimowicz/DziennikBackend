package org.example.dziennikbackend.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dziennikbackend.configs.ConfigObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class ConfigService {
    private ConfigObject configObject;

    public ConfigService() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = new ClassPathResource("config.json");

        try (InputStream inputStream = resource.getInputStream()) {
            this.configObject = objectMapper.readValue(inputStream, ConfigObject.class);
        }
    }

    public ConfigObject getConfigObject() {
        return this.configObject;
    }
}
