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
package com.gs.dmn.tck;

import org.junit.Test;

public class CL2TestCasesToJUnitTransformerTest extends AbstractTCKTestCasesToJUnitTransformerTest {
    @Override
    protected String getDMNInputPath() {
        return "tck/cl2/input";
    }

    @Override
    protected String getTestCasesInputPath() {
        return getDMNInputPath();
    }

    @Override
    protected String getExpectedPath() {
        return "tck/cl2/expected/test";
    }

    @Test
    public void testCL2() throws Exception {
        doTest("0001-input-data-string", "0001-input-data-string-test-01");
        doTest("0002-input-data-number", "0002-input-data-number-test-01");
        doTest("0003-input-data-string-allowed-values", "0003-input-data-string-allowed-values-test-01");
        doTest("0004-simpletable-U", "0004-simpletable-U-test-01");
        doTest("0005-simpletable-A", "0005-simpletable-A-test-01");
        doTest("0006-simpletable-P1", "0006-simpletable-P1-test-01");
        doTest("0007-simpletable-P2", "0007-simpletable-P2-test-01");
        doTest("0008-LX-arithmetic", "0008-LX-arithmetic-test-01");
        doTest("0009-invocation-arithmetic", "0009-invocation-arithmetic-test-01");
        doTest("0010-multi-output-U", "0010-multi-output-U-test-01");
        doTest("0100-feel-constants", "0100-feel-constants-test-01");
        doTest("0101-feel-constants", "0101-feel-constants-test-01");
        doTest("0102-feel-constants", "0102-feel-constants-test-01");
        doTest("0105-feel-math", "0105-feel-math-test-01");
        doTest("0106-feel-ternary-logic", "0106-feel-ternary-logic-test-01");
        doTest("0107-feel-ternary-logic-not", "0107-feel-ternary-logic-not-test-01");
    }
}
