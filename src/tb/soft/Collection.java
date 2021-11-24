package tb.soft;

import java.util.*;

class CollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public CollectionException(String message) {
        super(message);
    }

}

public class Collection {

    private static final ConsoleUserDialog UI = new ConsoleUserDialog();

    //Metody dawania elementów do kolekcji
    public void addObjectToSetOrList(AbstractCollection<Person> collection, Person person) {
        collection.add(person);
    }

    public void addObjectToMap(AbstractMap<Integer ,Person> map, Person person, Integer k) {
        map.put(k,person);
    }

    //metody usuwania obiektów z kolekcji
    public void removeObjectFromSetOrList(AbstractCollection<Person> collection, int index){
        int start = 0;

        Iterator<Person> it = collection.iterator();
        Person resPerson = it.next();
        while (start != index) {

            resPerson = it.next();
            start += 1;
        }
            collection.remove(resPerson);
    }
    public void removeObjectFromMap(AbstractMap<Integer ,Person> map, int index) {
            map.remove(index);
    }

    //metody iteracji po kolekcjach
    public void iterateOnSetOrList(AbstractCollection<Person> collection) {
        Iterator<Person> it = collection.iterator();
        while (it.hasNext()) {
            UI.printMessage(it.next().toString());
        }
    }

    public void iterateOnMap(AbstractMap<Integer, Person> map) {
        for (Map.Entry<Integer, Person> m:
                map.entrySet()) {

            UI.printMessage((m.getKey() + ". "
                    + m.getValue()).toString());
        }
    }


}
