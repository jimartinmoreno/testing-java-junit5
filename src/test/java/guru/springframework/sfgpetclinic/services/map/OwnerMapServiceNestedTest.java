package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Cada @BeforeEach es ejecutado antes de cada test, esto provoca que incializan los datos antes de
 * ejecutar cada uno de los test
 */

@DisplayName("Owner Map Service Test - ")
@Tag(value = "Service")
class OwnerMapServiceNestedTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);
    }

    @DisplayName("Verify Zero Owners")
    @Test
    void ownersAreZero() {
        int ownerCount = ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }

    @DisplayName("Pet Type - ")
    @Nested
    class TestCreatePetTypes {

        @BeforeEach
        void setUp() {
            PetType petType = new PetType(1L, "Dog");
            PetType petType2 = new PetType(2L, "Cat");
            petTypeService.save(petType);
            petTypeService.save(petType2);
        }

        @DisplayName("Test Pet Count")
        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();
            assertThat(petTypeCount).isNotZero().isEqualTo(2);
        }

        @DisplayName("Save Owners Tests - ")
        @Nested
        class SaveOwnersTests {

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Before", "Each"));
            }

            @DisplayName("Save Owner")
            @Test
            void saveOwner() {
                Owner owner = new Owner(2L, "Joe", "Buck");
                Owner savedOwner = ownerMapService.save(owner);
                assertThat(savedOwner).isNotNull();
            }

            @DisplayName("Save Owners Tests - ")
            @Nested
            class FindOwnersTests {

                @DisplayName("Find Owner")
                @Test
                void findOwner() {
                    Owner foundOwner = ownerMapService.findById(1L);
                    assertThat(foundOwner).isNotNull();
                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound() {
                    Owner foundOwner = ownerMapService.findById(2L);
                    assertThat(foundOwner).isNull();
                }
            }
        }
    }

    @DisplayName("Verify Still Zero Owners")
    @Test
    void ownersAreStillZero() {
        int ownerCount = ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }
}