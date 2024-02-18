package com.baeldung.theValueAnnotation

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.env.PropertySource
import org.springframework.core.io.support.EncodedResource
import org.springframework.core.io.support.PropertySourceFactory
import org.springframework.lang.Nullable

class YamlPropertySourceFactory : PropertySourceFactory {
    override fun createPropertySource(@Nullable name: String?, encodedResource: EncodedResource): PropertySource<*> =
        PropertiesPropertySource(
            encodedResource.resource.filename,
            (YamlPropertiesFactoryBean().also { it.setResources(encodedResource.resource) }.getObject())
        )
}