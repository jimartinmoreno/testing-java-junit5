package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

/**
 * Interfaces can be implemented in JUnit 5 by test classes inheriting their annotations and
 * properties
 *
 * @BeforeAll esta anotación tiene que ir un método static si no es asi tenemos que anotar la clase
 * como @TestInstance
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS) allows us to modify the lifecycle of the test class
 * using the @TestInstance annotation. Is the same as make the methods and variables static.
 * They are shared between the test
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("Controllers")
public interface ControllerTests {


    @BeforeAll
    //static void  beforeAll(){
    default void beforeAll(){
        System.out.println("Lets do something here");
    }

}
