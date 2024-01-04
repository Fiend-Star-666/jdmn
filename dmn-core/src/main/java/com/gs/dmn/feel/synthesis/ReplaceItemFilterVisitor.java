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
package com.gs.dmn.feel.synthesis;

import com.gs.dmn.error.ErrorHandler;
import com.gs.dmn.feel.analysis.syntax.ast.Element;
import com.gs.dmn.feel.analysis.syntax.ast.expression.Name;
import com.gs.dmn.feel.analysis.syntax.ast.visitor.CloneVisitor;

public class ReplaceItemFilterVisitor<T, C> extends CloneVisitor<T, C> {
    private final String oldLambdaParameterName;
    private final String newLambdaParameterName;

    public ReplaceItemFilterVisitor(String oldLambdaParameterName, String newLambdaParameterName, ErrorHandler errorHandler) {
        super(errorHandler);
        this.oldLambdaParameterName = oldLambdaParameterName;
        this.newLambdaParameterName = newLambdaParameterName;
    }

    //
    // Primary expressions
    //
    @Override
    public Element<T> visit(Name<T> element, C context) {
        if (element == null) {
            return null;
        }

        String name = element.getName();
        if (this.oldLambdaParameterName.equals(name)) {
            element.setName(this.newLambdaParameterName);
        }
        return element;
    }
}
