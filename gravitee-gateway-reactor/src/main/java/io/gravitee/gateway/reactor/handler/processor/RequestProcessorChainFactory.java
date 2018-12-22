package io.gravitee.gateway.reactor.handler.processor;

import io.gravitee.gateway.core.processor.Processor;
import io.gravitee.gateway.reactor.handler.transaction.TransactionProcessor;
import io.gravitee.gateway.reactor.handler.transaction.TransactionProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class RequestProcessorChainFactory {

    @Autowired
    private TransactionProcessorFactory transactionHandlerFactory;

    private List<Processor> requestProcessors;

    public Processor<Processor<Void>> create() {

        transactionHandlerFactory.create();
        return new SimpleProcessorChain<>(requestProcessors);
    }
}
