public class LivingBeing {
    // 내 게임의 생명체라면 응당 가지는 능력ㆍ특성이다.
    // 캡슐화를 위해 protected를 사용하여 하위 클래스에서만 접근 가능하게 합니다.
    protected String name;
    protected int healthPoint;
    protected int attackPower;
    protected int energy;

    // 생성자: 객체 초기화
    public LivingBeing(String name, int healthPoint, int attackPower, int energy) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.attackPower = attackPower;
        this.energy = energy;
    }

    // 메서드: 생존 여부 확인
    public boolean isAlive() {
        return this.healthPoint > 0;
    }

    // 메서드: 피해를 입음
    public void takeDamage(int damage) {
        System.out.println(this.name + "이(가) " + damage + "의 피해를 입었다.");

        // Math.max(a,b)의 결과가 a < b 이면 b를 반환한다. a > b이면 a를 반환한다. (큰 값을 반환함.)
        this.healthPoint = Math.max(this.healthPoint - damage, 0);

        if (this.healthPoint == 0) {
            System.out.println(this.name + "이(가) 쓰러졌다.");
        }
    }

    // 메서드: 공격
    // 하위 클래스에서 이 동작을 오버라이딩(재정의)하여 다르게 구현할 수 있습니다.
    public void attack(LivingBeing target) {
        System.out.println(this.name + "이(가) " + target.name + "을(를) 공격!");
        target.takeDamage(this.attackPower);
    }

    // Getter 메서드 (필요에 따라 추가): 현재 HP를 알 수 있게 합니다.
    public int getHealthPoint() {
        return healthPoint;
    }
}