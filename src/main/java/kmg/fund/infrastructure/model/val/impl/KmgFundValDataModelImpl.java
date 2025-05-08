package kmg.fund.infrastructure.model.val.impl;

import kmg.core.infrastructure.model.val.impl.KmgValDataModelImpl;
import kmg.fund.infrastructure.common.msg.KmgFundComValMsgTypes;
import kmg.fund.infrastructure.context.KmgMessageSource;
import kmg.fund.infrastructure.context.SpringApplicationContextHelper;

/**
 * KMG基盤バリデーションデータモデル<br>
 * <p>
 * Fundは、Foundationの略。<br>
 * Valは、Validationの略。
 * </p>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
public class KmgFundValDataModelImpl extends KmgValDataModelImpl {

    /**
     * KMGメッセージリソース
     *
     * @since 0.1.0
     */
    private KmgMessageSource messageSource;

    /**
     * コンストラクタ<br>
     *
     * @author KenichiroArai
     *
     * @since 0.1.0
     *
     * @param messageTypes
     *                     メッセージの種類
     * @param messageArgs
     *                     メッセージの引数
     */
    public KmgFundValDataModelImpl(final KmgFundComValMsgTypes messageTypes, final Object[] messageArgs) {

        super(messageTypes, messageArgs);

    }

    /**
     * メッセージを作成して返す。
     *
     * @since 0.1.0
     *
     * @return メッセージ
     */
    @Override
    protected String createMessage() {

        final String result = this.messageSource.getExcMessage(this.getMessageTypes(), this.getMessageArgs());
        return result;

    }

    /**
     * メッセージソースを作成する。
     */
    @Override
    protected void createMessageSource() {

        this.messageSource = SpringApplicationContextHelper.getBean(KmgMessageSource.class);

    }

}
