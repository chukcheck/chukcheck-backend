package com.chukcheck.core.domain.sns.application;

import com.chukcheck.core.domain.sns.entity.Sns;

public interface SnsWriter {

    Sns save(Sns entity);

}
