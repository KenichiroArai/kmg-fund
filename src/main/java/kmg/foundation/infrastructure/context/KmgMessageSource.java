package kmg.foundation.infrastructure.context;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import kmg.core.infrastructure.common.KmgCommonLogMessageTypes;
import kmg.core.infrastructure.common.KmgCommonMessageTypes;
import kmg.core.infrastructure.common.KmgCommonMsgMessageTypes;
import kmg.core.infrastructure.type.KmgString;

/**
 * KMGメッセージリソース
 *
 * @since 0.1.0
 */
@Component
public class KmgMessageSource {

    /**
     * コード埋め込みフォーマット
     *
     * @since 0.1.0
     */
    private static final String CODE_EMBEDDING_FORMAT = "[%s] %s"; //$NON-NLS-1$

    /** メッセージリソース */
    @Autowired
    private MessageSource messageSource;

    /**
     * メッセージを取得する
     *
     * @since 0.1.0
     *
     * @param type
     *                          メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     * @param codeEmbeddingFlag
     *                          コード埋め込みフラグ。trueの場合、メッセージコードをメッセージの先頭に追加します。 例: "[E001] エラーメッセージ"
     *
     * @return メッセージ
     */
    public String getMessage(final KmgCommonMessageTypes type, final boolean codeEmbeddingFlag) {

        final String result = this.getMessage(type, null, codeEmbeddingFlag);
        return result;

    }

    /**
     * メッセージを取得する
     *
     * @since 0.1.0
     *
     * @param type
     *                          メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     * @param args
     *                          引数
     * @param codeEmbeddingFlag
     *                          コード埋め込みフラグ。trueの場合、メッセージコードをメッセージの先頭に追加します。 例: "[E001] エラーメッセージ"
     *
     * @return メッセージ
     */
    public String getMessage(final KmgCommonMessageTypes type, final Object[] args, final boolean codeEmbeddingFlag) {

        String result = KmgString.EMPTY;

        String message = this.messageSource.getMessage(type.getCode(), args, Locale.JAPANESE);

        // コード埋め込みフラグがtrueか
        if (codeEmbeddingFlag) {
            // trueの場合

            // メッセージコードを先頭に付加する
            message = String.format(KmgMessageSource.CODE_EMBEDDING_FORMAT, type.getCode(), message);

        }

        result = message;
        return result;

    }

    /**
     * ログメッセージを取得する
     *
     * @since 0.1.0
     *
     * @param type
     *             メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     * @param args
     *             引数
     *
     * @return メッセージ
     */
    public String getLogMessage(final KmgCommonLogMessageTypes type, final Object[] args) {

        final String result = this.getMessage(type, args, true);
        return result;

    }

    /**
     * ログメッセージを取得する
     *
     * @since 0.1.0
     *
     * @param type
     *             メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     *
     * @return メッセージ
     */
    public String getLogMessage(final KmgCommonLogMessageTypes type) {

        final String result = this.getMessage(type, true);
        return result;

    }

    /**
     * メッセージメッセージを取得する
     *
     * @since 0.1.0
     *
     * @param type
     *             メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     * @param args
     *             引数
     *
     * @return メッセージ
     */
    public String getMsgMessage(final KmgCommonMsgMessageTypes type, final Object[] args) {

        final String result = this.getMessage(type, args, false);
        return result;

    }

    /**
     * メッセージメッセージを取得する
     *
     * @since 0.1.0
     *
     * @param type
     *             メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     *
     * @return メッセージ
     */
    public String getMsgMessage(final KmgCommonMsgMessageTypes type) {

        final String result = this.getMessage(type, false);
        return result;

    }

}
