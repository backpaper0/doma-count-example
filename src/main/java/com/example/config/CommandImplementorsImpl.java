package com.example.config;

import java.lang.reflect.Method;

import org.seasar.doma.jdbc.CommandImplementors;
import org.seasar.doma.jdbc.command.ResultSetHandler;
import org.seasar.doma.jdbc.command.SelectCommand;
import org.seasar.doma.jdbc.query.SelectQuery;

public class CommandImplementorsImpl implements CommandImplementors {

    @Override
    public <RESULT> SelectCommand<RESULT> createSelectCommand(final Method method,
            final SelectQuery query, final ResultSetHandler<RESULT> resultSetHandler) {

        //渡されたSelectOptionsがCountOnlySelectOptionsの場合は
        //クエリ発行をしないSelectCommandサブクラスを返す。
        if (query.getOptions() instanceof CountOnlySelectOptions) {
            return new CountOnlySelectCommand<>(query, resultSetHandler);
        }

        //上記以外の場合はデフォルトの動作を行う。
        return CommandImplementors.super.createSelectCommand(method, query, resultSetHandler);
    }
}
