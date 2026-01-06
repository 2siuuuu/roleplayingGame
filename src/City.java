public class City {
    private String name;
    private int id;

    public City(String name) {
        // city가 3개 이상 생성되는 것을 방지한다.
        if(this.id != 3) {
            this.name = name;
            this.id += 1;
        }
    }

    public String getName() {
        return name;
    }
}
