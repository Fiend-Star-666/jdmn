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
import com.gs.dmn.ast.TGroup;
import com.gs.dmn.serialization.DMNVersion;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class GroupConverter extends ArtifactConverter {
    public static final String NAME = "name";

    public GroupConverter(XStream xstream, DMNVersion version) {
        super(xstream, version);
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(TGroup.class);
    }

    @Override
    protected DMNBaseElement createModelObject() {
        return new TGroup();
    }

    @Override
    protected void assignAttributes(HierarchicalStreamReader reader, Object parent) {
        super.assignAttributes(reader, parent);
        TGroup grp = (TGroup) parent;

        String name = reader.getAttribute(NAME);

        grp.setName(name);
    }

    @Override
    protected void writeAttributes(HierarchicalStreamWriter writer, Object parent) {
        super.writeAttributes(writer, parent);
        TGroup grp = (TGroup) parent;

        if (grp.getName() != null) {
            writer.addAttribute(NAME, grp.getName());
        }
    }
}
