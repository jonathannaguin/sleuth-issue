package org.springframework.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandler
{
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public Html handle(Throwable e)
    {
        logger.warn("Error happened {}", e.getMessage());
        return new Html(e.getMessage());
    }
}
