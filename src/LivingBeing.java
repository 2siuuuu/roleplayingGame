import java.util.Set;

public class LivingBeing {
    // 생명체의 능력ㆍ특성.
    // 캡슐화를 위해 protected를 사용하여 하위 클래스에서만 접근 가능하게 합니다.
    protected String name;
    protected int healthPoint;
    protected int attackPower;
    // 에너지가 0이 되면 피해를 50% 더 받는다.
    protected int energy;
    protected boolean isInDefensiveState;

    // 생존 여부는 선언과 동시에 생존 상태로 초기화
    protected boolean alive = true;

    protected boolean isBattleAvailable = false;
    // isBattleAvailable true 타이밍 : 전투 시작
    // isBattleAvailable false 타이밍 : 전투 주체 사망|도망|전투 종료

    private boolean isjudged;


    // 생성자: 객체 초기화
    public LivingBeing(String name, int healthPoint, int attackPower, int energy) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.attackPower = attackPower;
        this.energy = energy;
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

        // 피해 입기 전, 상태에 따라 데미지 계산
        damage = damageCalculator(damage);

        // Math.max(a,b)의 결과가 a < b 이면 b를 반환한다. a > b이면 a를 반환한다. (큰 값을 반환함.)
        // 계산 결과는 항상 0이거나 0 이상의 수이다.
        this.healthPoint = Math.max(this.healthPoint - damage, 0);

        System.out.println(this.name + "이(가) " + damage + "의 피해를 입었다.");

        //피해 입은 후 방어 상태였다면 방어를 해제
        if (isInDefensiveState) isInDefensiveState = false;


        // 사망 판단
        // 계산 후 체력이 0 이하이면 대상이 쓰러졌다고 알린다.
        if (this.healthPoint == 0) { // <=에서 ==으로 변경. 이유는 Line 86의 코드 설명 참고.
            setAliveStatus("DEATH");
            // 해당 생명체는 죽기만 하면 되고, 죽음을 판정했음을 알리는 메시지는 Battle에서 주관하면 된다.
            // 즉 아래 메시지는 이곳에 있으면 안 된다.
            System.out.println(this.name + "이(가) 쓰러졌다.");
        }
    }
    private int damageCalculator(int originDamage) {
        int result = originDamage;

        //현재 방어 상태 확인
        if (isInDefensiveState) {
            result = 0;
            System.out.println(this.name + "이(가) 피해를 방어했다.");
        }

        // 계산된 결과 반환
        return result;
    }

    // 메서드: 공격
    // 하위 클래스에서 이 동작을 오버라이딩(재정의)하여 다르게 구현할 수 있습니다.
    public void attack(LivingBeing target) {
        System.out.println(this.name + "이(가) " + target.name + "을(를) 공격!");
        target.takeDamage(this.attackPower);
    }

    // 메서드 : 방어
    // 해당 생명체의 방어 상태를 ON/OFF하는 메서드. 방어 상태 설정 메서드.
    public void defend(boolean value) {
        isInDefensiveState = value;
        System.out.println(this.name+"이(가) 방어 상태에 돌입했다.");
        //디버깅 로그 출력
//        System.out.println("방어 상태:"+isInDefensiveState);

    }

    // 메서드: 도망
    public void avoid_battle(LivingBeing target) {

    }




}