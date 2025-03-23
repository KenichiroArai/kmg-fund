package kmg.foundation.infrastructure.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring アプリケーションコンテキストヘルパー<br>
 * Springのコンテキストにアクセスするための静的ヘルパークラス
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@Component
public class SpringApplicationContextHelper implements ApplicationContextAware {

    /**
     * アプリケーションコンテキスト
     *
     * @since 0.1.0
     */
    private static ApplicationContext applicationContext;

    /**
     * アプリケーションコンテキストを設定する<br>
     * Springフレームワークによって自動的に呼び出される
     *
     * @since 0.1.0
     *
     * @param applicationContext
     *                           アプリケーションコンテキスト
     *
     * @throws BeansException
     *                        Beanの例外が発生した場合
     */
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {

        SpringApplicationContextHelper.applicationContext = applicationContext;

    }

    /**
     * 指定されたクラス型のBeanを取得する<br>
     *
     * @since 0.1.0
     *
     * @param <T>
     *                  Bean型
     * @param beanClass
     *                  Beanクラス
     *
     * @return 指定された型のBean
     */
    public static <T> T getBean(final Class<T> beanClass) {

        final T result = SpringApplicationContextHelper.applicationContext.getBean(beanClass);
        return result;

    }

    /**
     * 指定された名前のBeanを取得する<br>
     *
     * @since 0.1.0
     *
     * @param <T>
     *                 Bean型
     * @param beanName
     *                 Bean名
     *
     * @return 指定された名前のBean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(final String beanName) {

        final T result = (T) SpringApplicationContextHelper.applicationContext.getBean(beanName);
        return result;

    }

    /**
     * デフォルトコンストラクタ<br>
     *
     * @since 0.1.0
     */
    private SpringApplicationContextHelper() {

        // 処理なし
    }
}
