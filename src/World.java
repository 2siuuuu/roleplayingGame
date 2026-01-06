import java.util.List;

public class World {
    private String name;
    private List<City> cities;

    public World(String name, List<City> cities) {
        this.name = name;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public List<City> getCities() {
        return cities;
    }
}
