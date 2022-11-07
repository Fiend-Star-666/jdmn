<#--
    Copyright 2016 Goldman Sachs.

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.

    You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations under the License.
-->
<#import "events.ftl" as events />

<#macro applyMethods drgElement>
<#if drgElement.class.simpleName == "TDecision">
    <@apply.applyMap drgElement />

    <@apply.applyString drgElement />
<#elseif drgElement.class.simpleName == "TBusinessKnowledgeModel">
    <@apply.applyMap drgElement />

<#elseif drgElement.class.simpleName == "TDecisionService">
    <@apply.applyMap drgElement />

</#if>
    <@apply.applyPojo drgElement />
</#macro>

<#macro applyMap drgElement >
    @java.lang.Override()
    public ${transformer.drgElementOutputType(drgElement)} apply(${transformer.drgElementSignatureWithMap(drgElement)}) {
    <#if transformer.canGenerateApplyWithMap(drgElement)>
        try {
            return apply(${transformer.drgElementArgumentListWithMap(drgElement)});
        } catch (Exception e) {
            logError("Cannot apply decision '${javaClassName}'", e);
            return null;
        }
    <#else>
        throw ${transformer.constructor(transformer.dmnRuntimeExceptionClassName(), "\"Not all arguments can be serialized\"")};
    </#if>
    }
</#macro>

<#macro applyString drgElement >
    <#if transformer.shouldGenerateApplyWithConversionFromString(drgElement)>
    public ${transformer.drgElementOutputType(drgElement)} apply(${transformer.drgElementSignatureWithConversionFromString(drgElement)}) {
        try {
            return apply(${transformer.drgElementArgumentListWithConversionFromString(drgElement)});
        } catch (Exception e) {
            logError("Cannot apply decision '${javaClassName}'", e);
            return null;
        }
    }

    </#if>
</#macro>

<#macro applyPojo drgElement >
    public ${transformer.drgElementOutputType(drgElement)} apply(${transformer.drgElementSignature(drgElement)}) {
        <#if drgElement.class.simpleName == "TDecisionService">
        <@applyServiceMethodBody drgElement />
        <#else>
        <@applyMethodBody drgElement />
        </#if>
    }
</#macro>

<#--
    Apply method body
-->
<#macro applyMethodBody drgElement>
        try {
        <@events.startDRGElement drgElement/>

        <#if modelRepository.isDecisionTableExpression(drgElement)>
            <@expressionApplyBody drgElement />
        <#elseif modelRepository.isLiteralExpression(drgElement)>
            <@expressionApplyBody drgElement/>
        <#elseif modelRepository.isInvocationExpression(drgElement)>
            <@expressionApplyBody drgElement/>
        <#elseif modelRepository.isContextExpression(drgElement)>
            <@expressionApplyBody drgElement/>
        <#elseif modelRepository.isRelationExpression(drgElement)>
            <@expressionApplyBody drgElement/>
        <#elseif modelRepository.isFunctionDefinitionExpression(drgElement)>
            <@expressionApplyBody drgElement/>
        <#else>
            logError("${modelRepository.expression(drgElement).class.simpleName} is not implemented yet");
            return null;
        </#if>
        } catch (Exception e) {
            logError("Exception caught in '${modelRepository.name(drgElement)}' evaluation", e);
            return null;
        }
</#macro>

<#macro applyServiceMethodBody drgElement>
        try {
        <@events.startDRGElement drgElement/>

        <@bindInputDecisions drgElement/>
        <@expressionApplyBody drgElement/>
        } catch (Exception e) {
            logError("Exception caught in '${modelRepository.name(drgElement)}' evaluation", e);
            return null;
        }
</#macro>

