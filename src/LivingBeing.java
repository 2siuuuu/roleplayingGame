import java.util.Set;

public class LivingBeing {
    // 내 게임의 생명체라면 응당 가지는 능력ㆍ특성이다.
    // 캡슐화를 위해 protected를 사용하여 하위 클래스에서만 접근 가능하게 합니다.
    protected String name;
    protected int healthPoint;
    protected int attackPower;
    protected int energy;
    // 에너지가 0이 되면 피해를 50% 더 받는다.

    protected int statement;
    Set<Integer> statementType;
    // 0 - 사망, 1 - 생존, 2 - 허약, 3 - 도망

    protected boolean isBattleAvailable;
    // LivingBeing의 필드로 선언. why? 모두가 가지는 근본상태임. plyer만이, monster만이 가지는 상태는 아님.

    private boolean isjudged;

    // 생성자: 객체 초기화
    public LivingBeing(String name, int healthPoint, int attackPower, int energy) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.attackPower = attackPower;
        this.energy = energy;
        statementType = Set.of(0,1,2,3);
        this.statement = 1;
        //전투 시작 시 생존상태로 설정 필요.

        setBattleAvailable(true);
    }


    public boolean isJudged() {
        return this.isjudged;
    }

    public void setIsJudged(boolean flag) {
        this.isjudged = flag;
    }

    public void setBattleAvailable(boolean flag) {
        this.isBattleAvailable = flag;
    }

    public boolean isBattleAvailable() {
        return this.isBattleAvailable;
    }


    // 메서드: 생존 여부 확인
    public boolean isAlive() {
        // 이 메서드에서 생존 여부 판단 기준은 오직 hp 임.
        return this.healthPoint > 0;
    }

    // 메서드: 피해를 입음
    public void takeDamage(int damage) {
        System.out.println(this.name + "이(가) " + damage + "의 피해를 입었다.");

        // Math.max(a,b)의 결과가 a < b 이면 b를 반환한다. a > b이면 a를 반환한다. (큰 값을 반환함.)
        this.healthPoint = Math.max(this.healthPoint - damage, 0);

        // 계산 후 체력이 0이면 대상이 쓰러졌다고 알린다.
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

    // 메서드: 도망
    public void avoid_battle(LivingBeing target) {

    }

    // Getter 메서드 (필요에 따라 추가): 현재 HP를 알 수 있게 합니다.
    public int getHealthPoint() {
        return healthPoint;
    }

    // Getter 메서드 : 현재 에너지 리턴
    public int getEnergy() {
        return energy;
    }

    // Getter 메서드 : 현재 상태 리턴
    public int getStatement() {
        // 0 - 사망, 1 - 생존, 2 - 허약, 3 - 도망
        return statement;
    }

    public void setStatement(int value) {
        // 0 - 사망, 1 - 생존, 2 - 허약, 3 - 도망
        if (!this.statementType.contains(value)) {
            // value가 0,1,2,3 중 없을 경우 예외 처리
            System.out.println("입력값 오류");
        } else {
            this.statement = value;
        }
    }

}