package kmg.fund.infrastructure.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import kmg.fund.domain.types.KmgFundGenMessageTypes;
import kmg.fund.infrastructure.exception.KmgFundException;

/**
 * KMG YAMLユーティリティ<br>
 *
 * @author KenichiroArai
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 */
public final class KmgYamlUtils {

    /**
     * YAML をロードし、マップ形式で返す<br>
     *
     * @param path
     *             ファイルパス
     *
     * @return マップ形式のデータ
     *
     * @throws KmgFundException
     *                          入出力処理に失敗した場合
     */
    public static Map<String, Object> load(final Path path) throws KmgFundException {

        Map<String, Object> result = null;

        final Yaml yaml = new Yaml();

        try (InputStream inputStream = Files.newInputStream(path)) {

            result = yaml.load(inputStream);

        } catch (final NoSuchFileException e) {

            final KmgFundGenMessageTypes genMsgType = KmgFundGenMessageTypes.KMGFUND_GEN24000;
            final Object[]               genMsgArgs = {
                path.toString()
            };
            throw new KmgFundException(genMsgType, genMsgArgs, e);

        } catch (final IOException e) {

            final KmgFundGenMessageTypes genMsgType = KmgFundGenMessageTypes.KMGFUND_GEN24001;
            final Object[]               genMsgArgs = {
                path.toString()
            };
            throw new KmgFundException(genMsgType, genMsgArgs, e);

        }

        return result;

    }

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    private KmgYamlUtils() {

        // 処理無し
    }
}
