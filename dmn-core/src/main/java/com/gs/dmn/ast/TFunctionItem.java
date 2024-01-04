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
package com.gs.dmn.ast;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "label",
        "outputTypeRef",
        "otherAttributes",
        "description",
        "parameters",
        "extensionElements"
})
public class TFunctionItem extends TDMNElement implements Visitable {
    private List<TInformationItem> parameters;
    private QName outputTypeRef;

    public List<TInformationItem> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }
        return this.parameters;
    }

    public QName getOutputTypeRef() {
        return outputTypeRef;
    }

    public void setOutputTypeRef(QName value) {
        this.outputTypeRef = value;
    }

    @Override
    public <C, R> R accept(Visitor<C, R> visitor, C context) {
        return visitor.visit(this, context);
    }
}
