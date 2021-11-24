package tb.soft;

import java.util.*;

public class PersonCollectionApp {


        private static final String COLLECTION_MENU =
                "    WYBIERZ RODZAJ KOLEKCJI  		\n" +
                        "1 - Set  					\n" +
                        "2 - List 					\n" +
                        "3 - Map  					\n" +
                        "0 - Zakończ program        \n";

        private static final String SET_MENU =
                "    WYBIERZ KOLEKCJĘ  		\n" +
                        "1 - HashSet 				\n" +
                        "2 - TreeSet 				\n";

        private static final String LIST_MENU =
                "    WYBIERZ KOLEKCJĘ  		\n" +
                        "1 - ArrayList  			\n" +
                        "2 - LinkedList 			\n";

        private static final String MAP_MENU =
                "    WYBIERZ KOLEKCJĘ  		\n" +
                        "1 - HashMap  				\n" +
                        "2 - TreeMap				\n";

        private static final String OPERATION_MENU =
                "    WYBIERZ OPERACJĘ  		  \n" +
                        "1 - Dodaj osobę do kolekcji  \n" +
                        "2 - Usuń osobę z kolekcji    \n" +
                        "3 - Wypisz zawartość kolekcji\n" +
                        "0 - Wróć do menu             \n";

        private static final ConsoleUserDialog UI = new ConsoleUserDialog();



        public static void main(String[] args) {
                PersonCollectionApp application = new PersonCollectionApp();
                application.runMainLoop();
        }

        private NewPerson currentPerson = null;
        private Collection currentCollection = null;
        private AbstractCollection<Person> chosenCollection = null;
        private AbstractMap<Integer, Person> chosenMap = null;

        public void runMainLoop() {
                currentCollection = new Collection();
                while(true) {
                        switch(UI.enterInt(COLLECTION_MENU + "==>>")) {
                                case 1:
                                        switch(UI.enterInt(SET_MENU + "==>>")) {
                                                case 1:
                                                        runCollectionTest("HashSet");
                                                        break;
                                                case 2:
                                                        runCollectionTest("TreeSet");
                                                        break;
                                                        }
                                        break;
                                case 2:
                                       switch(UI.enterInt(LIST_MENU + "==>>")) {
                                                case 1:
                                                       runCollectionTest("ArrayList");
                                                       break;
                                                case 2:
                                                       runCollectionTest("LinkedList");
                                                       break;
                                                        }
                                       break;
                                case 3:
                                       switch(UI.enterInt(MAP_MENU + "==>>")) {
                                               case 1:
                                                      runCollectionTest("HashMap");
                                                      break;
                                               case 2:
                                                      runCollectionTest("TreeMap");
                                                      break;
                                       }
                                break;
                                case 0:
                                        UI.printMessage("Program kończy działanie\n");
                                        System.exit(0);
                        }

                }
        }

