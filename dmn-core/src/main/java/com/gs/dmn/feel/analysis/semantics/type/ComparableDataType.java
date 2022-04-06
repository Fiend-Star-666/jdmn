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
package com.gs.dmn.feel.analysis.semantics.type;

import com.gs.dmn.el.analysis.semantics.type.Type;

public class ComparableDataType extends DataType {
    public static final ComparableDataType COMPARABLE = new ComparableDataType("comparable", null);

    protected ComparableDataType(String name, String conversionFunction) {
        super(name, conversionFunction);
    }

    @Override
    public boolean equivalentTo(Type other) {
        return this == other;
    }

    @Override
    public boolean conformsTo(Type other) {
        return this == other;
    }

    @Override
    public boolean isFullySpecified() {
        return true;
    }
}
