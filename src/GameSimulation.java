public class GameSimulation {
    static void main(String[] args) {
        // 1. 플레이어와 몬스터 객체 생성
        Player hero = new Player("용감한 기사", 100, 15, 50, 1, 0);
        Monster goblin = new Monster("고블린", 30, 8, 20, "Normal");

        System.out.println("--- 전투 시작 ---");

        // 2. 몬스터가 플레이어를 공격 (Monster에서 오버라이딩된 attack 사용)
        goblin.attack(hero);
        System.out.println("기사 현재 HP: " + hero.getHealthPoint()); // 캡슐화된 HP를 Getter로 접근

        // 3. 플레이어가 몬스터를 공격 (LivingBeing의 기본 attack 사용)
        hero.attack(goblin);
        System.out.println("고블린 현재 HP: " + goblin.getHealthPoint());

        // 4. 몬스터 고유 행동
        goblin.roar();

        // 5. 플레이어 특수 행동 (경험치 획득)
        hero.gainExperience(50);

        System.out.println("--- 전투 종료 ---");
    }
}