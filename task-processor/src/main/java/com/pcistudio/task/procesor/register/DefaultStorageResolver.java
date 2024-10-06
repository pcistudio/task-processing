package com.pcistudio.task.procesor.register;

import com.pcistudio.task.procesor.HandlerPropertiesWrapper;
import com.pcistudio.task.procesor.StorageResolver;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultStorageResolver implements StorageResolver {

    private final ProcessorRegisterLookup processorRegister;

    @Override
    public String resolveStorageName(String handlerName) {
        HandlerPropertiesWrapper properties = processorRegister.getProperties(handlerName);
        if (properties == null) {
            throw new IllegalArgumentException("Handler not found: " + handlerName);
        }
        return properties.getTableName();
    }
}
