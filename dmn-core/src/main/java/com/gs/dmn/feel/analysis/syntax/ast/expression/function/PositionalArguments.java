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
package com.gs.dmn.feel.analysis.syntax.ast.expression.function;

import java.util.ArrayList;
import java.util.List;

public class PositionalArguments<T> extends Arguments<T> {
    private final List<Object> arguments;

    public PositionalArguments() {
        this(new ArrayList<>());
    }

    public PositionalArguments(List<Object> arguments) {
        if (arguments == null) {
            arguments = new ArrayList<>();
        }
        this.arguments = arguments;
    }

    public List<Object> getArguments() {
        return arguments;
    }

    public void add(Object arg) {
        this.arguments.add(arg);
    }

    @Override
    public List<Object> argumentList(List<FormalParameter<T>> formalParameters) {
        return arguments;
    }
}
