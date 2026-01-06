import java.util.Scanner;
public class GameManager {

    private static final Scanner sc = new Scanner(System.in);

    public static Scanner getScanner() {
        return sc;
    }


    // 게임 종료 시 단 한번 `sc.close();`
    //Scanner.close()는 프로그램 종료 시 한 번만!
    //여러 곳에서 닫으면 InputStream이 닫혀 입력 불가 문제 발생
}
