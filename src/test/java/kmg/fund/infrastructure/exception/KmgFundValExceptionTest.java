package kmg.fund.infrastructure.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import kmg.core.infrastructure.model.val.impl.KmgValsModelImpl;

/**
 * KMG 基盤バリデーション例外テスト<br>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SuppressWarnings({
    "static-method",
})
public class KmgFundValExceptionTest {

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    public KmgFundValExceptionTest() {

        // 処理なし
    }

    /**
     * コンストラクタのテスト<br>
     *
     * @since 0.1.0
     */
    @Test
    public void testConstructor() {

        // テストデータ
        final KmgValsModelImpl validationsModel = new KmgValsModelImpl();
        final Throwable        cause            = new RuntimeException("テスト例外");

        // テスト実行
        final KmgFundValException exception1 = new KmgFundValException(validationsModel);
        final KmgFundValException exception2 = new KmgFundValException(validationsModel, cause);

        // 検証
        Assertions.assertNotNull(exception1);
        Assertions.assertNotNull(exception2);
        Assertions.assertEquals(validationsModel, exception1.getValidationsModel());
        Assertions.assertEquals(validationsModel, exception2.getValidationsModel());
        Assertions.assertEquals(cause, exception2.getCause());

    }
}
