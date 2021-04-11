#include <iostream>
#include <stdio.h>
#include <string>
#include <stdlib.h>
#include <time.h>
using namespace std;

class Dice {
	public:
		int getPoint(void);
		Dice();
		
	private:
		int point;
};

Dice::Dice() {
	srand((unsigned)time(NULL)); 
	point = rand() % 6 + 1;
}

int Dice::getPoint(void) {
	return point;
}

int main() {
    char ch = ' ';
    int count = 0, isGameOn = 0, temp = 0;
    
    cout << "Welcome to the dice game!\n";
    cout << "This is a very simple game. In a round, the player can roll dice twice in a row.\n";
    cout << "If the sum of the two points is 7, the player win. On the contrary, the player fail.\n";
    cout << "You can play the game easily according to the instructions.\n\n";
    cout << "Now, please input 's' to start or 'q' to quit.\n\n";
    
    while (cin.get(ch)) {
    	if (ch == '\n') {  //排除掉回车符的干扰 
            continue;
		} else {
			if (isGameOn == 0) {  //上一轮游戏已经结束，下一轮还未开始的情况 
				if (ch == 's') {  //开始一轮新的游戏 
        			isGameOn = 1;
            		cout << "\nGame start!\n";
            		cout << "Please input 'r' to roll the first dice or 'q' to quit.\n\n";
            	} else if (ch == 'q') {  //退出游戏 
            		break;
				} else {  //玩家输入非法字符时的反馈 
					cout << "\nYour command is '" << ch << "', which is not a legitimate input!\n";
            		cout << "Please input 's' to start or 'q' to quit.\n\n";
				}
            } else if (isGameOn == 1) {  //一轮游戏正在进行中的情况 
            	if (ch == 'r') {  //掷骰子 
            		if (count == 0) {  //掷第一个骰子 
            			Dice dice;
            			temp = dice.getPoint();
            			count = count + temp;
            			cout << "\nThe first time you roll the dice, you get " << temp << " points.\n";
            			cout << "Please input 'r' to roll the second dice or 'q' to quit.\n\n";
					} else if (count > 0 && count < 7) {  //掷第二个骰子 （用count值判断是不是第二个） 
						Dice dice;
            			temp = dice.getPoint();
            			count = count + temp;
            			cout << "\nThe second time you roll the dice, you get " << temp << " points.\n";
						if (count == 7) {  //玩家胜利 
							cout << "Terrific! The sum of the two points is exactly 7.\n";
						} else {  //玩家失败 
							cout << "Sorry, the sum of the two points is " << count << " not 7.\n";
						}
						cout << "Input 's' to restart or 'q' to quit.\n\n";
						isGameOn = 0;
						count = 0;
					} else {  //count值异常时自动退出游戏 
						break;
					}
				} else if (ch == 'q') {  //退出游戏 
					break;
				} else {  //玩家输入非法字符时的反馈 
					cout << "\nYour command is '" << ch << "', which is not a legitimate input!\n";
            		cout << "Please input 'r' to roll the dice or 'q' to quit.\n\n";
				}
			} else {  //isGameOn值异常时自动退出游戏 
				break;
			}
    	}
    }
    cout << "\nGame Over!" << endl;
    
	return 0;
}
