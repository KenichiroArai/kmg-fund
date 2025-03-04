package kmg.tool.infrastructure.context;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import kmg.core.infrastructure.common.KmgCommonMsgMessageTypes;

/**
 * KMGメッセージリソース
 */
@Component
public class KmgMessageSource {

    /** メッセージリソース */
    @Autowired
    private MessageSource messageSource;

    /**
     * メッセージを取得する
     *
     * @param kmgCommonMsgMessageTypes
     *                        メッセージの種類
     *
     * @return メッセージ
     */
    public String getMessage(final KmgCommonMsgMessageTypes kmgCommonMsgMessageTypes) {

        final String result = this.getMessage(kmgCommonMsgMessageTypes, null);
        return result;

    }

    /**
     * メッセージを取得する
     *
     * @param kmgCommonMsgMessageTypes
     *                        メッセージの種類
     * @param args
     *                        引数
     *
     * @return メッセージ
     */
    public String getMessage(final KmgCommonMsgMessageTypes kmgCommonMsgMessageTypes, final Object[] args) {

        final String result = this.messageSource.getMessage(kmgCommonMsgMessageTypes.getCode(), args, Locale.JAPANESE);
        return result;

    }

}
