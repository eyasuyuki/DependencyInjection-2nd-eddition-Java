package org.javaopen.di.chap1;

import org.apache.commons.lang3.StringUtils;

import java.security.Principal;

/**
 * リスト 1.3
 */
public class SecureMessageWriter implements IMessageWriter {
    private Principal principal = null;
    private IMessageWriter writer = null;

    public SecureMessageWriter(Principal principal, IMessageWriter writer) {
        if (principal == null)
            throw new IllegalStateException("principal must not be null");
        if (writer == null)
            throw new IllegalStateException("writer must not be null");
        this.principal = principal;
        this.writer = writer;
    }

    @Override
    public void write(String message) {
        if (isAuthenticated()) {
            writer.write(message);
        }
    }

    public boolean isAuthenticated() {
        String name = principal.getName();
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return switch (name) {
            case "anonymous" -> false;
            case "UNKNOWN" -> false;
            case "anonymousUser" -> false;
            default -> true;
        };
    }
}
