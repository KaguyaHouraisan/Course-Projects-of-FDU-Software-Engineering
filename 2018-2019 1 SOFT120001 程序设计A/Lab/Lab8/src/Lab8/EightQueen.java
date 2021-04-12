package Test;

import java.util.Scanner;

public class EightQueen {
    private static int num;
    private static int count = 0;

    public static void main(String[] args) {
        System.out.print("请输入您想计算几皇后问题的解：");
        Scanner input = new Scanner(System.in);
        num = input.nextInt();
        int i,j;
        char[][] chessBoard = new char[num][num];
        for(i = 0; i < num; i++)
            for(j = 0; j < num; j++)
                chessBoard[i][j] = '-';
        output(chessBoard,0);
        System.out.println( num + " 皇后问题的解有 " + count + " 种");
    }

    private static void output(char[][] chessBoard,int row) {
        int i,j;
        if(row == num){
            System.out.println("第 "+ (++count) +" 种解为：");
            for(i = 0; i < num; i++){
                for(j = 0; j < num; j++){
                    System.out.print(chessBoard[i][j] + " ");
                }
                System.out.print("\n");
            }
            return;
        }

        char[][] temp = new char[num][num];
        for(i = 0; i < num; i++)
            for(j = 0; j < num; j++)
                temp[i][j] = chessBoard[i][j];
        for(i = 0; i < num; i++){
            for(j = 0; j < num; j++)
                temp[row][j] = '-';
            temp[row][i] = '*';
            if(check(temp,row,i)){
                output(temp,row + 1);
            }
        }
    }

    private static boolean check(char[][] chessBoard,int row,int column) {
        int n = 1;
        boolean flag = true;
        for (; row - n >= 0; n++){
            if ((chessBoard[row - n][column] == '*') || (column - n >= 0 && chessBoard[row - n][column - n] == '*') ||
                    (column + n < num && chessBoard[row - n][column + n] == '*')) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
