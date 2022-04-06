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
package com.gs.dmn.transformation.native_.statement;

import com.gs.dmn.el.analysis.semantics.type.Type;

public class ExpressionStatement extends Statement {
    private final String text;
    private final Type expressionType;

    public ExpressionStatement(String text, Type expressionType) {
        this.text = text;
        this.expressionType = expressionType;
    }

    @Override
    public String getText() {
        return text;
    }

    public Type getExpressionType() {
        return expressionType;
    }

    @Override
    public String toString() {
        return text;
    }
}
