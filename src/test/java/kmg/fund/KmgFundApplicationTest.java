package kmg.fund;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * KMG基盤アプリケーションのテスト<br>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SpringBootTest
@ActiveProfiles("test")
public class KmgFundApplicationTest {

    /**
     * アプリケーションコンテキストが正常に起動することを確認するテスト<br>
     *
     * @since 0.1.0
     */
    @Test
    public void contextLoads() {

        // コンテキストが正常に起動することを確認
    }

    /**
     * mainメソッドのテスト<br>
     *
     * @since 0.1.0
     */
    @SuppressWarnings("static-method")
    @Test
    public void testMain() {

        // テストデータの準備
        final String[] args = {};

        // テスト実行
        KmgFundApplication.main(args);

        // 検証
        // mainメソッドは正常に実行されることを確認
        // 実際の出力結果は標準出力に出力されるため、ここでは検証しない
    }
}
