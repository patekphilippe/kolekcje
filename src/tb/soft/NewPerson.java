package tb.soft;

import java.util.Objects;
import java.lang.Object;

public class NewPerson extends Person implements Comparable<NewPerson>{

    public NewPerson(String first_name, String last_name) throws PersonException {
        super(first_name, last_name);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            NewPerson person = (NewPerson) obj;
            if (person.getFirstName().equals(getFirstName())) {
                if (person.getLastName().equals(getLastName())) {
                    return person.getBirthYear() == getBirthYear();
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
            return Objects.hash(getFirstName(), getLastName(), getBirthYear());
    }

    @Override
    public int compareTo(NewPerson o) {
        return 0;
    }
}
