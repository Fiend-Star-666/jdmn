/**
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
package com.gs.dmn.transformation;

import org.junit.Test;

public class NCTckDMNToJavaTransformerTest extends AbstractTckDMNToJavaTransformerTest {
    @Test
    public void testCL3() throws Exception {
//        doTest("0015-all-any");
//        doTest("0019-flight-rebooking");
    }

    @Override
    protected String getInputPath() {
        return "tck/nc/input";
    }

    @Override
    protected String getExpectedPath() {
        return "tck/nc/expected/dmn";
    }
}
