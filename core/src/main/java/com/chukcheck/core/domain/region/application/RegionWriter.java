package com.chukcheck.core.domain.region.application;

import com.chukcheck.core.domain.region.entity.Region;

public interface RegionWriter {

    Region save(Region entity);
}
