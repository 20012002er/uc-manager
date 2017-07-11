package com.jia16.uc.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by lazyb on 2017/7/7.
 */
public enum PasswordEncryption {

    BCRYPT {
        public String encrypt(String content) {
            return BCrypt.hashpw(content, BCrypt.gensalt(4));
        }

        public boolean check(String input, String hashedContent) {
            return BCrypt.checkpw(input, hashedContent);
        }
    };

    private PasswordEncryption() {

    }

    public abstract String encrypt(String var1);

    public abstract boolean check(String var1, String var2);

}
