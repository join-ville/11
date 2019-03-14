import java.math.BigDecimal;
public class ISBN {
    static String isdn;
    public static boolean checkISBN(String isbn) {
        isdn=isbn;
        int count = 0;
        char[] str = isbn.toCharArray();
        switch (isbn.length()) {
            case 10:
                for (int i = 0; i < 9; i++) {
                    if (str[i] < '0' || str[i] > '9') {
                        return false;
                        //throw new RuntimeException("所输入的ISBN:"+isbn + " 中第" + (i + 1) + "位出现非法字符" + str[i]);
                    }
                    count += (str[i] - '0') * (10 - i);
                }
                if (str[9] > '0' && str[9] < '9')
                    count += str[9] - '0';
                else if (str[9] == 'X')
                    count += 10;
                else
                    return false;
                //throw new RuntimeException("所输入的ISBN:"+isbn + " 第10位出现非法字符" + str[9]);
                if (count % 11 == 0)
                    return true;
                else
                    return false;
            case 13:
                if (!isbn.startsWith("978") && !isbn.startsWith("979"))
                    return false;
                //throw new RuntimeException("所输入的ISBN:"+isbn + " 前三位不符合规范");
                int mark = 1;
                for (int i = 0; i < 13; i++) {
                    if (str[i] < '0' || str[i] > '9') {
                        return false;
                        //throw new RuntimeException("所输入的ISBN:"+isbn + " 第" + (i + 1) + "位出现非法字符" + str[i]);
                    }
                    count += (str[i] - '0') * mark;
                    if (mark == 1)
                        mark = 3;
                    else
                        mark = 1;
                }
                if (count % 10 == 0)
                    return true;
                else
                    return false;
            default: {
                return false;
                //throw new RuntimeException("所输入的ISBN:" + isbn + " 位数不符合规范");
            }
        }
    }
    public String toString () {
        return isdn;
    }
}
class BookTest{
    public static void main(String[] args)throws Exception{
        Book soft=new Book("962793139X","soft",new BigDecimal("123.97"),10,200,"xiaom","xiaow");
        //soft.print();
        ISBN a=new ISBN();
        boolean b=a.checkISBN("962793139X");
        soft.setIsbn("111111111");
        System.out.println(a);
        System.out.println(soft.getIsbn());
        System.out.println(a.checkISBN(soft.getIsbn()));
        //System.out.println(a);
        //System.out.print(b);
    }
}
