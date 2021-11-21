package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
class ParameterizedTests {

    @ParameterizedTest(name = "Year {0} is a leap year.")
    @ValueSource(ints = {2016, 2020, 2048})
    @Order(4)
    void if_it_is_one_of_the_following_years(int year) {
        //fail();
        assertTrue(year > 2000);
    }

    @ParameterizedTest(name = ParameterizedTest.DEFAULT_DISPLAY_NAME)
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
        assertThat(ownerType).isIn(OwnerType.CORPORATE, OwnerType.INDIVIDUAL);
    }

    @DisplayName("CSV Input Test")
    @ParameterizedTest(name = "{displayName} - [{index}] > {arguments}")
    @CsvSource({
            "Nacho, Martin, 48",
            "Hector, Martin, 47",
            "Zina, Petrikova, 40",
    })
    void csvInputTest(String name, String surname, int age) {
        System.out.println(name + " " + surname + ", Age > " + age);
    }

    @DisplayName("CSV File Test")
    @ParameterizedTest(name = "{displayName} - [{index}] > {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1) // El fichero está en la carpeta resources
    void csvFileTest(String name, String surname, int age) {
        System.out.println(name + " " + surname + ", Age > " + age);
    }

    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = "{displayName} - [{index}] > {arguments}")
    @MethodSource("getArgs")
    void fromMethodTest(String name, String surname, int age) {
        System.out.println(name + " " + surname + ", Age > " + age);
    }

    /**
     * Method provider of the fromMethodTest, we can use this method to get data from DB,
     * Rest Service, JMS Queue, etc
     * @return Stream<Arguments>
     */
    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of("Nacho", "Martin", 48),
                Arguments.of("Hector", "Martin", 47),
                Arguments.of("Zina", "Petrikova", 40)
        );
    }

    @DisplayName("Custom Provider Test")
    @ParameterizedTest(name = "{displayName} - [{index}] > {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String name, String surname, int age) {
        System.out.println(name + " " + surname + ", Age > " + age);
    }
}
