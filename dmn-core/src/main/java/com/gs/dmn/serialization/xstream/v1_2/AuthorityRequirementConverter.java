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
package com.gs.dmn.serialization.xstream.v1_2;

import com.gs.dmn.ast.DMNBaseElement;
import com.gs.dmn.ast.TAuthorityRequirement;
import com.gs.dmn.ast.TDMNElementReference;
import com.gs.dmn.serialization.DMNVersion;
import com.gs.dmn.serialization.xstream.v1_1.DMNElementConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class AuthorityRequirementConverter extends DMNElementConverter {
    public static final String REQUIRED_AUTHORITY = "requiredAuthority";
    public static final String REQUIRED_INPUT = "requiredInput";
    public static final String REQUIRED_DECISION = "requiredDecision";

    public AuthorityRequirementConverter(XStream xstream, DMNVersion version) {
        super(xstream, version);
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(TAuthorityRequirement.class);
    }

    @Override
    protected DMNBaseElement createModelObject() {
        return new TAuthorityRequirement();
    }

    @Override
    protected void assignChildElement(Object parent, String nodeName, Object child) {
        TAuthorityRequirement ar = (TAuthorityRequirement) parent;

        if (REQUIRED_DECISION.equals(nodeName)) {
            ar.setRequiredDecision((TDMNElementReference) child);
        } else if (REQUIRED_INPUT.equals(nodeName)) {
            ar.setRequiredInput((TDMNElementReference) child);
        } else if (REQUIRED_AUTHORITY.equals(nodeName)) {
            ar.setRequiredAuthority((TDMNElementReference) child);
        } else {
            super.assignChildElement(parent, nodeName, child);
        }
    }

    @Override
    protected void assignAttributes(HierarchicalStreamReader reader, Object parent) {
        super.assignAttributes(reader, parent);

        // no attributes.
    }

    @Override
    protected void writeChildren(HierarchicalStreamWriter writer, MarshallingContext context, Object parent) {
        super.writeChildren(writer, context, parent);
        TAuthorityRequirement ar = (TAuthorityRequirement) parent;

        if (ar.getRequiredDecision() != null) {
            writeChildrenNode(writer, context, ar.getRequiredDecision(), REQUIRED_DECISION);
        }
        if (ar.getRequiredInput() != null) {
            writeChildrenNode(writer, context, ar.getRequiredInput(), REQUIRED_INPUT);
        }
        if (ar.getRequiredAuthority() != null) {
            writeChildrenNode(writer, context, ar.getRequiredAuthority(), REQUIRED_AUTHORITY);
        }
    }

    @Override
    protected void writeAttributes(HierarchicalStreamWriter writer, Object parent) {
        super.writeAttributes(writer, parent);

        // no attributes.
    }
}