<#macro expressionApplyBody drgElement>
        <#if transformer.isCached(modelRepository.name(drgElement))>
            if (cache_.contains("${modelRepository.name(drgElement)}")) {
                // Retrieve value from cache
                ${transformer.drgElementOutputType(drgElement)} output_ = (${transformer.drgElementOutputType(drgElement)})cache_.lookup("${modelRepository.name(drgElement)}");

                <@events.endDRGElementAndReturnIndent "    " drgElement "output_" />
            } else {
                // ${transformer.evaluateElementCommentText(drgElement)}
                ${transformer.drgElementOutputType(drgElement)} output_ = lambda.apply(${transformer.drgElementArgumentList(drgElement)});
                cache_.bind("${modelRepository.name(drgElement)}", output_);

                <@events.endDRGElementAndReturnIndent "    " drgElement "output_" />
            }
        <#else>
            // ${transformer.evaluateElementCommentText(drgElement)}
            ${transformer.drgElementOutputType(drgElement)} output_ = lambda.apply(${transformer.drgElementArgumentList(drgElement)});

            <@events.endDRGElementAndReturn drgElement "output_" />
        </#if>
</#macro>

<#--
    Bind direct input decisions for DS
-->
<#macro bindInputDecisions drgElement>
    <@bindInputDecisionsIndent "" drgElement/>
</#macro>

<#macro bindInputDecisionsIndent extraIndent drgElement>
    <#list modelRepository.directInputDecisions(drgElement)>
            ${extraIndent}// Bind input decisions
        <#items as inputDecision>
            ${extraIndent}cache_.bind("${modelRepository.name(inputDecision.element)}", ${transformer.drgElementReferenceVariableName(inputDecision)});
        </#items>

    </#list>
</#macro>

<#---
    Evaluate method
-->
<#macro evaluateExpressionMethod drgElement>
    <#if modelRepository.isDecisionTableExpression(drgElement)>

        <@addEvaluateDecisionTableMethod drgElement/>
        <@addRuleMethods drgElement/>
        <@addConversionMethod drgElement/>
    <#elseif modelRepository.isLiteralExpression(drgElement)>

        <@addEvaluateExpressionMethod drgElement/>
    <#elseif modelRepository.isInvocationExpression(drgElement)>

        <@addEvaluateExpressionMethod drgElement/>
    <#elseif modelRepository.isContextExpression(drgElement)>

        <@addEvaluateExpressionMethod drgElement/>
    <#elseif modelRepository.isRelationExpression(drgElement)>

        <@addEvaluateExpressionMethod drgElement/>
    <#elseif modelRepository.isFunctionDefinitionExpression(drgElement)>

        <@addEvaluateExpressionMethod drgElement/>
    </#if>
</#macro>

<#macro evaluateServiceMethod drgElement>
        <@addEvaluateServiceMethod drgElement/>
</#macro>

<#--
    Decision table
-->
<#macro addEvaluateDecisionTableMethod drgElement>
    public com.gs.dmn.runtime.LambdaExpression<${transformer.drgElementOutputType(drgElement)}> lambda =
        new com.gs.dmn.runtime.LambdaExpression<${transformer.drgElementOutputType(drgElement)}>() {
            public ${transformer.drgElementOutputType(drgElement)} apply(${transformer.lambdaApplySignature()}) {
            <@extractParametersFromArgs transformer.drgElementSignatureParameters(drgElement)/>

            <@applySubDecisionsIndent "        " drgElement/>
            <#assign expression = modelRepository.expression(drgElement)>
                <@collectRuleResults drgElement expression />

                // Return results based on hit policy
                ${transformer.drgElementOutputType(drgElement)} output_;
            <#if modelRepository.isSingleHit(expression.hitPolicy)>
                if (ruleOutputList_.noMatchedRules()) {
                    // Default value
                    output_ = ${transformer.defaultValue(drgElement)};
                } else {
                    ${transformer.abstractRuleOutputClassName()} ruleOutput_ = ruleOutputList_.applySingle(${transformer.hitPolicyAnnotationClassName()}.${transformer.hitPolicy(drgElement)});
                    <#if modelRepository.isCompoundDecisionTable(drgElement)>
                    output_ = toDecisionOutput((${transformer.ruleOutputClassName(drgElement)})ruleOutput_);
                    <#else>
                    output_ = ruleOutput_ == null ? null : ((${transformer.ruleOutputClassName(drgElement)})ruleOutput_).${transformer.outputClauseGetter(drgElement, expression.output[0])};
                    </#if>
                }

                return output_;
            <#elseif modelRepository.isMultipleHit(expression.hitPolicy)>
                if (ruleOutputList_.noMatchedRules()) {
                    // Default value
                    output_ = ${transformer.defaultValue(drgElement)};
                } else {
                    List<? extends ${transformer.abstractRuleOutputClassName()}> ruleOutputs_ = ruleOutputList_.applyMultiple(${transformer.hitPolicyAnnotationClassName()}.${transformer.hitPolicy(drgElement)});
                <#if modelRepository.isCompoundDecisionTable(drgElement)>
                    <#if modelRepository.hasAggregator(expression)>
                    output_ = null;
                    <#else>
                    output_ = ruleOutputs_.stream().map(o -> toDecisionOutput(((${transformer.ruleOutputClassName(drgElement)})o))).collect(Collectors.toList());
                    </#if>
                <#else >
                    <#if modelRepository.hasAggregator(expression)>
                    output_ = ${transformer.aggregator(drgElement, expression, expression.output[0], "ruleOutputs_")};
                    <#else>
                    output_ = ruleOutputs_.stream().map(o -> ((${transformer.ruleOutputClassName(drgElement)})o).${transformer.outputClauseGetter(drgElement, expression.output[0])}).collect(Collectors.toList());
                    </#if>
                </#if>
                }

                return output_;
            <#else>
                logError("Unknown hit policy '" + ${expression.hitPolicy} + "'"));
                return output_;
            </#if>
            }
    };

