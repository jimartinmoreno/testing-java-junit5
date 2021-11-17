package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * @TestMethodOrder cuando queremos que los test se ejecuten en un determinado orden
 * @DisplayName: Para indicar un nombre personalizado
 * @IndicativeSentencesGeneration funciona para generar los nombres de los test si no se especifican
 * especifitamente un @DisplayName, en caso de indicarlos utiliza este
 * @Tag Tags are used to filter which tests are executed for a given test plan.
 */

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@DisplayName("My IndexControllerTest DisplayName")
@IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
@Tag(value = "Controller")
class IndexControllerTest {

    IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @Test
    @Order(2)
    @DisplayName("My indexTest DisplayName")
    void indexTest() {
        assertEquals("index", indexController.index());
        assertEquals("index", indexController.index(), "Wrong View returned");
        assertEquals("index", indexController.index(), () -> "Wrong View returned");
        assertThat(indexController.index())
                .as("%s ERROR MESSAGE: ", "indexTest as")
                .withFailMessage("%s ERROR MESSAGE: ", "indexTest withFailMessage")
                .isEqualTo("index");
    }

    @Test
    @Order(1)
    @DisplayName("My oupsHandlerTest DisplayName :-)")
    void oupsHandlerTest() {
        assertTrue("notimplemented".equals(indexController.oupsHandler()),
                () -> "Wrong View returned");
    }

    @Test
    @DisplayName(" MyassertThrowsException")
    @Order(3)
    void assertThrowsException() {
        assertThrows(ValueNotFoundException.class, () -> {
            indexController.throwException();
        }, () -> "No se ha lanzado la excepción esperada");
    }

    @ParameterizedTest(name = "Year {0} is a leap year.")
    @ValueSource(ints = {2016, 2020, 2048})
    @Order(4)
    void if_it_is_one_of_the_following_years(int year) {
        //fail();
        assertTrue(year > 2000);
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOut() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(1000);
            System.out.println("I got here assertTimeout");
        }, "Se ha superado el tiempo del timeout dentro del assertTimeout");
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOutPrempt() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(1000);
            System.out.println("I got here assertTimeoutPreemptively");
        }, () -> "Se ha superado el tiempo del timeout dentro del assertTimeoutPreemptively");
    }

    @Test
    void testAssumptionTrue() {

        /**
         * @assumeTrue(), if called with an expression evaluating to false, the test will halt
         * and be ignored.
         */
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
        assertTrue(true);
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue() {
        assumeTrue("GURU".equalsIgnoreCase("GURU"));
        System.out.println("I got here testAssumptionTrueAssumptionIsTrue");
        assertFalse(false);
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOS() {
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows() {
    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8() {
    }

    @EnabledOnJre(JRE.JAVA_17)
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_18)
    @Test
    void testMeOnJava17() {
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "jt")
    @Test
    void testIfUserJT() {
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "Nacho")
    @Test
    void testIfUserNacho() {
    }

    @EnabledIfSystemProperty(named = "path", matches = ".*java.*")
    @Test
    void tesEnabledIfSystemProperty() {
    }
}