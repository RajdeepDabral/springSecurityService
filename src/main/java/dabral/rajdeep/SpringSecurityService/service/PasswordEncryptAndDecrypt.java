package dabral.rajdeep.SpringSecurityService.service;

public interface PasswordEncryptAndDecrypt {
    public void prepareSecreteKey(String myKey);
    public String encrypt(String strToEncrypt, String secret);
    public String decrypt(String strToDecrypt, String secret);
}
