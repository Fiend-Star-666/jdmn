
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"decision.ftl", "Bankrates"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "Bankrates",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.RELATION,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class Bankrates extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "Bankrates",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION,
        com.gs.dmn.runtime.annotation.ExpressionKind.RELATION,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    public Bankrates() {
    }

    @java.lang.Override()
    public List<type.TLoanProduct> applyMap(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(context_);
        } catch (Exception e) {
            logError("Cannot apply decision 'Bankrates'", e);
            return null;
        }
    }

    public List<type.TLoanProduct> apply(com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            // Start decision 'Bankrates'
            com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = context_ != null ? context_.getAnnotations() : null;
            com.gs.dmn.runtime.listener.EventListener eventListener_ = context_ != null ? context_.getEventListener() : null;
            com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = context_ != null ? context_.getExternalFunctionExecutor() : null;
            com.gs.dmn.runtime.cache.Cache cache_ = context_ != null ? context_.getCache() : null;
            long bankratesStartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments bankratesArguments_ = new com.gs.dmn.runtime.listener.Arguments();
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, bankratesArguments_);

            // Evaluate decision 'Bankrates'
            List<type.TLoanProduct> output_ = lambda.apply(context_);

            // End decision 'Bankrates'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, bankratesArguments_, output_, (System.currentTimeMillis() - bankratesStartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'Bankrates' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<List<type.TLoanProduct>> lambda =
        new com.gs.dmn.runtime.LambdaExpression<List<type.TLoanProduct>>() {
            public List<type.TLoanProduct> apply(Object... args_) {
                com.gs.dmn.runtime.ExecutionContext context_ = 0 < args_.length ? (com.gs.dmn.runtime.ExecutionContext) args_[0] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = context_ != null ? context_.getAnnotations() : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = context_ != null ? context_.getEventListener() : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = context_ != null ? context_.getExternalFunctionExecutor() : null;
                com.gs.dmn.runtime.cache.Cache cache_ = context_ != null ? context_.getCache() : null;

                return asList(new type.TLoanProductImpl(number("0"), "Oceans Capital", number("0"), number(".03500")),
                new type.TLoanProductImpl(number("2700"), "eClick Lending", number("1.1"), number(".03200")),
                new type.TLoanProductImpl(number("1200"), "eClickLending", number("0.1"), number(".03375")),
                new type.TLoanProductImpl(number("3966"), "AimLoan", number("1.1"), number(".03000")),
                new type.TLoanProductImpl(number("285"), "Home Loans Today", number("1.1"), number(".03125")),
                new type.TLoanProductImpl(number("4028"), "Sebonic", number("0.1"), number(".03125")),
                new type.TLoanProductImpl(number("4317"), "AimLoan", number("0.1"), number(".03125")),
                new type.TLoanProductImpl(number("2518"), "eRates Mortgage", number("1.1"), number(".03125")),
                new type.TLoanProductImpl(number("822"), "Home Loans Today", number("0.1"), number(".03250")),
                new type.TLoanProductImpl(number("1995"), "AimLoan", number("0"), number(".03250")));
            }
        };
}
