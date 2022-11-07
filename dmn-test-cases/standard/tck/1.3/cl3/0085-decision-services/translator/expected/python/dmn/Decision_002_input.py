import typing
import decimal
import datetime
import isodate
import time

import jdmn.runtime.Context
import jdmn.runtime.DefaultDMNBaseDecision
import jdmn.runtime.ExecutionContext
import jdmn.runtime.LambdaExpression
import jdmn.runtime.LazyEval
import jdmn.runtime.Pair
import jdmn.runtime.Range
import jdmn.runtime.RuleOutput
import jdmn.runtime.RuleOutputList

import jdmn.runtime.annotation.Annotation
import jdmn.runtime.annotation.AnnotationSet
import jdmn.runtime.annotation.DRGElementKind
import jdmn.runtime.annotation.ExpressionKind
import jdmn.runtime.annotation.HitPolicy

import jdmn.runtime.cache.Cache

import jdmn.runtime.external.ExternalFunctionExecutor

import jdmn.runtime.listener.Arguments
import jdmn.runtime.listener.DRGElement
import jdmn.runtime.listener.EventListener
import jdmn.runtime.listener.Rule


# Generated(value = ["decision.ftl", "decision_002_input"])
class Decision_002_input(jdmn.runtime.DefaultDMNBaseDecision.DefaultDMNBaseDecision):
    DRG_ELEMENT_METADATA: jdmn.runtime.listener.DRGElement.DRGElement = jdmn.runtime.listener.DRGElement.DRGElement(
        "",
        "decision_002_input",
        "",
        jdmn.runtime.annotation.DRGElementKind.DRGElementKind.DECISION,
        jdmn.runtime.annotation.ExpressionKind.ExpressionKind.LITERAL_EXPRESSION,
        jdmn.runtime.annotation.HitPolicy.HitPolicy.UNKNOWN,
        -1
    )

    def __init__(self):
        jdmn.runtime.DefaultDMNBaseDecision.DefaultDMNBaseDecision.__init__(self)

    def apply(self, context_: jdmn.runtime.ExecutionContext.ExecutionContext) -> typing.Optional[str]:
        try:
            # Start decision 'decision_002_input'
            annotationSet_: jdmn.runtime.annotation.AnnotationSet.AnnotationSet = None if context_ is None else context_.annotations
            eventListener_: jdmn.runtime.listener.EventListener.EventListener = None if context_ is None else context_.eventListener
            externalExecutor_: jdmn.runtime.external.ExternalFunctionExecutor.ExternalFunctionExecutor = None if context_ is None else context_.externalFunctionExecutor
            cache_: jdmn.runtime.cache.Cache.Cache = None if context_ is None else context_.cache
            decision_002_inputStartTime_ = int(time.time_ns()/1000)
            decision_002_inputArguments_ = jdmn.runtime.listener.Arguments.Arguments()
            eventListener_.startDRGElement(self.DRG_ELEMENT_METADATA, decision_002_inputArguments_)

            if cache_.contains("decision_002_input"):
                # Retrieve value from cache
                output_: typing.Optional[str] = cache_.lookup("decision_002_input")

                # End decision 'decision_002_input'
                eventListener_.endDRGElement(self.DRG_ELEMENT_METADATA, decision_002_inputArguments_, output_, (int(time.time_ns()/1000) - decision_002_inputStartTime_))

                return output_
            else:
                # Evaluate decision 'decision_002_input'
                output_: typing.Optional[str] = self.evaluate(context_)
                cache_.bind("decision_002_input", output_)

                # End decision 'decision_002_input'
                eventListener_.endDRGElement(self.DRG_ELEMENT_METADATA, decision_002_inputArguments_, output_, (int(time.time_ns()/1000) - decision_002_inputStartTime_))

                return output_
        except Exception as e:
            self.logError("Exception caught in 'decision_002_input' evaluation", e)
            return None

    def evaluate(self, context_: jdmn.runtime.ExecutionContext.ExecutionContext) -> typing.Optional[str]:
        return "bar"
