package com.example;

import java.util.logging.Logger;

import org.seasar.doma.jdbc.tx.TransactionManager;

import com.example.config.DomaConfig;
import com.example.dao.MessageDao;
import com.example.dao.MessageDaoImpl;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(final String[] args) {
        final TransactionManager tm = DomaConfig.singleton().getTransactionManager();
        tm.required(App::run);
    }

    static void run() {

        final MessageDao dao = new MessageDaoImpl();

        dao.init();

        logger.info("******************** begin ********************");

        final long count = dao.count();

        logger.info("count = " + count);

        logger.info("******************** end ********************");
    }
}
