import java.io.*;

public class Main {

    private static String long2ip(long longIp) {
        return ((longIp >> 24) & 0xFF) + "." + ((longIp >> 16) & 0xFF) + "." + ((longIp >> 8) & 0xFF) + "." + (longIp & 0xFF);
    }

    public static void main(String[] args) {
        String regfile = null, country = null, output = null;

        // parse arguments from command line
        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-i")) {
                i++;
                regfile = args[i];
            }
            if (args[i].equals("-c")) {
                i++;
                country = args[i];
            }

        }
        System.out.println("File: " + regfile + "\nCountry: " + country + "\n--------- CUT HERE ---------");

        // show usage
        if (args.length < 4) {
            System.out.println("Usage: location2ip -i file -c country -o iplist");
            return;
        }

        // convert array for count host => class subnet
        long convert[] = new long[33];

        convert[0] = 4294967296L;
        convert[1] = 2147483648L;
        convert[2] = 1073741824;
        convert[3] = 536870912;
        convert[4] = 268435456;
        convert[5] = 134217728;
        convert[6] = 67108864;
        convert[7] = 33554432;
        convert[8] = 16777216;
        convert[9] = 8388608;
        convert[10] = 4194304;
        convert[11] = 2097152;
        convert[12] = 1048576;
        convert[13] = 524288;
        convert[14] = 262144;
        convert[15] = 131072;
        convert[16] = 16384;
        convert[17] = 32768;
        convert[18] = 16384;
        convert[19] = 8192;
        convert[20] = 4096;
        convert[21] = 2048;
        convert[22] = 1024;
        convert[23] = 512;
        convert[24] = 256;
        convert[25] = 128;
        convert[26] = 64;
        convert[27] = 32;
        convert[28] = 16;
        convert[29] = 8;
        convert[30] = 4;
        convert[31] = 2;
        convert[32] = 1;

        // read file and parse
        try (BufferedReader nfile = new BufferedReader(new FileReader(regfile))) {
            String line = nfile.readLine();
            while (line != null) {
                if (line.contains(country)) {
                    String delims = "[,]+";
                    String[] tokens = line.split(delims);
                    String ipfrom = tokens[0].substring(1, tokens[0].length() - 1);
                    String ipto = tokens[1].substring(1, tokens[1].length() - 1);

                    long longip_from = Long.parseLong(ipfrom, 10);
                    long longip_to = Long.parseLong(ipto, 10);
                    long subnet = (longip_to - longip_from + 1);

                    for (int i = 0; i < convert.length; i++)
                        if (convert[i] == subnet)
                            System.out.println(long2ip(longip_from) + "/" + i);
                }

                line = nfile.readLine();
            }
            nfile.close();
            System.out.println("--------- CUT HERE ---------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
