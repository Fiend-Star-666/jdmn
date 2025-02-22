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

import com.gs.dmn.dialect.DMNDialectDefinition;
import com.gs.dmn.dialect.DoubleMixedJavaTimeDMNDialectDefinition;
import com.gs.dmn.tck.ast.TestCases;

import javax.xml.datatype.Duration;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

public class DoubleMixedCL2DMNInterpreterTest extends CL2SDMNInterpreterTest<Double, LocalDate, OffsetTime, ZonedDateTime, Duration> {
    @Override
    protected DMNDialectDefinition<Double, LocalDate, OffsetTime, ZonedDateTime, Duration, TestCases> getDialectDefinition() {
        return new DoubleMixedJavaTimeDMNDialectDefinition();
    }
}
