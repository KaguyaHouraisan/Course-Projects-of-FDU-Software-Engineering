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
    	if (ch == '\n') {  //�ų����س����ĸ��� 
            continue;
		} else {
			if (isGameOn == 0) {  //��һ����Ϸ�Ѿ���������һ�ֻ�δ��ʼ����� 
				if (ch == 's') {  //��ʼһ���µ���Ϸ 
        			isGameOn = 1;
            		cout << "\nGame start!\n";
            		cout << "Please input 'r' to roll the first dice or 'q' to quit.\n\n";
            	} else if (ch == 'q') {  //�˳���Ϸ 
            		break;
				} else {  //�������Ƿ��ַ�ʱ�ķ��� 
					cout << "\nYour command is '" << ch << "', which is not a legitimate input!\n";
            		cout << "Please input 's' to start or 'q' to quit.\n\n";
				}
            } else if (isGameOn == 1) {  //һ����Ϸ���ڽ����е���� 
            	if (ch == 'r') {  //������ 
            		if (count == 0) {  //����һ������ 
            			Dice dice;
            			temp = dice.getPoint();
            			count = count + temp;
            			cout << "\nThe first time you roll the dice, you get " << temp << " points.\n";
            			cout << "Please input 'r' to roll the second dice or 'q' to quit.\n\n";
					} else if (count > 0 && count < 7) {  //���ڶ������� ����countֵ�ж��ǲ��ǵڶ����� 
						Dice dice;
            			temp = dice.getPoint();
            			count = count + temp;
            			cout << "\nThe second time you roll the dice, you get " << temp << " points.\n";
						if (count == 7) {  //���ʤ�� 
							cout << "Terrific! The sum of the two points is exactly 7.\n";
						} else {  //���ʧ�� 
							cout << "Sorry, the sum of the two points is " << count << " not 7.\n";
						}
						cout << "Input 's' to restart or 'q' to quit.\n\n";
						isGameOn = 0;
						count = 0;
					} else {  //countֵ�쳣ʱ�Զ��˳���Ϸ 
						break;
					}
				} else if (ch == 'q') {  //�˳���Ϸ 
					break;
				} else {  //�������Ƿ��ַ�ʱ�ķ��� 
					cout << "\nYour command is '" << ch << "', which is not a legitimate input!\n";
            		cout << "Please input 'r' to roll the dice or 'q' to quit.\n\n";
				}
			} else {  //isGameOnֵ�쳣ʱ�Զ��˳���Ϸ 
				break;
			}
    	}
    }
    cout << "\nGame Over!" << endl;
    
	return 0;
}
