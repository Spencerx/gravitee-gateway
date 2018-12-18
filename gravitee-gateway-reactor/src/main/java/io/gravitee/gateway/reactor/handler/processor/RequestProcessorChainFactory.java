package io.gravitee.gateway.reactor.handler.processor;

import io.gravitee.gateway.core.processor.Processor;

import java.util.List;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class RequestProcessorChainFactory {

    private List<Processor> requestProcessors;

    public Processor<Void> create() {
        return new SimpleProcessorChain<Void>(requestProcessors);
    }
}
