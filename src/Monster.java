public class Monster extends LivingBeing {
    private final String monsterType; // 몬스터 고유 속성

    // 생성자
    public Monster(String name, int healthPoint, int attackPower, int energy, String monsterType) {
        super(name, healthPoint, attackPower, energy);
        this.monsterType = monsterType;
    }

    // Monster 고유 동작: 포효
    // 상대의 에너지를 감소시킨다.
    public void roar() {
        if (this.energy >= 10) {
            System.out.println(this.name + "이(가) 포효했다! 에너지가 10 감소한다.");
            this.energy -= 10;
        } else {
            System.out.println(this.name + "이(가) 기력이 없어 포효할 수 없다.");
        }
    }

    // (선택 사항) LivingBeing의 attack() 메서드를 Monster만의 특성으로 오버라이딩 가능
    @Override
    public void attack(LivingBeing target) {
        if (this.energy > 0) {
            // 몬스터는 공격 시 에너지를 소비하는 로직 추가
            super.attack(target); // 부모의 기본 공격 로직 실행
//            this.energy -= 1; // 에너지는 일단 주석처리. 현재 우선개발사항이 아님
            System.out.println(this.name + "의 현재 에너지: " + this.energy);
        } else {
            System.out.println(this.name + "이(가) 지쳐서 공격할 수 없다.");
        }
    }
}