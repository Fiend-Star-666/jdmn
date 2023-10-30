/*
 * Copyright 2016 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.gs.dmn.feel.analysis.syntax.ast.expression.type;

import com.gs.dmn.feel.analysis.syntax.ast.Visitor;

import java.util.Objects;

public class RangeTypeExpression<T, C> extends TypeExpression<T, C> {
    private final TypeExpression<T, C> elementTypeExpression;

    public RangeTypeExpression(TypeExpression<T, C> listType) {
        this.elementTypeExpression = listType;
    }

    public TypeExpression<T, C> getElementTypeExpression() {
        return this.elementTypeExpression;
    }

    @Override
    public Object accept(Visitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RangeTypeExpression<?, ?> that = (RangeTypeExpression<?, ?>) o;
        return Objects.equals(elementTypeExpression, that.elementTypeExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementTypeExpression);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), this.elementTypeExpression.toString());
    }
}
