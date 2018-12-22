package io.gravitee.gateway.reactor.handler.processor;

import io.gravitee.gateway.core.processor.Processor;
import io.gravitee.gateway.reactor.handler.ResponseTimeProcessor;
import io.gravitee.gateway.reactor.handler.reporter.ReporterProcessor;
import io.gravitee.gateway.report.ReporterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class DefaultResponseProcessorChainFactory {

    @Autowired
    private ReporterService reporterService;

    private List<Processor> processors = new ArrayList<>();

    {
        processors.add(new ResponseTimeProcessor());
        processors.add(new ReporterProcessor(reporterService));
    }

    public Processor<Processor<Void>> create() {
        return new SimpleProcessorChain<>(processors);
    }
}
