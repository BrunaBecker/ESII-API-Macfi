package com.macfi.model.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    //TODO: figure out how to fucking get the right path (linux included)
    public String location = "src/main/files";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

}
