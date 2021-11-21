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
    default void beforeEachTest(TestInfo testInfo){
        System.out.println("beforeEachTest > Running Test - " + testInfo.getDisplayName());
    }
}
