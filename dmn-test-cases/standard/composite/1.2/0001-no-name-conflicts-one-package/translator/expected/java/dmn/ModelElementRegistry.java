
public class ModelElementRegistry extends com.gs.dmn.runtime.discovery.ModelElementRegistry {
    public ModelElementRegistry() {
        // Register elements from model 'Model B1'
        register("http://www.provider.com/definitions/model-b1#Evaluating Say Hello", "EvaluatingB1SayHello");
        register("http://www.provider.com/definitions/model-b1#Great the person", "GreetThePerson");
        // Register elements from model 'Model B2'
        register("http://www.provider.com/definitions/model-b2#Evaluating B2 Say Hello", "EvaluatingB2SayHello");
        // Register elements from model 'Model C'
        register("http://www.provider.com/definitions/model-c#Model C Decision based on Bs", "ModelCDecisionBasedOnBs");
        // Register elements from model 'model-a'
        register("http://www.provider.com/definitions/model-a#Person Name", "PersonName");
    }
}
