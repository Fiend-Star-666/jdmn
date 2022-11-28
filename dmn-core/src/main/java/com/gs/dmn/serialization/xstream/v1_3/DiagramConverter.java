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
package com.gs.dmn.serialization.xstream.v1_3;

import com.gs.dmn.ast.dmndi.Diagram;
import com.gs.dmn.serialization.DMNVersion;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public abstract class DiagramConverter extends DiagramElementConverter {
    private static final String RESOLUTION = "resolution";
    private static final String DOCUMENTATION = "documentation";
    private static final String NAME = "name";

    public DiagramConverter(XStream xstream, DMNVersion version) {
        super(xstream, version);
    }

    @Override
    protected void assignChildElement(Object parent, String nodeName, Object child) {
        super.assignChildElement(parent, nodeName, child);
    }

    @Override
    protected void assignAttributes(HierarchicalStreamReader reader, Object parent) {
        super.assignAttributes(reader, parent);
        Diagram abs = (Diagram) parent;

        String name = reader.getAttribute(NAME);
        String documentation = reader.getAttribute(DOCUMENTATION);
        String resolution = reader.getAttribute(RESOLUTION);

        if (name != null) {
            abs.setName(name);
        }
        if (documentation != null) {
            abs.setDocumentation(documentation);
        }
        if (resolution != null) {
            abs.setResolution(Double.valueOf(resolution));
        }
    }

    @Override
    protected void writeChildren(HierarchicalStreamWriter writer, MarshallingContext context, Object parent) {
        super.writeChildren(writer, context, parent);
    }

    @Override
    protected void writeAttributes(HierarchicalStreamWriter writer, Object parent) {
        super.writeAttributes(writer, parent);
        Diagram abs = (Diagram) parent;

        if (abs.getName() != null) {
            writer.addAttribute(NAME, abs.getName());
        }
        if (abs.getDocumentation() != null) {
            writer.addAttribute(DOCUMENTATION, abs.getDocumentation());
        }
        if (abs.getResolution() != null) {
            writer.addAttribute(RESOLUTION, abs.getResolution().toString());
        }
    }
}
