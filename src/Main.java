import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, InvalidKeySpecException, InvalidKeyException, IOException {
//        readFile("D:\\Xdministrator\\Desktop\\a.jpg");
/*        try {
            convertToHex(System.out, new File("D:\\Xdministrator\\Desktop\\a.jpg"));
//            convertToHex(new PrintStream("D:\\Xdministrator\\Desktop\\a.jpg.hex"), new File("D:\\Xdministrator\\Desktop\\a.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }*/
        
        /*String c7 = null;
        int q = 0;
        String k = "4655434b4c5556";
        StringBuilder sbx = new StringBuilder();
        for(int i=1;i<=k.length()/2;i++){
            if(q <= k.length()-2) {
                c7 = k.substring(q, q + 2);
            }
            String[] f = new String[k.length()/2+1];
            f[i] = c7;
            byte[] w = new byte[k.length()/2+1];
            w[i] = (byte)Integer.parseInt(f[i],16);
            q=q+2;
        }*/
        /*String k = "4655434B4C5556";
        byte[] z = hexString2Bytes(k);
        System.out.println(new String(z));*/
//        System.out.println(Des.encrypt(readFile("D:\\Xdministrator\\Desktop\\a.jpg"),Des.KEY));
//        writeMethod3("D:\\Xdministrator\\Desktop\\a.jpg.desx",Des.encrypt(hexReadFile("D:\\Xdministrator\\Desktop\\a.jpg"),Des.KEY));
//        hexWrite(hexString2Bytes(Des.encrypt(hexReadFile("D:\\Xdministrator\\Desktop\\a.jpg"),Des.KEY)),"D:\\Xdministrator\\Desktop\\a.jpg.des");

//        hexWrite(hexString2Bytes(Des.decrypt(hexReadFile("D:\\Xdministrator\\Desktop\\a.jpg.des"),Des.KEY)),"D:\\Xdministrator\\Desktop\\x.jpg");
        hexWrite(hexString2Bytes(Des.decrypt(hexReadFile("D:\\Xdministrator\\Desktop\\a.jpg.des"),Des.KEY)),"D:\\Xdministrator\\Desktop\\x.jpg");
//        hexWrite(hexString2Bytes(Des.decrypt(readFile("D:\\Xdministrator\\Desktop\\a.jpg.des"),Des.KEY)),"D:\\Xdministrator\\Desktop\\x.jpg");
//        System.out.println(readFile("D:\\\\Xdministrator\\\\Desktop\\\\a.jpg"));
       /* byte[] r = new byte[]{(byte)0x46,(byte)0x55,(byte)0x43,(byte)0x4b,(byte)0x20,(byte)0x55};
        write(r);*/
//        System.out.println(String.format("%d",Integer.parseInt(f[i],16)));
//        System.out.println(hexReadFile("D:\\Xdministrator\\Desktop\\a.jpg"));
    }

    /*
     * 16进制字符串转字节数组
     */
    public static byte[] hexString2Bytes(String hex) {

        if ((hex == null) || (hex.equals(""))){
            return null;
        }
        else if (hex.length()%2 != 0){
            return null;
        }
        else{
            hex = hex.toUpperCase();
            int len = hex.length()/2;
            byte[] b = new byte[len];
            char[] hc = hex.toCharArray();
            for (int i=0; i<len; i++){
                int p=2*i;
                b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p+1]));
            }
            return b;
        }

    }

    /*
     * 字符转换为字节
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static int toDec(String s) {
        int result = 0;
        for(int i = 0; i < 8; i++) {
            int temp = s.charAt(i);
            //9的ascii为57
            if(temp > 57) {
                temp -= 55; //'A'为65对应10，故减去55
            }else {
                temp -= 48;
            }
            result += temp * getProduct(7 - i);
        }
        return result;
    }
    /**
     * 计算16的n次方　
     */
    public static int getProduct(int n) {
        int result = 1;
        for(int i = 0; i < n; i++) {
            result *= 16;
        }
        return result;
    }

    public static String readFile(String strFile){
        byte[] bytes = null;
        try{
            InputStream is = new FileInputStream(strFile);
            int iAvail = is.available();
            bytes = new byte[iAvail];
            is.read(bytes);
            //System.out.println("文件内容:\n" + new String(bytes));
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new String(bytes);
    }

    public static String hexReadFile( String file) throws IOException {
        InputStream is = new FileInputStream(new File(file));

        int bytesCounter =0;
        int value = 0;
        StringBuilder sbHex = new StringBuilder();
        StringBuilder sbText = new StringBuilder();
        StringBuilder sbResult = new StringBuilder();

        while ((value = is.read()) != -1) {
            //convert to hex value with "X" formatter
            sbHex.append(String.format("%02X", value));

            //If the chracater is not convertable, just print a dot symbol "."
            if (!Character.isISOControl(value)) {
                sbText.append((char)value);
            }else {
                sbText.append("");
            }

            //if 16 bytes are read, reset the counter,
            //clear the StringBuilder for formatting purpose only.
            if(bytesCounter==15){
                sbResult.append(sbHex);//.append("      ").append(sbText).append("\n");
                sbHex.setLength(0);
                sbText.setLength(0);
                bytesCounter=0;
            }else{
                bytesCounter++;
            }
        }
        //if still got content
        if(bytesCounter!=0){
            //add spaces more formatting purpose only
            for(; bytesCounter<16; bytesCounter++){
                //1 character 3 spaces
                sbHex.append("");
            }
            sbResult.append(sbHex);//.append("      ").append(sbText).append("\n");
        }
        is.close();
        return sbResult.toString();// + "\n\n";
    }

    public static void hexWrite(byte[] bytes,String filex){
        FileOutputStream fop = null;
        File file;
//        String content = "This is the text content";

        try {

            file = new File(filex);
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
//            byte[] contentInBytes = content.getBytes();

            fop.write(bytes);
            fop.flush();
            fop.close();

            System.out.println(new String(bytes));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 使用FileWriter类写文本文件
     */
    public static void writeMethod1(String file,String content)
    {
        String fileName=file;
        try
        {
            //使用这个构造函数时，如果存在kuka.txt文件，
            //则先把这个文件给删除掉，然后创建新的kuka.txt
            FileWriter writer=new FileWriter(fileName);
            writer.write(content);
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //注意：上面的例子由于写入的文本很少，使用FileWrite类就可以了。但如果需要写入的
    //内容很多，就应该使用更为高效的缓冲器流类BufferedWriter。
    /**
     * 使用BufferedWriter类写文本文件
     */
    public static void writeMethod3(String file,String content) {
        String fileName = file;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(content);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}