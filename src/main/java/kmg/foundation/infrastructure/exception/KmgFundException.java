package kmg.foundation.infrastructure.exception;

import org.springframework.beans.factory.annotation.Autowired;

import kmg.core.infrastructure.exception.KmgDomainException;
import kmg.foundation.infrastructure.common.KmgFundComGenMessageTypes;
import kmg.foundation.infrastructure.context.KmgMessageSource;

/**
 * KMG 基盤例外<br>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
public class KmgFundException extends KmgDomainException {

    /**
     * デフォルトシリアルバージョンＵＩＤ
     *
     * @since 0.1.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * KMGメッセージリソース
     *
     * @since 0.1.0
     */
    @Autowired
    private KmgMessageSource messageSource;

    /**
     * コンストラクタ<br>
     *
     * @since 0.1.0
     *
     * @param messageTypes
     *                     メッセージの種類
     */
    public KmgFundException(final KmgFundComGenMessageTypes messageTypes) {

        this(messageTypes, null, null);

    }

    /**
     * コンストラクタ<br>
     *
     * @since 0.1.0
     *
     * @param messageTypes
     *                     メッセージの種類
     * @param messageArgs
     *                     メッセージの引数
     */
    public KmgFundException(final KmgFundComGenMessageTypes messageTypes, final Object[] messageArgs) {

        this(messageTypes, messageArgs, null);

    }

    /**
     * コンストラクタ<br>
     *
     * @since 0.1.0
     *
     * @param messageTypes
     *                     メッセージの種類
     * @param messageArgs
     *                     メッセージの引数
     * @param cause
     *                     原因
     */
    public KmgFundException(final KmgFundComGenMessageTypes messageTypes, final Object[] messageArgs,
        final Throwable cause) {

        super(messageTypes, messageArgs, cause);

    }

    /**
     * コンストラクタ<br>
     *
     * @since 0.1.0
     *
     * @param messageTypes
     *                     メッセージの種類
     * @param cause
     *                     原因
     */
    public KmgFundException(final KmgFundComGenMessageTypes messageTypes, final Throwable cause) {

        this(messageTypes, null, cause);

    }

    /**
     * メッセージを作成し、返す。
     *
     * @return メッセージ
     */
    @Override
    protected String createMessage() {

        final String result = this.messageSource.getGenMessage(this.getMessageTypes(), this.getMessageArgs());
        return result;

    }

}
