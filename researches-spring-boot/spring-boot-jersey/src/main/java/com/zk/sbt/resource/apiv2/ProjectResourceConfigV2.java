package com.zk.sbt.resource.apiv2;

import com.zk.sbt.common.ObjectMapperContextResolver;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.Provider;

/**
 * Created by zk_chs on 17/1/13.
 */
@ApplicationPath(ProjectResourceConfigV2.APPLICATION_PATH)
@Provider
public class ProjectResourceConfigV2 extends ResourceConfig {

    public static final String APPLICATION_PATH = "apiv2";

    public ProjectResourceConfigV2 (){
        property(ServerProperties.WADL_FEATURE_DISABLE, true);

        // register custom
        register(ObjectMapperContextResolver.class);

        // register resource

    }

}
