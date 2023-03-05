package com.portalprojects.infrastructure.projection;

import com.portalprojects.entity.base.IsIdentified;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author phongtt35
 */
@Projection(types = {})
public interface SimpleEntityProj extends IsIdentified {
    String getName();
}
