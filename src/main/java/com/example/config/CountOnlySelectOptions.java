package com.example.config;

import org.seasar.doma.jdbc.SelectOptions;

public class CountOnlySelectOptions extends SelectOptions {

    public CountOnlySelectOptions() {
        count();
    }
}
