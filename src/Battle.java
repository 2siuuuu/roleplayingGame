import java.util.Scanner;
import java.util.Set;

public class Battle {
    private Player player;
    private Monster monster;
    private Referee referee;  // referee 객체 추가
    private boolean last_attacker; // false - player, true - monster
    // last_attacker를 변경해주는 메서드는 "현재 행위자를 변경"이라는 의미를 가진 메서드명을 가진 메서드.
    private Set<Integer> action_type;

    public Battle(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
        this.referee = new Referee(player, monster);  // referee 인스턴스 생성
        action_type = Set.of(1,2,3);
        this.last_attacker = false; // 첫 공격자(행위자)는 플레이어로 설정.
    }

    public void battle_start(Scanner sc) {
        System.out.println("전투 시작!");
        this.referee.startCombat();  // 전투 상태를 true로 변경

        // 턴 시작

        while(this.monster.isBattleAvailable() && this.player.isBattleAvailable()) {
            // 현재 둘 다 전투 가능 상태임.

            initiate_action(battle_choice(sc));
            this.referee.judge();


            // P,M의 행동을 모두 마쳤을 경우, 다음턴으로 넘어가기 위한 심사
            if (this.monster.isJudged() && this.player.isJudged() && this.referee.GetCombatState()) {
                // 둘 다 다음턴에 전투 가능한 경우 턴 수 증가
                this.referee.turn_increase();

                this.player.setIsJudged(false);
                this.monster.setIsJudged(false);
            }
        }
        // 전투 종료
        System.out.println("전투 종료.");


    }

    private int battle_choice(Scanner sc) {
        //이 메서드는 오직 플레이어에게만 유효하다. 상대(몬스터)는 자신의 행동을 스스로 결정할 수 없다.. 아직까지는..
        // 그러므로 만약 현재 턴이 몬스터이면, 무조건 공격하도록 설계.
        // 리턴값 : 1 - 공격, 2 - 방어, 3 - 도망
        if (get_last_attacker()) {
            // 현재 턴이 몬스터인 경우
            // 공격
//            this.player.attack(this.monster);
            return 1;
        }
        else {
            // 현재 턴이 플레이어인 경우
            while (true) {
                System.out.println("행동을 선택하세요: 1. 공격 | 2. 방어 | 3. 도망");
                int input = sc.nextInt();

                if (this.action_type.contains(input)) {
                    // 잘 골랐을 경우
                    return input;
                } else {
                    // 사용자가 입력한 숫자가 가능한 action 중 없을 때.
                    // 즉 잘못 입력했을 때.
                    System.out.println("딴 거 골라");
                }
            }

        }
    }

    private void turn_manager(Scanner sc) {

    }

    private void initiate_action(int actiontype) {
        if (get_last_attacker()) {
            // 현재 행동 개시자가 몬스터일 경우
            this.monster.attack(this.player);
        }
        else {
            // 현재 행동 개시자가 플레이어일 경우
            if (actiontype == 1) {
                //공격
                this.player.attack(this.monster);
            } else if (actiontype == 2) {
                //방어
                System.out.println("방어했다!");
            }
            else if (actiontype == 3) {
                // 도망
                System.out.println("도망갔다!");
            }
        }
    }

    private boolean get_last_attacker(){
        return this.last_attacker;
    }

    private void setLastAttacker() {
        if (this.last_attacker) {
            //false로 변경
            this.last_attacker = false;
        }
        else {
            this.last_attacker = true;
        }
    }

    public class Referee {
        private int turn_number;
        private boolean combat_state;
        private Player player;
        private Monster monster;


        Referee(Player player, Monster monster) {
            this.turn_number = 1;
            this.combat_state = false;
            this.player = player;
            this.monster = monster;
        }

        public void judge(){
            // 모두의 상태값 검사

            // 사망, 도망 판단
            if (!this.player.isAlive()){
                //플레이어 사망
                // 상태 변경
                this.player.setStatement(0);
                // 전투 불능으로 변경
                this.player.setBattleAvailable(false);
                System.out.println("플레이어 상태:"+this.player.getStatement());
                // 전투 종료로 변경
                endCombat();

                // 디버깅
                System.out.println("몬스터hp:"+this.monster.getHealthPoint());
                System.out.println("몬스터 상태:"+this.monster.getStatement());
                System.out.println("플레이어 hp :"+this.player.getHealthPoint());
                System.out.println("플레이어 상태 :"+this.player.getStatement());

                return;
            }
            else if(!this.monster.isAlive()){
                //몬스터 사망
                this.monster.setStatement(0);
                this.monster.setBattleAvailable(false);
                System.out.println("몬스터 상태:"+this.monster.getStatement());
                //전투 종료로 변경
                endCombat();

                // 디버깅
                System.out.println("몬스터hp:"+this.monster.getHealthPoint());
                System.out.println("몬스터 상태:"+this.monster.getStatement());
                System.out.println("플레이어 hp :"+this.player.getHealthPoint());
                System.out.println("플레이어 상태 :"+this.player.getStatement());

                return;
            }



            // 상태 이상 판단 & 값 상태 변경
            // <추가 바람>

            // 전투 지속 가능 시 :
                // 현재 턴이 누구인지 판단 후 해당 인물 judge 통과 처리
            if (get_last_attacker()){
                // 현재 행위자가 몬스터임.
                this.monster.setIsJudged(true);
            } else {
                // 현재 행위자가 플레이어임.
                this.player.setIsJudged(true);
            }

            // 마지막 행위자 변경.
            setLastAttacker();

        }

        private void startCombat() {
            this.combat_state = true;  // 전투 시작 메서드
        }

        private void endCombat() {
            this.combat_state = false;  // 전투 종료 메서드
        }

        private boolean GetCombatState() {
            return this.combat_state;
        }

        private int GetTurnNumber() {
            return this.turn_number;
        }

        private void turn_increase() {
            this.turn_number++;
        }
    }
}