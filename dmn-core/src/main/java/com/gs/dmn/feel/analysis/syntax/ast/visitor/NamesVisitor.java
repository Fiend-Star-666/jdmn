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
package com.gs.dmn.feel.analysis.syntax.ast.visitor;

import com.gs.dmn.error.ErrorHandler;
import com.gs.dmn.feel.analysis.syntax.ast.Element;
import com.gs.dmn.feel.analysis.syntax.ast.expression.Name;

import java.util.LinkedHashSet;
import java.util.Set;

public class NamesVisitor<T, C> extends TraversalVisitor<T, C> {
    private final Set<String> names;

    public NamesVisitor(ErrorHandler errorHandler) {
        super(errorHandler);
        this.names = new LinkedHashSet<>();
    }

    public Set<String> getNames() {
        return names;
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
        this.names.add(name);
        return element;
    }
}