</#macro>

<#macro addRuleMethods drgElement>
    <#assign expression = modelRepository.expression(drgElement)>
    <#list expression.rule>
        <#items as rule>
    @${transformer.ruleAnnotationClassName()}(index = ${rule_index}, annotation = "${transformer.annotationEscapedText(rule)}")
    public ${transformer.abstractRuleOutputClassName()} rule${rule_index}(${transformer.ruleSignature(drgElement)}) {
        // Rule metadata
        ${transformer.drgRuleMetadataClassName()} ${transformer.drgRuleMetadataFieldName()} = new ${transformer.drgRuleMetadataClassName()}(${rule_index}, "${transformer.annotationEscapedText(rule)}");

        <@events.startRule drgElement rule_index />

        // Apply rule
        ${transformer.ruleOutputClassName(drgElement)} output_ = new ${transformer.ruleOutputClassName(drgElement)}(false);
        if (${transformer.condition(drgElement, rule, rule_index)}) {
            <@events.matchRule drgElement rule_index />

            // Compute output
            output_.setMatched(true);
            <#list expression.output as output>
            output_.${transformer.outputClauseSetter(drgElement, output, "${transformer.outputEntryToNative(drgElement, rule.outputEntry[output_index], output_index)}")};
                <#if modelRepository.isOutputOrderHit(expression.hitPolicy) && transformer.outputClausePriority(drgElement, rule.outputEntry[output_index], output_index)?exists>
            output_.${transformer.outputClausePrioritySetter(drgElement, output, "${transformer.outputClausePriority(drgElement, rule.outputEntry[output_index], output_index)}")};
                </#if>
            </#list>

            <@addAnnotation drgElement rule rule_index />
        }

        <@events.endRule drgElement rule_index "output_" />

        return output_;
    }

        </#items>
    </#list>
</#macro>

<#macro collectRuleResults drgElement expression>
                // Apply rules and collect results
                ${transformer.ruleOutputListClassName()} ruleOutputList_ = new ${transformer.ruleOutputListClassName()}();
            <#assign expression = modelRepository.expression(drgElement)>
            <#list expression.rule>
                <#items as rule>
                <#if modelRepository.isFirstSingleHit(expression.hitPolicy) && modelRepository.atLeastTwoRules(expression)>
                <#if rule?is_first>
                ${transformer.abstractRuleOutputClassName()} tempRuleOutput_ = rule${rule_index}(${transformer.ruleArgumentList(drgElement)});
                ruleOutputList_.add(tempRuleOutput_);
                boolean matched_ = tempRuleOutput_.isMatched();
                <#else >
                if (!matched_) {
                    tempRuleOutput_ = rule${rule_index}(${transformer.ruleArgumentList(drgElement)});
                    ruleOutputList_.add(tempRuleOutput_);
                    matched_ = tempRuleOutput_.isMatched();
                }
                </#if>
                <#else >
                ruleOutputList_.add(rule${rule_index}(${transformer.ruleArgumentList(drgElement)}));
                </#if>
                </#items>
            </#list>
