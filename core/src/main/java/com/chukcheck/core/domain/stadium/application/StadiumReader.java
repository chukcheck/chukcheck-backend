package com.chukcheck.core.domain.stadium.application;

import com.chukcheck.core.domain.stadium.entity.Stadium;

import java.util.List;

public interface StadiumReader {

    Stadium findById(Long id);

    List<Stadium> findAll();
}
