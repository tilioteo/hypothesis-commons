package org.hypothesis.interfaces.expression;

import org.hypothesis.evaluation.IndexedExpression;

import javax.annotation.Nonnull;

public interface Exchange {

    void addInput(@Nonnull IndexedExpression expression);

    void addOutput(@Nonnull IndexedExpression expression);

    void addScore(@Nonnull IndexedExpression expression);
}
