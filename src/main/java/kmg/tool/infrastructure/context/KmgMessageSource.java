package kmg.tool.infrastructure.context;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import kmg.core.infrastructure.common.KmgMessageTypes;

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
     * @param kmgMessageTypes
     *                        メッセージの種類
     *
     * @return メッセージ
     */
    public String getMessage(final KmgMessageTypes kmgMessageTypes) {

        final String result = this.getMessage(kmgMessageTypes, null);
        return result;

    }

    /**
     * メッセージを取得する
     *
     * @param kmgMessageTypes
     *                        メッセージの種類
     * @param args
     *                        引数
     *
     * @return メッセージ
     */
    public String getMessage(final KmgMessageTypes kmgMessageTypes, final Object[] args) {

        final String result = this.messageSource.getMessage(kmgMessageTypes.getCode(), args, Locale.JAPANESE);
        return result;

    }

}
