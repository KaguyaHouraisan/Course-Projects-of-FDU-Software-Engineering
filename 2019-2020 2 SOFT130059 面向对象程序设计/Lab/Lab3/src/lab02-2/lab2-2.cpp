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

class Player {
	public:
		int getScoreOfSum(void);
		void updateScoreOfSum(int point);
		int getScoreOfProduct(void);
		void updateScoreOfProduct(int point);
		int getRound(void);
		void nextRound(void);
		Player();
		
	private:
		int scoreOfSum;
		int scoreOfProduct;
		int round;
};

Player::Player() {
	scoreOfSum = 0;
	scoreOfProduct = 1;
	round = 0;
}

int Player::getScoreOfSum(void) {
	return scoreOfSum;
}

void Player::updateScoreOfSum(int point) {
	scoreOfSum = scoreOfSum + point;
}

int Player::getScoreOfProduct(void) {
	return scoreOfProduct;
}

void Player::updateScoreOfProduct(int point) {
	scoreOfProduct = scoreOfProduct * point;
}

int Player::getRound(void) {
	return round;
}

void Player::nextRound(void) {
	round = round + 1;
}

int main() {
    char ch = ' ';
    int count = 0, temp = 0, isQuit = 0;
    
    cout << "Welcome to the dice game!\n";
    cout << "This is a very simple game. In a round, every player can roll dice twice in a row.\n";
    cout << "The final score of player A is the sum of the two dice, and player B's is the product.\n";
    cout << "In player A and player B, the one with the largest remainder after dividing the score by 6 will win.\n";
    cout << "You can play the game easily according to the instructions.\n\n";
    cout << "Now, please input 's' to start or 'q' to quit.\n\n";
    
    while (cin.get(ch)) {
    	if (ch == '\n') {  //�ų����س����ĸ��� 
            continue;
		} else {
			if (ch == 's') {  //��ʼһ���µ���Ϸ 
            	cout << "\nGame start!\n";
            	cout << "Player A should input 'r' to roll the first dice or 'q' to quit.\n\n";
            	
            	Player playerA, playerB;
            	while (cin.get(ch)) {  //һ����Ϸ�е�ѭ�� 
    				if (ch == '\n') {  //�ų����س����ĸ��� 
            			continue;
					} else if (ch == 'q') {  //���ѡ���˳���Ϸ 
						isQuit = 1;
						break;
					} else if (ch == 'r') {  //��������� 
						Dice dice;
            			temp = dice.getPoint();
						if (playerA.getRound() == 0 && playerB.getRound() == 0) {  //���AͶ��һ�� 
            				playerA.updateScoreOfSum(temp);
            				playerA.nextRound();
            				cout << "\nThe first time player A roll the dice gets " << temp << " points.\n";
            				cout << "Player A should input 'r' to roll the second dice or 'q' to quit.\n\n";
						} else if (playerA.getRound() == 1 && playerB.getRound() == 0) {  //���AͶ�ڶ���
            				playerA.updateScoreOfSum(temp);
            				playerA.nextRound();
            				cout << "\nThe second time player A roll the dice gets " << temp << " points.\n";
            				cout << "Player B should input 'r' to roll the first dice or 'q' to quit.\n\n";
						} else if (playerA.getRound() == 2 && playerB.getRound() == 0) {  //���BͶ��һ��
           					playerB.updateScoreOfProduct(temp);
           					playerB.nextRound();
           					cout << "\nThe first time player B roll the dice gets " << temp << " points.\n";
           					cout << "Player B should input 'r' to roll the second dice or 'q' to quit.\n\n";
						} else if (playerA.getRound() == 2 && playerB.getRound() == 1) {  //���BͶ�ڶ���
           					playerB.updateScoreOfProduct(temp);
            				playerB.nextRound();
            				cout << "\nThe second time player B roll the dice gets " << temp << " points.\n\n";
            				cout << "Player A's score is " << playerA.getScoreOfSum() << ", after dividing 6 the remainder is " << (playerA.getScoreOfSum() % 6) << ".\n";
            				cout << "Player B's score is " << playerB.getScoreOfProduct() << ", after dividing 6 the remainder is " << (playerB.getScoreOfProduct() % 6) << ".\n";
            				if ((playerA.getScoreOfSum() % 6) > (playerB.getScoreOfProduct() % 6)) {  //���Aʤ�� 
								cout << "\nTerrific! Player A won!!!\n";
							} else if ((playerA.getScoreOfSum() % 6) < (playerB.getScoreOfProduct() % 6)) {  //���Bʤ�� 
								cout << "\nTerrific! Player B won!!!\n";
							} else {  //ƽ�� 
								cout << "\nThe game has drawn!!!\n";
							}
							cout << "\nNow input 's' to restart or 'q' to quit.\n\n";
							count = 0;
							break;  //����һ����Ϸ 
						} else {  //Ͷ���Ӵ����쳣ʱ�Զ��˳���Ϸ 
							return 0; 
						}
					} else {  //�������Ƿ��ַ�ʱ�ķ��� 
						cout << "\nYour command is '" << ch << "', which is not a legitimate input!\n";
            			cout << "Please input 'r' to roll the dice or 'q' to quit.\n\n";
					}
    			}
   				if (isQuit == 1) {  //����Ϸ������ʱѡ���˳���Ϸ 
    				isQuit = 0;
    				break;
				}
           	} else if (ch == 'q') {  //�˳���Ϸ 
           		break;
			} else {  //�������Ƿ��ַ�ʱ�ķ��� 
				cout << "\nYour command is '" << ch << "', which is not a legitimate input!\n";
            	cout << "Please input 's' to start or 'q' to quit.\n\n";
			}
    	}
    }
    
    cout << "\nGame Over!" << endl;
    
	return 0;
}
