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

        if (query.getOptions() instanceof CountOnlySelectOptions) {
            return new CountOnlySelectCommand<>(query, resultSetHandler);
        }

        return CommandImplementors.super.createSelectCommand(method, query, resultSetHandler);
    }
}
