import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Scanner;

public class Des {
    public static final String KEY = "fuckfuckfuckfuckfuckfuck";
    /**
     * 加密（使用DES算法）
     *
     * @param txt
     *            需要加密的文本
     * @param key
     *            密钥
     * @return 成功加密的文本
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String encrypt(String txt, String key)
            throws InvalidKeySpecException, InvalidKeyException,
            NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        StringBuffer sb = new StringBuffer();
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory skeyFactory = null;
        Cipher cipher = null;
        try {
            skeyFactory = SecretKeyFactory.getInstance("DES");
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKey deskey = skeyFactory.generateSecret(desKeySpec);
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] cipherText = cipher.doFinal(txt.getBytes());
        for (int n = 0; n < cipherText.length; n++) {
            String stmp = (java.lang.Integer.toHexString(cipherText[n] & 0XFF));

            if (stmp.length() == 1) {
                sb.append("0" + stmp);
            } else {
                sb.append(stmp);
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 解密（使用DES算法）
     *
     * @param txt
     *            需要解密的文本
     * @param key
     *            密钥
     * @return 成功解密的文本
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decrypt(String txt, String key)
            throws InvalidKeyException, InvalidKeySpecException,
            NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory skeyFactory = null;
        Cipher cipher = null;
        try {
            skeyFactory = SecretKeyFactory.getInstance("DES");
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKey deskey = skeyFactory.generateSecret(desKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] btxts = new byte[txt.length() / 2];
        for (int i = 0, count = txt.length(); i < count; i += 2) {
            btxts[i / 2] = (byte) Integer.parseInt(txt.substring(i, i + 2), 16);
        }
        return (new String(cipher.doFinal(btxts)));
    }

    public static void main(String[] args)throws InvalidKeyException,
            NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException{
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        Scanner s3 = new Scanner(System.in);
        Scanner s4 = new Scanner(System.in);
        System.out.println("What you want(0en,1de): ");
        while (true) {
            String line = s.nextLine();
            if (line.equals("0")){
                System.out.println("原文：");
                System.out.println("密文："+encrypt(s1.nextLine(), KEY));
                break;
            }else if(line.equals("1")){
                System.out.println("密文：");
                System.out.println("原文："+decrypt(s2.nextLine(), KEY));
                break;
            }
        }
    }
} 