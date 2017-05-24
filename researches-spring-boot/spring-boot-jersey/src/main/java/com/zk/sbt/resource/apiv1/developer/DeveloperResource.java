package com.zk.sbt.resource.apiv1.developer;

import com.zk.sbt.entity.Developer;
import com.zk.sbt.provider.data.developer.DeveloperProvider;
import com.zk.sbt.resource.apiv1.developer.model.DeveloperRep;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by zk_chs on 17/2/12.
 */
@Path("users")
@Component
@Api("开发者资源接口集合")
public class DeveloperResource {

    @Autowired
    private DeveloperProvider developerProvider;

    @ApiOperation(value = "获取开发者信息接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "返回model", response = DeveloperRep.class, responseContainer = "List")
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkEnableEdit (@QueryParam("imageId") Long imageId){
        Developer developer = developerProvider.findById(1L);
        return Response.ok(developer).build();
    }

}
