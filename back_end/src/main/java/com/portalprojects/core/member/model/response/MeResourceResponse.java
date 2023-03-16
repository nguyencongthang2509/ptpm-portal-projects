package com.portalprojects.core.member.model.response;

import com.portalprojects.entity.Project;
import com.portalprojects.entity.Resource;
import com.portalprojects.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author thangncph26123
 */
@Projection(types = {Resource.class})
public interface MeResourceResponse extends IsIdentified {

 @Value("#{target.name}")
 String getName();

 @Value("#{target.content}")
 String getContent();

}