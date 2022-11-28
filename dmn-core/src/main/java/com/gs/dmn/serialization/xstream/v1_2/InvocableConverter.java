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

import com.gs.dmn.ast.TInformationItem;
import com.gs.dmn.ast.TInvocable;
import com.gs.dmn.serialization.DMNVersion;
import com.gs.dmn.serialization.xstream.v1_1.DRGElementConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public abstract class InvocableConverter extends DRGElementConverter {
    public static final String VARIABLE = "variable";

    public InvocableConverter(XStream xstream, DMNVersion version) {
        super(xstream, version);
    }

    @Override
    protected void assignChildElement(Object parent, String nodeName, Object child) {
        TInvocable bkm = (TInvocable) parent;

        if (VARIABLE.equals(nodeName)) {
            bkm.setVariable((TInformationItem) child);
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

        TInvocable bkm = (TInvocable) parent;

        if (bkm.getVariable() != null) {
            writeChildrenNode(writer, context, bkm.getVariable(), VARIABLE);
        }
    }

    @Override
    protected void writeAttributes(HierarchicalStreamWriter writer, Object parent) {
        super.writeAttributes(writer, parent);

        // no attributes.
    }
}
