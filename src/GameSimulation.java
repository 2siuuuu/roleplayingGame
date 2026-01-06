import java.util.Scanner;

public class GameSimulation {
    public static void main(String[] args) {
        // 사용자 입력을 위해 getScanner()
        Scanner sc = GameManager.getScanner();

        // 1. 플레이어와 몬스터 객체 생성
        Player hero = new Player("용감한 기사", 100, 25, 50, 1, 0);
        Monster goblin = new Monster("고블린", 30, 8, 20, "Normal");

        System.out.println("--- 전투 시작 ---");

        // 배틀 시작
        //
        Battle battle = new Battle(hero, goblin);

        battle.battle_start(sc);


        System.out.println("--- 전투 종료 ---");

        // GameManager - Scanner close
        sc.close();
    }
}