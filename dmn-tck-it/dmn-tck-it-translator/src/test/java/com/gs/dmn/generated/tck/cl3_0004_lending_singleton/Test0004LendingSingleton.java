package com.gs.dmn.generated.tck.cl3_0004_lending_singleton;

public class Test0004LendingSingleton extends AbstractTest0004Lending {
    @Override
    protected Routing makeRouting() {
        return Routing.instance();
    }

    @Override
    protected Strategy makeStrategy() {
        return Strategy.instance();
    }

    public static void main(String[] args) {
        new Test0004LendingSingleton().execute();
    }
}
