package io.gravitee.gateway.reactor.handler.processor;

import io.gravitee.gateway.api.ExecutionContext;
import io.gravitee.gateway.api.handler.Handler;
import io.gravitee.gateway.core.processor.AbstractProcessor;
import io.gravitee.gateway.core.processor.Processor;
import io.gravitee.gateway.core.processor.ProcessorFailure;

import java.util.Iterator;
import java.util.List;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class SimpleProcessorChain<T> extends AbstractProcessor<Processor<T>> {

    private final Iterator<? extends Processor> iterator;

    public SimpleProcessorChain(List<? extends Processor> providers) {
        this.iterator = providers.iterator();
    }

    @Override
    public void handle(ExecutionContext context) {
        if (iterator.hasNext()) {
            Processor processor = iterator.next();

            processor
                    .handler(buffer -> handle(context))
                    .errorHandler(new Handler<ProcessorFailure>() {
                        @Override
                        public void handle(ProcessorFailure failure) {
                            errorHandler.handle(failure);
                        }
                    })
                    .exitHandler(stream -> exitHandler.handle((Processor<T>) stream));


            processor.handle(context);
        } else {
            next.handle(null);
        }
    }
}
