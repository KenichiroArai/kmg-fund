package kmg.fund.infrastructure.exception;

import kmg.core.infrastructure.exception.KmgValException;
import kmg.core.infrastructure.model.val.KmgValsModel;

/**
 * KMG 基盤バリデーション例外<br>
 * <p>
 * Fundは、Foundationの略。
 * </p>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
public class KmgFundValException extends KmgValException {

    /**
     * デフォルトシリアルバージョンＵＩＤ
     *
     * @since 0.1.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ<br>
     *
     * @since 0.1.0
     *
     * @param validationsModel
     *                         KMGバリデーション集合モデル
     */
    public KmgFundValException(final KmgValsModel validationsModel) {

        this(validationsModel, null);

    }

    /**
     * コンストラクタ<br>
     *
     * @since 0.1.0
     *
     * @param validationsModel
     *                         KMGバリデーション集合モデル
     * @param cause
     *                         原因
     */
    public KmgFundValException(final KmgValsModel validationsModel, final Throwable cause) {

        super(validationsModel, cause);

    }

}
