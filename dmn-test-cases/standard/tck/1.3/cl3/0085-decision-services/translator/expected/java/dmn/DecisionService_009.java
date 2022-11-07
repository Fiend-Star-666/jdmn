
import java.util.*;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = {"ds.ftl", "decisionService_009"})
@com.gs.dmn.runtime.annotation.DRGElement(
    namespace = "",
    name = "decisionService_009",
    label = "",
    elementKind = com.gs.dmn.runtime.annotation.DRGElementKind.DECISION_SERVICE,
    expressionKind = com.gs.dmn.runtime.annotation.ExpressionKind.OTHER,
    hitPolicy = com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
    rulesCount = -1
)
public class DecisionService_009 extends com.gs.dmn.runtime.DefaultDMNBaseDecision {
    public static final com.gs.dmn.runtime.listener.DRGElement DRG_ELEMENT_METADATA = new com.gs.dmn.runtime.listener.DRGElement(
        "",
        "decisionService_009",
        "",
        com.gs.dmn.runtime.annotation.DRGElementKind.DECISION_SERVICE,
        com.gs.dmn.runtime.annotation.ExpressionKind.OTHER,
        com.gs.dmn.runtime.annotation.HitPolicy.UNKNOWN,
        -1
    );

    private static class DecisionService_009LazyHolder {
        static final DecisionService_009 INSTANCE = new DecisionService_009();
    }
    public static DecisionService_009 instance() {
        return DecisionService_009LazyHolder.INSTANCE;
    }

    private final Decision_009_2 decision_009_2;

    private DecisionService_009() {
        this(new Decision_009_2());
    }

    private DecisionService_009(Decision_009_2 decision_009_2) {
        this.decision_009_2 = decision_009_2;
    }

    @java.lang.Override()
    public String apply(java.util.Map<String, String> input_, com.gs.dmn.runtime.ExecutionContext context_) {
        try {
            return apply(input_.get("decision_009_3"), context_.getAnnotations(), context_.getEventListener(), context_.getExternalFunctionExecutor(), context_.getCache());
        } catch (Exception e) {
            logError("Cannot apply decision 'DecisionService_009'", e);
            return null;
        }
    }

    public String apply(String decision_009_3, com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_, com.gs.dmn.runtime.listener.EventListener eventListener_, com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_, com.gs.dmn.runtime.cache.Cache cache_) {
        try {
            // Start DS 'decisionService_009'
            long decisionService_009StartTime_ = System.currentTimeMillis();
            com.gs.dmn.runtime.listener.Arguments decisionService_009Arguments_ = new com.gs.dmn.runtime.listener.Arguments();
            decisionService_009Arguments_.put("decision_009_3", decision_009_3);
            eventListener_.startDRGElement(DRG_ELEMENT_METADATA, decisionService_009Arguments_);

            // Bind input decisions
            cache_.bind("decision_009_3", decision_009_3);

            // Evaluate DS 'decisionService_009'
            String output_ = lambda.apply(decision_009_3, annotationSet_, eventListener_, externalExecutor_, cache_);

            // End DS 'decisionService_009'
            eventListener_.endDRGElement(DRG_ELEMENT_METADATA, decisionService_009Arguments_, output_, (System.currentTimeMillis() - decisionService_009StartTime_));

            return output_;
        } catch (Exception e) {
            logError("Exception caught in 'decisionService_009' evaluation", e);
            return null;
        }
    }

    public com.gs.dmn.runtime.LambdaExpression<String> lambda =
        new com.gs.dmn.runtime.LambdaExpression<String>() {
            public String apply(Object... args_) {
                String decision_009_3 = 0 < args_.length ? (String) args_[0] : null;
                com.gs.dmn.runtime.annotation.AnnotationSet annotationSet_ = 1 < args_.length ? (com.gs.dmn.runtime.annotation.AnnotationSet) args_[1] : null;
                com.gs.dmn.runtime.listener.EventListener eventListener_ = 2 < args_.length ? (com.gs.dmn.runtime.listener.EventListener) args_[2] : null;
                com.gs.dmn.runtime.external.ExternalFunctionExecutor externalExecutor_ = 3 < args_.length ? (com.gs.dmn.runtime.external.ExternalFunctionExecutor) args_[3] : null;
                com.gs.dmn.runtime.cache.Cache cache_ = 4 < args_.length ? (com.gs.dmn.runtime.cache.Cache) args_[4] : null;

                // Apply child decisions
                String decision_009_2 = DecisionService_009.this.decision_009_2.apply(annotationSet_, eventListener_, externalExecutor_, cache_);

                return decision_009_2;
            }
    };
}