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
 * KMGメッセージリソース<br>
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

    /**
     * メッセージリソース
     *
     * @since 0.1.0
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * メッセージを取得する<br>
     * メッセージタイプに対応するメッセージパターンを取得し、指定されたコード埋め込みフラグに基づいてメッセージを整形します。 このメソッドは
     * {@link #getMessage(KmgCommonMessageTypes, Object[], boolean)} を引数なしで呼び出す便利メソッドです。
     *
     * @since 0.1.0
     *
     * @param type
     *                          メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     * @param codeEmbeddingFlag
     *                          コード埋め込みフラグ。trueの場合、メッセージコードをメッセージの先頭に追加します。 例: "[E001] エラーメッセージ"
     *
     * @return メッセージ。メッセージパターンが空の場合は空文字列を返します。
     *
     * @see #getMessage(KmgCommonMessageTypes, Object[], boolean)
     */
    public String getMessage(final KmgCommonMessageTypes type, final boolean codeEmbeddingFlag) {

        /* 引数なしでメッセージを取得 */
        final String result = this.getMessage(type, null, codeEmbeddingFlag);
        return result;

    }

    /**
     * メッセージを取得する<br>
     * メッセージタイプに対応するメッセージパターンを取得し、指定された引数で置換します。 コード埋め込みフラグがtrueの場合、メッセージコードをメッセージの先頭に付加します。
     *
     * @since 0.1.0
     *
     * @param type
     *                          メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     * @param args
     *                          メッセージの引数。メッセージパターン内のプレースホルダーを置換するために使用されます。 nullの場合、メッセージパターンをそのまま返します。
     * @param codeEmbeddingFlag
     *                          コード埋め込みフラグ。trueの場合、メッセージコードをメッセージの先頭に追加します。 例: "[E001] エラーメッセージ"
     *
     * @return メッセージ。メッセージパターンが空の場合は空文字列を返します。
     */
    public String getMessage(final KmgCommonMessageTypes type, final Object[] args, final boolean codeEmbeddingFlag) {

        String result = KmgString.EMPTY;

        /* メッセージの取得 */
        String message = this.messageSource.getMessage(type.getCode(), args, Locale.JAPANESE);

        /* コード埋め込みフラグの処理 */
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
     * ログメッセージを取得する<br>
     * メッセージタイプに対応するメッセージパターンを取得し、指定された引数で置換します。 このメソッドは {@link #getMessage(KmgCommonMessageTypes, Object[], boolean)} を
     * コード埋め込みフラグをtrueに設定して呼び出す便利メソッドです。
     *
     * @since 0.1.0
     *
     * @param type
     *             メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     * @param args
     *             メッセージの引数。メッセージパターン内のプレースホルダーを置換するために使用されます。 nullの場合、メッセージパターンをそのまま返します。
     *
     * @return メッセージ。メッセージコードが先頭に埋め込まれます（例：「[E001] エラーメッセージ」）。
     *
     * @see #getMessage(KmgCommonMessageTypes, Object[], boolean)
     * @see KmgCommonLogMessageTypes
     */
    public String getLogMessage(final KmgCommonLogMessageTypes type, final Object[] args) {

        /* コード埋め込みフラグをtrueに設定して、メッセージを取得 */
        final String result = this.getMessage(type, args, true);
        return result;

    }

    /**
     * ログメッセージを取得する<br>
     * メッセージタイプに対応するメッセージパターンを取得します。 このメソッドは {@link #getMessage(KmgCommonMessageTypes, boolean)} を
     * コード埋め込みフラグをtrueに設定して呼び出す便利メソッドです。
     *
     * @since 0.1.0
     *
     * @param type
     *             メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     *
     * @return メッセージ。メッセージコードが先頭に埋め込まれます（例：「[E001] エラーメッセージ」）。
     *
     * @see #getMessage(KmgCommonMessageTypes, boolean)
     * @see KmgCommonLogMessageTypes
     */
    public String getLogMessage(final KmgCommonLogMessageTypes type) {

        /* コード埋め込みフラグをtrueに設定して、メッセージを取得 */
        final String result = this.getMessage(type, true);
        return result;

    }

    /**
     * メッセージメッセージを取得する<br>
     * メッセージタイプに対応するメッセージパターンを取得し、指定された引数で置換します。 このメソッドは {@link #getMessage(KmgCommonMessageTypes, Object[], boolean)} を
     * コード埋め込みフラグをfalseに設定して呼び出す便利メソッドです。
     *
     * @since 0.1.0
     *
     * @param type
     *             メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     * @param args
     *             メッセージの引数。メッセージパターン内のプレースホルダーを置換するために使用されます。 nullの場合、メッセージパターンをそのまま返します。
     *
     * @return メッセージ。メッセージコードは埋め込まれません。
     *
     * @see #getMessage(KmgCommonMessageTypes, Object[], boolean)
     * @see KmgCommonMsgMessageTypes
     */
    public String getMsgMessage(final KmgCommonMsgMessageTypes type, final Object[] args) {

        /* コード埋め込みフラグをfalseに設定して、メッセージを取得 */
        final String result = this.getMessage(type, args, false);
        return result;

    }

    /**
     * メッセージメッセージを取得する<br>
     * メッセージタイプに対応するメッセージパターンを取得します。 このメソッドは {@link #getMessage(KmgCommonMessageTypes, boolean)} を
     * コード埋め込みフラグをfalseに設定して呼び出す便利メソッドです。
     *
     * @since 0.1.0
     *
     * @param type
     *             メッセージの種類。対応するリソースからメッセージパターンを取得するために使用されます。
     *
     * @return メッセージ。メッセージコードは埋め込まれません。
     *
     * @see #getMessage(KmgCommonMessageTypes, boolean)
     * @see KmgCommonMsgMessageTypes
     */
    public String getMsgMessage(final KmgCommonMsgMessageTypes type) {

        /* コード埋め込みフラグをfalseに設定して、メッセージを取得 */
        final String result = this.getMessage(type, false);
        return result;

    }

}
