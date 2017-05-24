package com.zk.sbt.resource.apiv1;

import com.zk.sbt.common.ObjectMapperContextResolver;
import com.zk.sbt.resource.apiv1.developer.DeveloperResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.Provider;

/**
 * Created by zk_chs on 17/1/13.
 */
@ApplicationPath(ProjectResourceConfigV1.APPLICATION_PATH)
@Provider
public class ProjectResourceConfigV1 extends ResourceConfig {

    public static final String APPLICATION_PATH = "apiv1";

    public ProjectResourceConfigV1 (){
        property(ServerProperties.WADL_FEATURE_DISABLE, true);

        // register custom
        register(ObjectMapperContextResolver.class);

        // register resource
        register(DeveloperResource.class);
    }

}
