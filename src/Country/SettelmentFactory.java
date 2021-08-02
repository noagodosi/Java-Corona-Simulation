package Country;

import Location.Location;
import Population.Person;

import java.util.List;

public class SettelmentFactory {

    public static Settlement createSettelment(String type,String name, Location location, List<Person> people, RamzorColor ramzorColor, int maxPeople){
        if(type.equals("City")){
            return new City( name, location, people, ramzorColor, maxPeople);
        }
        if(type.equals("Moshav")){
            return new Moshav( name, location, people, ramzorColor, maxPeople);
        }
        else {
            return new Kibbutz( name, location, people, ramzorColor, maxPeople);
        }
    }
}
