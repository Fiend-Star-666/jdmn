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
package com.gs.dmn.serialization.xstream.v1_1;

import com.gs.dmn.ast.DMNBaseElement;
import com.gs.dmn.ast.TDMNElement;
import com.gs.dmn.ast.dmndi.DiagramElement;
import com.gs.dmn.ast.dmndi.Style;
import com.gs.dmn.serialization.DMNVersion;
import com.gs.dmn.serialization.xstream.CustomStaxReader;
import com.gs.dmn.serialization.xstream.CustomStaxWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class DMNBaseElementConverter extends DMNBaseConverter {
    private static final Logger LOG = LoggerFactory.getLogger(DMNBaseElementConverter.class);

    public DMNBaseElementConverter(XStream xstream, DMNVersion version) {
        super(xstream.getMapper(), version);
    }

    @Override
    protected void assignChildElement(Object parent, String nodeName, Object child) {
    }

    @Override
    protected void writeChildren(HierarchicalStreamWriter writer, MarshallingContext context, Object parent) {
    }

    @Override
    protected void assignAttributes(HierarchicalStreamReader reader, Object parent) {
        DMNBaseElement mib = (DMNBaseElement) parent;

        CustomStaxReader customStaxReader = (CustomStaxReader) reader.underlyingReader();

        mib.setElementInfo(customStaxReader.getElementInfo());

        setAdditionalAttributes(parent, customStaxReader.getAdditionalAttributes());
    }

    @Override
    protected void writeAttributes(HierarchicalStreamWriter writer, Object parent) {
        DMNBaseElement mib = (DMNBaseElement) parent;

        CustomStaxWriter staxWriter = ((CustomStaxWriter) writer.underlyingWriter());
        for (Entry<String, String> kv : mib.getElementInfo().getNsContext().entrySet()) {
            try {
                staxWriter.writeNamespace(kv.getKey(), kv.getValue());
            } catch (Exception e) {
                LOG.warn("The XML driver writer failed to manage writing namespace, namespaces prefixes could be wrong in the resulting file.", e);
            }
        }

        Map<QName, String> otherAttributes = getOtherAttributes(parent);
        for (Entry<QName, String> kv : otherAttributes.entrySet()) {
            staxWriter.addAttribute(kv.getKey().getPrefix() + ":" + kv.getKey().getLocalPart(), kv.getValue());
        }
    }

    private Map<QName, String> getOtherAttributes(Object parent) {
        if (parent instanceof TDMNElement) {
            return ((TDMNElement) parent).getOtherAttributes();
        } else if (parent instanceof DiagramElement) {
            return ((DiagramElement) parent).getOtherAttributes();
        } else if (parent instanceof Style) {
            return ((Style) parent).getOtherAttributes();
        } else {
            return new LinkedHashMap<>();
        }
    }

    private void setAdditionalAttributes(Object parent, Map<QName, String> additionalAttributes) {
        if (parent instanceof TDMNElement) {
            ((TDMNElement) parent).getOtherAttributes().putAll(additionalAttributes);
        } else if (parent instanceof DiagramElement) {
            ((DiagramElement) parent).getOtherAttributes().putAll(additionalAttributes);
        } else if (parent instanceof Style) {
            ((Style) parent).getOtherAttributes().putAll(additionalAttributes);
        }
    }
}
