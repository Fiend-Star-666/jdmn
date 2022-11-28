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

import com.gs.dmn.ast.DMNBaseElement;
import com.gs.dmn.ast.TImport;
import com.gs.dmn.serialization.DMNVersion;
import com.gs.dmn.serialization.xstream.v1_1.NamedElementConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ImportConverter extends NamedElementConverter {
    public static final String NAMESPACE = "namespace";
    public static final String LOCATION_URI = "locationURI";
    public static final String IMPORT_TYPE = "importType";

    public ImportConverter(XStream xstream, DMNVersion version) {
        super(xstream, version);
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(TImport.class);
    }

    @Override
    protected DMNBaseElement createModelObject() {
        return new TImport();
    }

    @Override
    protected void assignChildElement(Object parent, String nodeName, Object child) {
        super.assignChildElement(parent, nodeName, child);
    }

    @Override
    protected void assignAttributes(HierarchicalStreamReader reader, Object parent) {
        super.assignAttributes(reader, parent);
        TImport i = (TImport) parent;

        String namespace = reader.getAttribute(NAMESPACE);
        String locationUri = reader.getAttribute(LOCATION_URI);
        String importType = reader.getAttribute(IMPORT_TYPE);

        i.setNamespace(namespace);
        i.setLocationURI(locationUri);
        i.setImportType(importType);
    }

    @Override
    protected void writeChildren(HierarchicalStreamWriter writer, MarshallingContext context, Object parent) {
        super.writeChildren(writer, context, parent);
    }

    @Override
    protected void writeAttributes(HierarchicalStreamWriter writer, Object parent) {
        super.writeAttributes(writer, parent);
        TImport i = (TImport) parent;

        if (i.getNamespace() != null) {
            writer.addAttribute(NAMESPACE, i.getNamespace());
        }
        if (i.getLocationURI() != null) {
            writer.addAttribute(LOCATION_URI, i.getLocationURI());
        }
        if (i.getImportType() != null) {
            writer.addAttribute(IMPORT_TYPE, i.getImportType());
        }
    }
}
