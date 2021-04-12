package Project1;

/**
 * 基础部分：控制游戏主体
 * @author 刘人豪
 * @version V1.0
 */
public class Tetris {
    static char[][] paint = new char[25][37];
    static int order,prepareCheck,nowCheck,x,y,con,point;
    static boolean quitflag = false;
    static boolean warning = false;
    static boolean getTheLowest = false;

    /**
     * 调用 Tetris.mainStep 方法
     */
    public static void main(String[] args) {
        MainStep.firstPrint();
    }

    /**
     * 游戏主体循环
     */
    static void mainStep() {
        Prepare.rePaint();                                                  //初始化二维数组为不包含任何方块的状态
        Prepare.rePrepare();                                                //初始化二维数组的准备区
        Prepare.randomPrepare();                                            //生成下一个出现的方块并赋在二维数组相应位置
        nowCheck = Prepare.howToRandom();                                   //随机生成当前下落的方块的类型
        Prepare.preview(x,y,nowCheck,'+');                              //把方块的垂直投影赋给二维数组
        Prepare.paintChange(x,y,nowCheck,'*');                          //根据方块的坐标对二维数组进行赋值
        MainStep.output(paint);                                             //打印完整游戏界面
        Prepare.preview(x,y,nowCheck,' ');                              //把方块的垂直投影清除
        for (;;) {
            Prepare.paintChange(x,y,nowCheck,' ');                      //把下落的方块清除
            MainStep.input();                                               //读取输入的指令
            if (quitflag) {
                System.out.println("\n\n\n");
                break;
            }                                                               //判断是否退出游戏
            MainStep.change(order);                                         //根据指令改变方块当前的位置
            Prepare.preview(x,y,nowCheck,'+');                          //把方块的垂直投影赋给二维数组
            Prepare.paintChange(x,y,nowCheck,'*');                      //根据方块的坐标对二维数组进行赋值
            MainStep.output(paint);                                         //打印完整游戏界面
            getTheLowest = Check.theLowestCheck(x,y,con,nowCheck,paint);    //检测方块是否触底
            if (getTheLowest) {
                Check.lineCheck(x,true);                             //检测是否存在可以消除的行
                nowCheck = prepareCheck;
                Prepare.rePrepare();
                Prepare.randomPrepare();
                getTheLowest = false;
                x = 4;
                y = 17;
                con = 0;
                Prepare.preview(x,y,nowCheck,'+');
                Prepare.paintChange(x,y,nowCheck,'*');
                MainStep.output(paint);
                Prepare.preview(x,y,nowCheck,' ');
            }
            else Prepare.preview(x,y,nowCheck,' ');
        }
        if (quitflag) {
            quitflag = false;
            MainStep.firstPrint();
        }
    }
}
