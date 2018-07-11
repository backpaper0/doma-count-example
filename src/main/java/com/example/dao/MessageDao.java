package com.example.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Script;
import org.seasar.doma.Select;
import org.seasar.doma.jdbc.SelectOptions;

import com.example.config.CountOnlySelectOptions;
import com.example.config.DomaConfig;
import com.example.entity.Message;

@Dao(config = DomaConfig.class)
public interface MessageDao {

    @Select
    List<Message> select(SelectOptions selectOptions);

    default long count() {
        final SelectOptions selectOptions = CountOnlySelectOptions.get();
        select(selectOptions);
        return selectOptions.getCount();
    }

    @Script
    void init();
}
