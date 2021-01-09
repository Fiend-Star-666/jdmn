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
package com.gs.dmn.runtime.interpreter;

import com.gs.dmn.DRGElementReference;
import com.gs.dmn.feel.interpreter.TypeConverter;
import com.gs.dmn.feel.lib.FEELLib;
import com.gs.dmn.runtime.DMNContext;
import com.gs.dmn.transformation.basic.BasicDMNToNativeTransformer;
import org.omg.spec.dmn._20191111.model.TDecision;
import org.omg.spec.dmn._20191111.model.TFunctionDefinition;
import org.omg.spec.dmn._20191111.model.TInvocable;

import java.util.List;
import java.util.Map;

public interface DMNInterpreter<NUMBER, DATE, TIME, DATE_TIME, DURATION> {
    BasicDMNToNativeTransformer getBasicDMNTransformer();

    FEELLib<NUMBER, DATE, TIME, DATE_TIME, DURATION> getFeelLib();

    TypeConverter getTypeConverter();

    //
    // Evaluate TDecisions
    //
    Result evaluate(DRGElementReference<? extends TDecision> reference, Map<String, Object> informationRequirements);

    //
    // Evaluate TInvocables
    //
    Result evaluate(DRGElementReference<? extends TInvocable> reference, List<Object> argList);
    Result evaluate(TInvocable invocable, List<Object> argList, DMNContext context);

    //
    // Evaluate expressions
    //
    Result evaluate(TFunctionDefinition functionDefinition, List<Object> args, DMNContext context);
}
