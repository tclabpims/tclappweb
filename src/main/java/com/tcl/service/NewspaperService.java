package com.tcl.service;

import com.tcl.model.NewspaperModel;

import java.util.List;

/**
 * Created by wang on 2017-07-03.
 */
public interface NewspaperService {
    NewspaperModel selectById(Long id);

    List<NewspaperModel> selectByType(String type);

    List<NewspaperModel> queryByInfo(NewspaperModel newspaperModel);

    List<NewspaperModel> selectByPageInfo(String type, int page_no, int pageSize);

    List<NewspaperModel> queryByPageInfo(String title, String type, String status, int page_no, int pageSize);

    int addNewspaper(NewspaperModel newspaperModel);

    int deleteById(long id);

    int updateById(NewspaperModel hospitalModel);
}
