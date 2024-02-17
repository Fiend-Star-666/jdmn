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
package com.gs.dmn.el.synthesis.triple;

import com.gs.dmn.ast.TDRGElement;
import com.gs.dmn.el.analysis.semantics.type.Type;

public class FunctionDefinitionTriple extends Triple {
    private final TDRGElement element;
    private final com.gs.dmn.feel.analysis.syntax.ast.expression.function.FunctionDefinition<Type> functionDefinition;
    private final boolean convertTypeToContext;
    private final Triple body;

    FunctionDefinitionTriple(TDRGElement element, com.gs.dmn.feel.analysis.syntax.ast.expression.function.FunctionDefinition<Type> functionDefinition, boolean convertTypeToContext, Triple body) {
        super();
        this.element = element;
        this.functionDefinition = functionDefinition;
        this.convertTypeToContext = convertTypeToContext;
        this.body = body;
    }

    TDRGElement getElement() {
        return element;
    }

    com.gs.dmn.feel.analysis.syntax.ast.expression.function.FunctionDefinition<Type> getFunctionDefinition() {
        return functionDefinition;
    }

    boolean isConvertTypeToContext() {
        return convertTypeToContext;
    }

    Triple getBody() {
        return body;
    }

    @Override
    public <C, R> R accept(Visitor<C, R> visitor, C context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        return "FunctionDefinition(%s, %s, %s)".formatted(element, functionDefinition, body);
    }
}
