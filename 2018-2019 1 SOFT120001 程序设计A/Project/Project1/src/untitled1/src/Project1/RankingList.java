package Project1;

import java.io.*;
import java.util.Arrays;

/**
 * 进阶部分：排行榜
 */
class RankingList {
    /**
     * 读取存储在 RankingList 文件中的排行榜数据，与当前得分比较排序，
     * 将结果重新写入 RankingList 文件
     */
    static void ranking() {
        boolean flag = false;
        int i = 0,j;
        int[] rankingPoint = new int[11];
        char[][] rankingUsername = new char[11][50];
        File file = new File("RankingList.txt");
        if (file.exists()) {
            try (FileReader reader = new FileReader("RankingList.txt");
                 BufferedReader br = new BufferedReader(reader)
            ) {
                if ((br.readLine()) == null) flag = true;
                else {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String str1 = line.replaceAll("[^a-z^A-Z]", "");
                        String str2 = line.replaceAll("[^0-9]", "");
                        char[] string = str1.toCharArray();
                        rankingUsername[i] = Arrays.copyOf(string, string.length);
                        rankingPoint[i] = Integer.valueOf(str2).intValue();
                        i++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!flag) {
                for (i = 0; i < 10; i++) {
                    if (rankingPoint[i] >= Tetris.point && rankingPoint[i + 1] <= Tetris.point) break;
                }
                for (j = 10; j > i + 1; j--) {
                    rankingPoint[j] = rankingPoint[j - 1];
                    rankingUsername[j] = Arrays.copyOf(rankingUsername[j - 1], rankingUsername[j - 1].length);
                }
                rankingPoint[i + 1] = Tetris.point;
                char[] string = SaveAndRead.username.toCharArray();
                rankingUsername[i + 1] = Arrays.copyOf(string, string.length);
            }
            else {
                rankingPoint[0] = Tetris.point;
                char[] string = SaveAndRead.username.toCharArray();
                rankingUsername[i] = Arrays.copyOf(string, string.length);
            }

            try (FileWriter writer = new FileWriter(file,false);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                for (i = 0;i < 10;i ++) {
                    out.write("\r\n" + rankingPoint[i]);
                    out.write(rankingUsername[i]);
                }
                out.flush();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取存放在 RankingList 文件中的排行榜数据，按格式打印出来
     */
    static void output() {
        try {
            File file = new File("RankingList.txt");
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("当前没有排行榜数据。\n\n\n");
                MainStep.firstPrint();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileReader reader = new FileReader("RankingList.txt");
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            if ((br.readLine()) == null) {
                System.out.println("当前没有排行榜数据。\n\n\n");
                MainStep.firstPrint();
            }
            else {
                int i = 1;
                System.out.print("\n\n\n只有存档模式的得分将计入排行榜！\n");
                while ((line = br.readLine()) != null) {
                    String str1 = line.replaceAll("[^a-z^A-Z]", "");
                    String str2 = line.replaceAll("[^0-9]", "");
                    int k = Integer.valueOf(str2).intValue();
                    if (k != 0) {
                        System.out.println("第" + i + "名：" + str1 + "  分值：" + str2);
                        i++;
                    }
                    else break;
                }
                System.out.print("\n");
                MainStep.firstPrint();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