</#macro>

<#macro addConversionMethod drgElement>
    <#if modelRepository.isCompoundDecisionTable(drgElement)>
    public ${transformer.drgElementOutputInterfaceName(drgElement)} toDecisionOutput(${transformer.ruleOutputClassName(drgElement)} ruleOutput_) {
        <#assign className = transformer.drgElementOutputClassName(drgElement)>
        ${className} result_ = ${transformer.defaultConstructor(className)};
        <#assign expression = modelRepository.expression(drgElement)>
        <#list expression.output as output>
        result_.${transformer.drgElementOutputSetter(drgElement, output, "ruleOutput_ == null ? null : ruleOutput_.${transformer.outputClauseGetter(drgElement, output)}")};
        </#list>
        return result_;
    }
    </#if>
</#macro>

<#--
    Expression
-->
<#macro addEvaluateExpressionMethod drgElement>
    public com.gs.dmn.runtime.LambdaExpression<${transformer.drgElementOutputType(drgElement)}> lambda =
        new com.gs.dmn.runtime.LambdaExpression<${transformer.drgElementOutputType(drgElement)}>() {
            public ${transformer.drgElementOutputType(drgElement)} apply(${transformer.lambdaApplySignature()}) {
            <@extractParametersFromArgs transformer.drgElementSignatureParameters(drgElement)/>

            <@applySubDecisionsIndent "        " drgElement/>
            <#assign stm = transformer.expressionToNative(drgElement)>
            <#if transformer.isCompoundStatement(stm)>
                <#list stm.statements as child>
                ${child.text}
                </#list>
            <#else>
                return ${stm.text};
            </#if>
            }
        };
</#macro>

<#macro addEvaluateServiceMethod drgElement>
    public com.gs.dmn.runtime.LambdaExpression<${transformer.drgElementOutputType(drgElement)}> lambda =
        new com.gs.dmn.runtime.LambdaExpression<${transformer.drgElementOutputType(drgElement)}>() {
            public ${transformer.drgElementOutputType(drgElement)} apply(${transformer.lambdaApplySignature()}) {
            <@extractParametersFromArgs transformer.drgElementSignatureParameters(drgElement)/>

            <@applySubDecisionsIndent "        " drgElement/>
            <#assign stm = transformer.serviceToNative(drgElement)>
            <#if transformer.isCompoundStatement(stm)>
                <#list stm.statements as child>
                ${child.text}
                </#list>
            <#else>
                return ${stm.text};
            </#if>
            }
    };
</#macro>

<#macro extractParametersFromArgs arguments>
            <#list transformer.drgElementSignatureParameters(drgElement) as argument>
                ${transformer.extractParameterFromArgs(argument, argument?index)}
            </#list>
</#macro>

<#--
    Apply direct sub-decisions
-->
<#macro applySubDecisions drgElement>
    <@applySubDecisionsIndent "" drgElement/>
</#macro>

<#macro applySubDecisionsIndent extraIndent drgElement>
    <#list modelRepository.directSubDecisions(drgElement)>
        ${extraIndent}// Apply child decisions
        <#items as subDecision>
            <#if transformer.isLazyEvaluated(subDecision)>
        ${extraIndent}${transformer.lazyEvalClassName()}<${transformer.drgElementOutputType(subDecision)}> ${transformer.drgElementReferenceVariableName(subDecision)} = new ${transformer.lazyEvalClassName()}<>(() -> ${javaClassName}.this.${transformer.drgElementReferenceVariableName(subDecision)}.apply(${transformer.drgElementArgumentList(subDecision)}));
            <#else>
        ${extraIndent}${transformer.drgElementOutputType(subDecision)} ${transformer.drgElementReferenceVariableName(subDecision)} = ${javaClassName}.this.${transformer.drgElementReferenceVariableName(subDecision)}.apply(${transformer.drgElementArgumentList(subDecision)});
            </#if>
        </#items>

    </#list>
</#macro>

<#--
    Annotations
-->
<#macro addAnnotation drgElement rule rule_index>
            // Add annotation
            ${transformer.annotationSetVariableName()}.addAnnotation("${drgElement.name}", ${rule_index}, ${transformer.annotation(drgElement, rule)});
</#macro>