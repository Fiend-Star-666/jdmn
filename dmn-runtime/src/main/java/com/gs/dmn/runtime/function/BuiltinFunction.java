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
package com.gs.dmn.runtime.function;

import com.gs.dmn.runtime.DMNRuntimeException;
import com.gs.dmn.runtime.Function;

import java.util.List;

public class BuiltinFunction extends Function {
    public static Function of(List<?> declarations) {
        return new BuiltinFunction(declarations);
    }

    // List of declarations
    private final List<?> declarations;

    private BuiltinFunction(List<?> declarations) {
        this.declarations = declarations;
    }

    public List<?> getDeclarations() {
        return declarations;
    }

    @Override
    public Object getType() {
        throw new DMNRuntimeException("Not supported");
    }

    @Override
    public String toString() {
        return String.format("%s(declaration='%s')", getClass().getSimpleName(), declarations);
    }
}