        private void runCollectionTest(String collectionName) {
                Integer run = 1;
                int index = 0;
                int counter = 0;
                if(collectionName == "HashSet" || collectionName == "TreeSet" || collectionName == "ArrayList" || collectionName == "LinkedList") {

                        if(collectionName == "HashSet") {
                                chosenCollection = new HashSet<>();
                        } else if(collectionName == "TreeSet") {
                                chosenCollection = new TreeSet<>();
                        } else if(collectionName == "ArrayList") {
                                chosenCollection = new ArrayList<>();
                        } else if(collectionName == "LinkedList") {
                                chosenCollection = new LinkedList<>();
                        }

                        while(run == 1) {
                                switch (UI.enterInt(OPERATION_MENU + "==>>")) {
                                        case 1:
                                                UI.printInfoMessage("Podaj Dane Nowej osoby, którą chcesz dodać do kolekcji");
                                                counter = counter + 1;
                                                currentPerson = createNewPerson();
                                                long addTime = System.nanoTime();
                                                currentCollection.addObjectToSetOrList(chosenCollection, currentPerson);
                                                UI.printMessage("Czas dodania osoby do kolekcji: " +
                                                        (System.nanoTime() - addTime));
                                                counter = counter + 1;
                                                break;
                                        case 2:
                                                index = UI.enterInt("podaj indeks osoby którą chcesz usunąć: ");
                                                if(counter > 0) {
                                                        counter = counter - 1;
                                                        if(index < 0 || index >= chosenCollection.size()) {
                                                                UI.printInfoMessage("Nie ma osoby o takim indeksie w kolekcji");
                                                        } else {
                                                                long deleteTime = System.nanoTime();
                                                                currentCollection.removeObjectFromSetOrList(chosenCollection, index);
                                                                UI.printMessage("Czas usunięcia osoby z kolekcji: " +
                                                                        (System.nanoTime() - deleteTime));
                                                        }

                                                } else {
                                                        UI.printInfoMessage("Nie ma osób w kolekcji");
                                                }
                                                break;
                                        case 3:
                                                if(chosenCollection != null) {
                                                        long iterateTime = System.nanoTime();
                                                        currentCollection.iterateOnSetOrList(chosenCollection);
                                                        UI.printMessage("Czas iteracji po kolekcji: " +
                                                                (System.nanoTime() - iterateTime));
                                                }
                                                else
                                                        UI.printInfoMessage("Nie ma osób w kolekcji");
                                                break;
                                        case 0:
                                                run = 0;
                                }
                        }
                } else {
                        if(collectionName == "HashMap"){
                            chosenMap = new HashMap<>();
                            counter = 0;
                        } else if(collectionName == "TreeMap") {
                            chosenMap = new TreeMap<>();
                            counter = 0;
                        }
                        while(run == 1) {
                                switch (UI.enterInt(OPERATION_MENU + "==>>")) {
                                        case 1:
                                                UI.printInfoMessage("Podaj Dane Nowej osoby, którą chcesz dodać do kolekcji");
                                                currentPerson = createNewPerson();
                                                long addTime = System.nanoTime();
                                                currentCollection.addObjectToMap(chosenMap, currentPerson, counter);
                                                UI.printMessage("Czas dodania osoby do kolekcji: " +
                                                        (System.nanoTime() - addTime));
                                                counter = counter + 1;
                                                break;
                                        case 2:
                                                index = UI.enterInt("podaj indeks osoby którą chcesz usunąć: ");
                                                if(counter != 0) {
                                                        counter = counter - 1;
                                                        if(index < 0 || index >= chosenMap.size()) {
                                                                UI.printInfoMessage("Nie ma osoby o takim indeksie w kolekcji");
                                                        } else {
                                                                long deleteTime = System.nanoTime();
                                                                currentCollection.removeObjectFromMap(chosenMap, index);
                                                                UI.printMessage("Czas usunięcia osoby z kolekcji: " +
                                                                        (System.nanoTime() - deleteTime));
                                                        }

                                                } else {
                                                        UI.printInfoMessage("Nie ma osób w kolekcji");
                                                }
                                                break;
                                        case 3:
                                                if(chosenMap != null) {
                                                        long iterateTime = System.nanoTime();
                                                        currentCollection.iterateOnMap(chosenMap);
                                                        UI.printMessage("Czas iteracji po kolekcji: " +
                                                                (System.nanoTime() - iterateTime));
                                                }
                                                else
                                                        UI.printInfoMessage("Nie ma osób w kolekcji");
                                                break;
                                        case 0:
                                                run = 0;
                                }
                        }
                }
        }

        private void runTimeTest() {

        }

        static NewPerson createNewPerson(){
                String first_name = UI.enterString("Podaj imię: ");
                String last_name = UI.enterString("Podaj nazwisko: ");
                String birth_year = UI.enterString("Podaj rok ur.: ");
                UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
                String job_name = UI.enterString("Podaj stanowisko: ");
                NewPerson person;
                try {
                        // Utworzenie nowego obiektu klasy Person oraz
                        // ustawienie wartości wszystkich atrybutów.
                        person = new NewPerson(first_name, last_name);
                        person.setBirthYear(birth_year);
                        person.setJob(job_name);
                } catch (PersonException e) {
                        // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
                        // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
                        // poszczególnych atrybutów.
                        // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
                        UI.printErrorMessage(e.getMessage());
                        return null;
                }
                return person;
        }
}
