package ru.kosmos.restaurantvoting;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import org.springframework.util.StopWatch;

@Slf4j
public class TimingExtension implements
        BeforeTestExecutionCallback, AfterTestExecutionCallback, BeforeAllCallback, AfterAllCallback {

    private StopWatch stopWatch;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        stopWatch = new StopWatch("Execution time of " + extensionContext.getRequiredTestClass().getSimpleName());
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        String testName = extensionContext.getDisplayName();
        log.info("\nStart " + testName);
        stopWatch.start(testName);
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        stopWatch.stop();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        log.info('\n' + stopWatch.prettyPrint() + '\n');
    }

}
