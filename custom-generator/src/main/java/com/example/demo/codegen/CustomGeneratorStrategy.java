package com.example.demo.codegen;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class CustomGeneratorStrategy extends DefaultGeneratorStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getJavaClassName(final Definition definition, final Mode mode) {
        String name = super.getJavaClassName(definition, mode);

        // no-op

        return name;
    }
}
