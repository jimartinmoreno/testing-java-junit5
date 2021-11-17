package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;


public class Vet extends Person {

    private Set<Speciality> specialities = new HashSet<>();

    public Vet(Long id, String firstName, String lastName, Set<Speciality> specialities) {
        super(id, firstName, lastName);
        this.specialities = specialities;
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }

    @Override
    public String toString() {
        return "Vet{" + super.toString() + ", " +
                "specialities=" + specialities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vet vet = (Vet) o;

        return getSpecialities() != null ? getSpecialities().equals(vet.getSpecialities()) : vet.getSpecialities() == null;
    }

    @Override
    public int hashCode() {
        return getSpecialities() != null ? getSpecialities().hashCode() : 0;
    }
}
