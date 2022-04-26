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
package com.gs.dmn.signavio.serialization;

import com.gs.dmn.runtime.DMNRuntimeException;
import com.gs.dmn.runtime.Pair;
import com.gs.dmn.serialization.DMN11To12DialectTransformerTest;
import com.gs.dmn.serialization.DMNReader;
import com.gs.dmn.serialization.DMNWriter;
import com.gs.dmn.signavio.SignavioTestConstants;
import com.gs.dmn.signavio.serialization.xstream.SignavioExtensionRegister;
import org.junit.Test;

import java.util.Collections;

public class SignavioDMN11To12DialectTransformerTest extends DMN11To12DialectTransformerTest {

    @Override
    @Test
    public void testTransform() throws Exception {
        doTest("Example credit decision.dmn", new Pair<>(SignavioTestConstants.SIG_NAMESPACE + "9acf44f2b05343d79fc35140c493c1e0.xml", "sig"));
    }

    @Test(expected = DMNRuntimeException.class)
    public void testTransformWithBKM() throws Exception {
        doTest("simple-decision-with-bkm.dmn", new Pair<>(SignavioTestConstants.SIG_NAMESPACE + "2521256910f54d44b0a90fa88a1aa917.xml", "sig"));
    }

    @Test
    public void testTransformWithMID() throws Exception {
        doTest("simpleMID.dmn", new Pair<>(SignavioTestConstants.SIG_NAMESPACE + "9e53b9fc18f94da39241c0319c532c9a.xml", "sig"));
    }

    @Test
    public void testTransformWithDecisionService() throws Exception {
        doTest("simple-decision-with-bkm-clone.dmn", new Pair<>(SignavioTestConstants.SIG_NAMESPACE + "2521256910f54d44b0a90fa88a1aa917.xml", "sig"));
    }

    @Override
    protected DMNReader getDMNReader() {
        return new DMNReader(LOGGER, false, Collections.singletonList(new SignavioExtensionRegister(SignavioTestConstants.SIG_EXT_NAMESPACE)));
    }

    @Override
    protected DMNWriter getDMNWriter() {
        return new DMNWriter(LOGGER, Collections.singletonList(new SignavioExtensionRegister(SignavioTestConstants.SIG_EXT_NAMESPACE)));
    }
}