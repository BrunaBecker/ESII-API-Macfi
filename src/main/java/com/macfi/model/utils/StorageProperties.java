package com.macfi.model.utils;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("storage")
public class StorageProperties {

    //TODO: figure out how to get the right path (linux included)
    public String location = "src/main/files";

    public void setLocation(String location){
        this.location = location;
    }

}
