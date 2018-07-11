package com.example;

import java.util.List;
import java.util.logging.Logger;

import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.jdbc.tx.TransactionManager;

import com.example.config.DomaConfig;
import com.example.dao.MessageDao;
import com.example.dao.MessageDaoImpl;
import com.example.entity.Message;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(final String[] args) {
        final TransactionManager tm = DomaConfig.singleton().getTransactionManager();
        tm.required(App::run);
    }

    static void run() {

        final MessageDao dao = new MessageDaoImpl();

        dao.init();

        logger.info("begin ****************************************");

        logger.info("default ++++++++++++++++++++++++++++++++++++++");

        final SelectOptions selectOptions = SelectOptions.get().count();
        final List<Message> messages = dao.select(selectOptions);
        logger.info("messages = " + messages);
        logger.info("count = " + selectOptions.getCount());

        logger.info("count only +++++++++++++++++++++++++++++++++++");

        final long count = dao.count();
        logger.info("count = " + count);

        logger.info("end ******************************************");
    }
}
