package kmg.tool.infrastructure.context;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import kmg.core.infrastructure.common.KmgCommonMessageTypes;
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

}
