package guru.springframework.sfgpetclinic.junitextensions;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * Original source - https://junit.org/junit5/docs/current/user-guide/#extensions-lifecycle-callbacks-timing-extension
 */
public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final Logger logger = Logger.getLogger(TimingExtension.class.getName());

    private static final String START_TIME = "start time";

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        long startTime = System.currentTimeMillis();
        getStore(context).put(START_TIME, startTime);
        logger.info(String.format("Method [%s] startTime %s", testMethod.getName(), startTime));
        logger.info(String.format("Tags: ", context.getTags()));
        logger.info(String.format("DisplayName: ", context.getDisplayName()));
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        long startTime = getStore(context).remove(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;

        logger.info(() -> String.format("Method [%s] took %s ms.", testMethod.getName(), duration));
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestClass(), context.getRequiredTestMethod()));
    }
}