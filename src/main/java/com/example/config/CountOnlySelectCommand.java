package com.example.config;

import org.seasar.doma.jdbc.command.ResultSetHandler;
import org.seasar.doma.jdbc.command.SelectCommand;
import org.seasar.doma.jdbc.query.SelectQuery;

public class CountOnlySelectCommand<RESULT> extends SelectCommand<RESULT> {

    public CountOnlySelectCommand(final SelectQuery query,
            final ResultSetHandler<RESULT> resultSetHandler) {
        super(query, resultSetHandler);
    }

    @Override
    public RESULT execute() {
        //クエリ発行しない。
        //戻り値も使わないのでnullを返す。
        return null;
    }
}
