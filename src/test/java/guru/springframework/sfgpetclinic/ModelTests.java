package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

/**
 * Interfaces can be implemented in JUnit 5 by test classes inheriting their annotations and properties
 */
@Tag("model")
public interface ModelTests {

    @BeforeEach
    default void beforeEachConsoleOutputer(TestInfo testInfo){
        System.out.println("Running Test - " + testInfo.getDisplayName());
    }
}
