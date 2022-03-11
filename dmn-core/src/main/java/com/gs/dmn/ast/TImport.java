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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gs.dmn.context.DMNContext;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "@kind")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "importedValues", value = TImportedValues.class)
})
@JsonPropertyOrder({
        "name",
        "id",
        "label",
        "otherAttributes",
        "description",
        "namespace",
        "locationURI",
        "importType",
        "extensionElements"
})
public class TImport extends TNamedElement implements Visitable {
    private String namespace;
    private String locationURI;
    private String importType;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String value) {
        this.namespace = value;
    }

    public String getLocationURI() {
        return locationURI;
    }

    public void setLocationURI(String value) {
        this.locationURI = value;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String value) {
        this.importType = value;
    }

    @Override
    public Object accept(Visitor visitor, DMNContext context) {
        return visitor.visit(this, context);
    }
}
