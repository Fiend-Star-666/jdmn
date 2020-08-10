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
package com.gs.dmn.transformation.basic;

import com.gs.dmn.DMNModelRepository;
import com.gs.dmn.DRGElementReference;
import com.gs.dmn.feel.analysis.semantics.environment.EnvironmentFactory;
import com.gs.dmn.feel.synthesis.type.NativeTypeFactory;
import com.gs.dmn.transformation.lazy.LazyEvaluationDetector;
import com.gs.dmn.transformation.native_.KotlinFactory;
import org.omg.spec.dmn._20180521.model.TDRGElement;
import org.omg.spec.dmn._20180521.model.TItemDefinition;

import java.util.Map;

public class BasicDMNToKotlinTransformer extends BasicDMNToJavaTransformer {
    public BasicDMNToKotlinTransformer(DMNModelRepository dmnModelRepository, EnvironmentFactory environmentFactory, NativeTypeFactory feelTypeTranslator, LazyEvaluationDetector lazyEvaluationDetector, Map<String, String> inputParameters) {
        super(dmnModelRepository, environmentFactory, feelTypeTranslator, lazyEvaluationDetector, inputParameters);
    }

    @Override
    protected void setNativeFactory(BasicDMNToNativeTransformer transformer) {
        this.nativeFactory = new KotlinFactory(this);
    }

    // Types
    @Override
    public String itemDefinitionNativeQualifiedInterfaceName(TItemDefinition itemDefinition) {
        return this.nativeTypeFactory.nullableType(super.itemDefinitionNativeQualifiedInterfaceName(itemDefinition));
    }

    @Override
    public String itemDefinitionNativeClassName(String interfaceName) {
        if (interfaceName.endsWith("?")) {
            return interfaceName.replace("?", "Impl?");
        } else {
            return interfaceName + "Impl";
        }
    }

    @Override
    public String makeListType(String listType, String elementType) {
        return this.nativeTypeFactory.nullableType(String.format("%s<%s>", listType, this.nativeTypeFactory.nullableType(elementType)));
    }

    @Override
    public String makeListType(String listType) {
        return this.nativeTypeFactory.nullableType(String.format("%s<Any?>", listType));
    }

    @Override
    protected String makeFunctionType(String name, String returnType) {
        return this.nativeTypeFactory.nullableType(String.format("%s<%s>", name, this.nativeTypeFactory.nullableType(returnType)));
    }

    @Override
    public String drgElementOutputType(DRGElementReference<? extends TDRGElement> reference) {
        return this.nativeTypeFactory.nullableType(super.drgElementOutputType(reference.getElement()));
    }

    @Override
    public String drgElementOutputType(TDRGElement element) {
        return this.nativeTypeFactory.nullableType(super.drgElementOutputType(element));
    }

    @Override
    public String lazyEvaluation(String elementName, String nativeName) {
        return isLazyEvaluated(elementName) ? String.format("%s?.getOrCompute()", nativeName) : nativeName;
    }
}
