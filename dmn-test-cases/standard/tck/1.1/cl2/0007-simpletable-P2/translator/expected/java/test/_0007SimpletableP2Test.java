
import java.util.*;
import java.util.stream.Collectors;

@jakarta.annotation.Generated(value = {"junit.ftl", "0007-simpletable-P2.dmn"})
public class _0007SimpletableP2Test extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    @org.junit.jupiter.api.Test
    public void testCase001() {
        com.gs.dmn.runtime.ExecutionContext context_ = new com.gs.dmn.runtime.ExecutionContext();
        com.gs.dmn.runtime.cache.Cache cache_ = context_.getCache();
        // Initialize input data
        java.math.BigDecimal age = number("18");
        String riskCategory = "Medium";
        Boolean isAffordable = Boolean.TRUE;

        // Check 'Approval Status'
        checkValues("Approved", new ApprovalStatus().apply(age, riskCategory, isAffordable, context_));
    }

    @org.junit.jupiter.api.Test
    public void testCase002() {
        com.gs.dmn.runtime.ExecutionContext context_ = new com.gs.dmn.runtime.ExecutionContext();
        com.gs.dmn.runtime.cache.Cache cache_ = context_.getCache();
        // Initialize input data
        java.math.BigDecimal age = number("17");
        String riskCategory = "Medium";
        Boolean isAffordable = Boolean.TRUE;

        // Check 'Approval Status'
        checkValues("Declined", new ApprovalStatus().apply(age, riskCategory, isAffordable, context_));
    }

    @org.junit.jupiter.api.Test
    public void testCase003() {
        com.gs.dmn.runtime.ExecutionContext context_ = new com.gs.dmn.runtime.ExecutionContext();
        com.gs.dmn.runtime.cache.Cache cache_ = context_.getCache();
        // Initialize input data
        java.math.BigDecimal age = number("18");
        String riskCategory = "High";
        Boolean isAffordable = Boolean.TRUE;

        // Check 'Approval Status'
        checkValues("Declined", new ApprovalStatus().apply(age, riskCategory, isAffordable, context_));
    }

    private void checkValues(Object expected, Object actual) {
        com.gs.dmn.runtime.Assert.assertEquals(expected, actual);
    }
}
