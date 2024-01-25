package com.gs.dmn.serialization.xstream;

import com.gs.dmn.serialization.DMNDialectTransformerTest;
import com.gs.dmn.serialization.SimpleDMNDialectTransformer;
import org.junit.jupiter.api.Test;

public class DMN11ToLatestDialectTransformerTest extends DMNDialectTransformerTest {
    @Test
    public void testTransform() throws Exception {
        doTest("0004-lending.dmn");
        doTest("0014-loan-comparison.dmn");
    }

    @Override
    protected SimpleDMNDialectTransformer getTransformer() {
        return new DMN11ToLatestDialectTransformer(LOGGER);
    }

    @Override
    protected String getSourceVersion() {
        return "1.1";
    }

    @Override
    protected String getTargetVersion() {
        return "1.4";
    }
}