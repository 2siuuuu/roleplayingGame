public class Player extends LivingBeing {
    private int level;      // 플레이어 고유 속성
    private int experience; // 플레이어 고유 속성


    // 생성자: LivingBeing의 속성을 포함하여 Player 객체 초기화
    public Player(String name, int healthPoint, int attackPower, int energy, int level, int experience) {
        
        // 부모 클래스(LivingBeing)의 생성자 호출
        super(name, healthPoint, attackPower, energy);
        this.level = level;
        this.experience = experience;
    }

    // Player 고유 동작: 경험치 획득
    public void gainExperience(int amount) {
        this.experience += amount;
        System.out.println(this.name + "이(가) 경험치 " + amount + "을(를) 획득! (현재 경험치: " + this.experience + ")");
        // 여기에 경험치에 따른 레벨업 로직을 추가할 수 있습니다.
    }

    // Player 고유 동작: 레벨업
    public void levelUp() {
        this.level++;
        this.healthPoint += 20; // 레벨업 보너스
        this.attackPower += 5;
        System.out.println(this.name + "이(가) 레벨 " + this.level + "이(가) 되었다! 능력치 상승!");
    }

    // (선택 사항) LivingBeing의 attack() 메서드를 Player만의 특성으로 오버라이딩 가능
    /*
    @Override
    public void attack(LivingBeing target) {
        // 플레이어만의 특수 공격 로직 (예: 크리티컬 확률)
        System.out.println(this.name + "이(가) 강하게 찔렀다!");
        target.takeDamage(this.attackPower + 5); 
    }
    */
}