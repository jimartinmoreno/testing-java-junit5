package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CustomArgsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
            throws Exception {

        return Stream.of(
                Arguments.of("Nacho", "Martin", 48),
                Arguments.of("Hector", "Martin", 47),
                Arguments.of("Zina", "Petrikova", 40)
        );
    }
}
