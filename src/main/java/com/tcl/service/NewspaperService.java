package com.tcl.service;

import com.tcl.model.NewspaperModel;

import java.util.List;

/**
 * Created by wang on 2017-07-03.
 */
public interface NewspaperService {
    NewspaperModel selectById(Long id);

    List<NewspaperModel> selectByType(String type);
}
