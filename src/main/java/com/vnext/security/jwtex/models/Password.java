package com.vnext.security.jwtex.models;


import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.mindrot.jbcrypt.BCrypt;

@EqualsAndHashCode
public class Password
{
    @NonNull
    private final String hash;

    private Password(@NonNull String _hash)
    {
        this.hash = _hash;
    }

    public static Password of(@NonNull String _rawPassword)
    {
        String hash = generateHash(_rawPassword);
        return new Password(hash);
    }

    public static Password ofHash(String _hash)
    {
        return new Password(_hash);
    }

    public boolean matchs(String _rawString)
    {
        if (_rawString == null) {
            return false;
        }

        return BCrypt.checkpw(_rawString, this.hash);
    }

    private static String generateHash(String _rawPassword)
    {
        return BCrypt.hashpw(_rawPassword, BCrypt.gensalt());
    }

    private String toHash()
    {
        return this.hash;
    }

}
