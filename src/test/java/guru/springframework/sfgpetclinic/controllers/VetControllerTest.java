package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.ModelImpl;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;

@Tag(value = "Controller")
class VetControllerTest {

    private VetController vetController;
    private VetService vetService;
    private SpecialtyService specialtyService;
    private Vet vet;
    private Speciality speciality;


    @BeforeEach
    void setUp() {
        speciality = new Speciality(1L, "My Specialty");
        vet = new Vet(1L, "Nacho", "Martin", Set.of(speciality));

        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);
        vetService.save(vet);
        vetController = new VetController(vetService);
    }

    @Test
    void listVets() {
        ModelImpl model = new ModelImpl();
        assertEquals("vets/index", vetController.listVets(model));
        System.out.println("model = " + model);
        assertThat(model).isNotNull();

        Set<Vet> vets = (Set<Vet>) model.getAttribute("vets");
        assertThat(vets)
                .withFailMessage("%s ERROR MESSAGE: ", "isNotNull")
                .isNotNull();
        assertThat(vets.size())
                .withFailMessage("%s ERROR MESSAGE: ", "size")
                .isPositive();
        assertThat(vets.contains(vet))
                .withFailMessage("%s ERROR MESSAGE: ", "contains is True")
                .isTrue();
        assertThat(vets)
                .withFailMessage("%s ERROR MESSAGE: ", "hasSize")
                .hasSize(1).contains(vet).size().isPositive();
    }
}
