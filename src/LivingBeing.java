import java.util.Set;

public class LivingBeing {
    // 생명체의 능력ㆍ특성.
    // 캡슐화를 위해 protected를 사용하여 하위 클래스에서만 접근 가능하게 합니다.
    protected String name;
    protected int healthPoint;
    protected int attackPower;
    protected int energy;
    // 에너지가 0이 되면 피해를 50% 더 받는다.

    // 생존 여부는 선언과 동시에 생존 상태로 초기화
    protected boolean alive = true;

    protected boolean isBattleAvailable;
    // LivingBeing의 필드로 선언. why? 모두가 가지는 근본상태임. plyer만이, monster만이 가지는 상태는 아님.

    private boolean isjudged;


    // 생성자: 객체 초기화
    public LivingBeing(String name, int healthPoint, int attackPower, int energy) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.attackPower = attackPower;
        this.energy = energy;

        setBattleAvailable(true);
    }


    // Get메서드 : judged 여부 반환
    public boolean isJudged() {
        return this.isjudged;
    }

    // Get메서드 : 전투 가능 상태 반환
    public boolean isBattleAvailable() {
        return this.isBattleAvailable;
    }

    // Get메서드: 생존 여부 확인
    public boolean isAlive() {
        return alive;
    }

    // Getter 메서드 (필요에 따라 추가): 현재 HP를 알 수 있게 합니다.
    public int getHealthPoint() {
        return healthPoint;
    }

    // Getter 메서드 : 현재 에너지 리턴
    public int getEnergy() {
        return energy;
    }


    // Set 메서드 : 객체의 judged 여부 변경.
    public void setIsJudged(boolean flag) {
        this.isjudged = flag;
    }

    // Set 메서드 : 전투 가능 상태 변경
    public void setBattleAvailable(boolean flag) {
        this.isBattleAvailable = flag;
    }

    // Set 메서드 : alive의 값 변경. new
    // 이 메서드는 setStatus에서만 호출할 수 있다.
    private void setAlive(boolean alive) { this.alive = alive; }

    // Set 메서드 : 객체의 생존 상태 변경. new
    public void setAliveStatus(String value) {
        // "LIVE","DEATH" 중 하나의 값을 받아 setAlive() 호출.
        if ("LIVE".equalsIgnoreCase(value)) setAlive(true);
        else if ("DEATH".equalsIgnoreCase(value)) setAlive(false);
        else throw new IllegalArgumentException("Invalid status: " + value);// 값 오류 시 throw.
    }


    // 메서드: 피해를 입음
    public void takeDamage(int damage) {
        // Math.max(a,b)의 결과가 a < b 이면 b를 반환한다. a > b이면 a를 반환한다. (큰 값을 반환함.)
        this.healthPoint = Math.max(this.healthPoint - damage, 0);

        System.out.println(this.name + "이(가) " + damage + "의 피해를 입었다.");

        // 계산 후 체력이 0 이하이면 대상이 쓰러졌다고 알린다.
        if (this.healthPoint <= 0) {
            setAliveStatus("DEATH");
            // 해당 생명체는 죽기만 하면 되고, 죽음을 판정했음을 알리는 메시지는 Battle에서 주관하면 된다.
            // 즉 아래 메시지는 이곳에 있으면 안 된다.
            System.out.println(this.name + "이(가) 쓰러졌다.");
        }
    }

    // 메서드: 공격
    // 하위 클래스에서 이 동작을 오버라이딩(재정의)하여 다르게 구현할 수 있습니다.
    public void attack(LivingBeing target) {
        System.out.println(this.name + "이(가) " + target.name + "을(를) 공격!");
        target.takeDamage(this.attackPower);
    }

    // 메서드 : 방어
    public void defend(LivingBeing target) {

    }

    // 메서드: 도망
    public void avoid_battle(LivingBeing target) {

    }




}