package org.javaopen.di.chap4.ui.security;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.request.cycle.RequestCycle;
import org.eclipse.jetty.util.StringUtil;
import org.javaopen.di.chap4.domain.IUserContext;
import org.javaopen.di.chap4.domain.Role;

import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

public class WebSessionUserContext implements IUserContext {
    @Override
    public boolean isInRole(Role role) {
        return AuthenticatedWebSession.get().getRoles().hasRole(role.getValue());
    }

    @Override
    public Currency getCurrency() {
        String acceptLanguage = RequestCycle.get().getRequest().getLocale().getLanguage();
        Locale locale = getLocale(acceptLanguage);
        return Currency.getInstance(locale);
    }

    /**
     * Accept-Languageから国を返す
     * Localeに定数で定義された国だけ対象にする
     * @param acceptLanguage
     * @return
     */
    private Locale getLocale(String acceptLanguage) {
        if (acceptLanguage == null || StringUtil.isEmpty(acceptLanguage) || "*".equals(acceptLanguage)) {
            return Locale.US;
        }
        for (Locale locale : Arrays.asList(Locale.JAPAN, Locale.US, Locale.UK, Locale.FRANCE, Locale.CANADA,
                Locale.GERMANY, Locale.CHINA, Locale.TAIWAN, Locale.ITALY, Locale.KOREA)) {
            if (acceptLanguage.equalsIgnoreCase(locale.toLanguageTag()) || acceptLanguage.equalsIgnoreCase(locale.getLanguage())) {
                return locale;
            }
        }
        return Locale.US;
    }

    private static LanguageWeight parseLanguageTag(String lang) {
        String[] parts = lang.split(";q=");
        String languageTag = parts[0].trim();
        double q = (parts.length > 1) ? parseWeight(parts[1].trim()) : 0.1;
        return new LanguageWeight(languageTag, q);
    }

    private static double parseWeight(String weight) {
        try {
            return Double.parseDouble(weight);
        } catch (NumberFormatException e) {
            return 0.1; // 不正なq値の場合はデフォルト0.1
        }
    }

    static class LanguageWeight {
        private final String language;
        private final double weight;
        public LanguageWeight(String language, double weight) {
            this.language = language;
            this.weight = weight;
        }

        public String getLanguage() {
            return language;
        }

        public double getWeight() {
            return weight;
        }
    }

}
