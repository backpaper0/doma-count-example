package com.example.config;

import org.seasar.doma.jdbc.SelectOptions;

public class CountOnlySelectOptions extends SelectOptions {

    private CountOnlySelectOptions() {
        count();
    }

    public static SelectOptions get() {
        return new CountOnlySelectOptions();
    }
}
