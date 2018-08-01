package online.xiaohei.project.practice.offer;

public class VMware {
    /**
     * 1, 校验IP地址是否在CIDR范围内
     * 我们一般用CIDR来表示一段ip地址。
     * CIDR是这样一个字符串：
     * 先是一个ipv4的地址的字符串表示，
     * 加一个”/“分隔符，再加一个13（含）到27（含）之间的整数。
     * 比如“192.128.1.134/25”，表示从192.128.1.128（含）到192.128.1.255（含）的128个ip地址。
     * ”/“前的字段“192.128.1.134”表示该范围内的一个ip地址（不一定是第一个ip地址）。
     * “/“之后的整数“25”表示该地址段里的所有ip地址的二进制形式，
     * 前25个bit都是相同的，也就是都是”1100 0000   1000 0000  0000 0001 1” 。
     * 输入：给定一个ip地址和一个CIDR
     * 输出：如果ip在CIDR内，输出true。不然，输出false
     *
     * @param ip
     * @param cidr
     * @return
     */

    boolean checkIPinCIDR(String ip, String cidr) {
        if (validateCIDR(cidr) && validateIP(ip)) {
            String[] cidrStrArr = cidr.split("/");
            String[] cidrArr = cidrStrArr[0].split("[.]");
            // binary 32 bit
            int num = Integer.parseInt(cidrStrArr[1]);
            String cidrBinary = (get8bitString(cidrArr[0])
                    + get8bitString(cidrArr[1])
                    + get8bitString(cidrArr[2])
                    + get8bitString(cidrArr[3])).substring(0,num);
            String[] ipArr = ip.split("[.]");
            String ipBinary = (get8bitString(ipArr[0])
                    + get8bitString(ipArr[1])
                    + get8bitString(ipArr[2])
                    + get8bitString(ipArr[3])).substring(0,num);
            ;
            return ipBinary.equals(cidrBinary);
        }
        return false;
    }

    // 检查ip地址是否是xxx.xxx.xxx.xxx
    boolean validateIP(String ip) {
        if (!ip.matches("(\\d)+.(\\d)+.(\\d)+.(\\d)+")) {
            return false;
        }
        String[] pre = ip.split("[.]");
        for (String i : pre) {
            if (Integer.parseInt(i) < 0 || Integer.parseInt(i) >= 256) {
                return false;
            }
        }
        return true;
    }

    // 检查cidr格式是否正确
    boolean validateCIDR(String cidr) {
        if (!cidr.matches("(\\d)+.(\\d)+.(\\d)+.(\\d)+/{1}(\\d)+")) {
            return false;
        }
        String[] temp = cidr.split("/");
        String[] pre = temp[0].split("[.]");

        for (String i : pre) {
            if (Integer.parseInt(i) < 0 || Integer.parseInt(i) >= 256) {
                return false;
            }
        }
        if (Integer.parseInt(temp[1]) < 13 || Integer.parseInt(temp[1]) > 27) {
            return false;
        }
        return true;
    }

    String get8bitString(String num) {
        String s = Integer.toBinaryString(Integer.parseInt(num));
        return String.format("%08d", Integer.valueOf(s));
    }

    public static void main(String[] args) {
        System.out.println(new VMware().checkIPinCIDR("192.128.1.129", "192.128.1.134/25"));
    }
}
