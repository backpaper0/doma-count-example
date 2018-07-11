package com.example.config;

import javax.sql.DataSource;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.CommandImplementors;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.Naming;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

@SingletonConfig
public final class DomaConfig implements Config {

    private static final DomaConfig singleton = new DomaConfig();

    private final LocalTransactionDataSource dataSource;
    private final Dialect dialect;
    private final CommandImplementors commandImplementors;

    private DomaConfig() {
        this.dataSource = new LocalTransactionDataSource(
                "jdbc:h2:mem:example;DB_CLOSE_DELAY=-1", "sa", "secret");
        this.dialect = new H2Dialect();
        this.commandImplementors = new CommandImplementorsImpl();
    }

    @Override
    public CommandImplementors getCommandImplementors() {
        return commandImplementors;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public Naming getNaming() {
        return Naming.SNAKE_LOWER_CASE;
    }

    @Override
    public TransactionManager getTransactionManager() {
        final LocalTransaction transaction = dataSource.getLocalTransaction(getJdbcLogger());
        return new LocalTransactionManager(transaction);
    }

    public static DomaConfig singleton() {
        return singleton;
    }
}
