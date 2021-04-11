#include <iostream>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
using namespace std;

/**
	封装后的Dice（骰子）类，在实例化时自动随机一个值 
*/
class Dice {
	public:
		int getPoint(void);
		Dice(void);
		
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

/**
	封装后的Player（玩家）类 
*/
class Player {
	public:
		char getUsername(void);
		Player(char name);
		Player(void);
		
	private:
		char username;
};

Player::Player(void) {
	username = 't';
}

Player::Player(char name) {
	username = name;
}

char Player::getUsername(void) {
	return username;
}

/**
	封装后的Game（游戏）类 
*/
class Game {
	public:
		Player getUser1(void);
		Player getUser2(void);
		int getNumOfPlayer(void); 
		void setUser1(Player user);
		void setUser2(Player user);
		void beforeGame(void);
		int chooseMode(int player);
		int roll(int player);
		int rollTheDice(int player);
		void findWinner(void);
		int continueOrNot(void);
		void reBegin(int t);
		void recount(int t);
		Game(void);
		
	private:
		Player user1;
		Player user2;
		int scoreOf1;
		int scoreOf2;
		int modeOf1;
		int modeOf2;
		int numOfPlayer; 
		int count;
		char ch;
		int result[500];
};

Game::Game(void) {
	scoreOf1 = 0;
	scoreOf2 = 0;
	modeOf1 = 0;
	modeOf2 = 0;
	numOfPlayer = 0;
	ch = ' ';
	count = 0;
}

Player Game::getUser1(void) {
	return user1;
}

Player Game::getUser2(void) {
	return user2;
}

int Game::getNumOfPlayer(void) {
	return numOfPlayer;
}

void Game::setUser1(Player user) {
	user1 = user;
	if (numOfPlayer == 0) {
		numOfPlayer++;
	}
}

void Game::setUser2(Player user) {
	user2 = user;
	if (numOfPlayer == 1) {
		numOfPlayer++;
	}
}

//打印游戏规则 
void Game::beforeGame(void) {
	cout << "Welcome to the dice game!\n";
    cout << "This is a very simple game. In a round, every player can roll dice twice in a row.\n";
    cout << "You are free to choose the scoring method: the sum of the two dice, or the product.\n";
    cout << "The player with the largest remainder after dividing the score by 6 will win.\n";
    cout << "You can play the game easily according to the instructions.\n";
    cout << "Now, the system has launched a round of games.\n";
    cout << "No players have joined the game yet. Do you want to join?\n\n";
}

//mode为1为加法计分，为2为乘法计分 
int Game::chooseMode(int player) {
	//参与游戏的玩家选择计分方式 
	cout << "\nNow player " << (player == 1 ? user1.getUsername() : user2.getUsername()) << " can choose the scoring method.\n";
    cout << "Input 's' to choose the sum of the two dice, or 'p' the product, or 'q' to quit.\n\n";
    while (cin.get(ch)) {
    	if (ch == '\n') {
            continue;
		} else if (ch == 's') {
			if (player == 1) {
				modeOf1 = 1;
			} else if (player == 2) {
				modeOf2 = 1;
			} 
			break;
		} else if (ch == 'p') {
			if (player == 1) {
				modeOf1 = 2;
			} else if (player == 2) {
				modeOf2 = 2;
			} 
			break;
		} else if (ch == 'q') {
			return -1;
		} else {
			cout << "\nYour command is '" << ch << "', which is not a legitimate input!\n";
			cout << "Please input 's' to choose the sum of the two dice, or 'p' the product, or 'q' to quit.\n\n";
		}
	}
	return 1;
}

//掷一枚骰子，并且更新game的计分结果 
int Game::roll(int player) {
	Dice dice;
	if (player == 1) {
		if (scoreOf1 == 0) {
			scoreOf1 = dice.getPoint();
		} else if (scoreOf1 >= 1 && scoreOf1 <= 6) {
			if (modeOf1 == 1) {
				scoreOf1 = scoreOf1 + dice.getPoint();
			} else if (modeOf1 == 2) {
				scoreOf1 = scoreOf1 * dice.getPoint();
			}
		}
	} else if (player == 2) {
		if (scoreOf2 == 0) {
			scoreOf2 = dice.getPoint();
		} else if (scoreOf2 >= 1 && scoreOf2 <= 6) {
			if (modeOf2 == 1) {
				scoreOf2 = scoreOf2 + dice.getPoint();
			} else if (modeOf2 == 2) {
				scoreOf2 = scoreOf2 * dice.getPoint();
			}
		}
	}
	return dice.getPoint();
}

//掷骰子 
int Game::rollTheDice(int player) {
	int temp;
	
	//参与游戏的玩家掷骰子 
	cout << "\nNow player " << (player == 1 ? user1.getUsername() : user2.getUsername()) << " can roll the dice.\n";
    cout << "Input 's' to roll the dice, or 'q' to quit.\n\n";
    while (cin.get(ch)) {
    	if (ch == '\n') {
            continue;
		} else if (ch == 's') {
			temp = roll(player);
			break;
		} else if (ch == 'q') {
			return -1;
		} else {
			cout << "\nYour command is '" << ch << "', which is not a legitimate input!\n";
			cout << "Please input 's' to roll the dice, or 'q' to quit.\n\n";
		}
	}
	cout << "\nPlayer " << (player == 1 ? user1.getUsername() : user2.getUsername()) << " rolls the dice and gets " << temp << " points.\n";
	cout << "Now player " << (player == 1 ? user1.getUsername() : user2.getUsername()) << "'s score is " << (player == 1 ? scoreOf1 : scoreOf2) << " .\n\n";
	return 1;
}

//寻找赢家 
void Game::findWinner(void) {
	cout << "The first player " << user1.getUsername() << "'s score is " << scoreOf1 << ", after dividing 6 the remainder is " << (scoreOf1 % 6) << ".\n";
	cout << "The second player " << user2.getUsername() << "'s score is " << scoreOf2 << ", after dividing 6 the remainder is " << (scoreOf2 % 6) << ".\n";
	if ((scoreOf1 % 6) > (scoreOf2 % 6)) {
		cout << "\nTerrific! Player " << user1.getUsername() << " won!!!\n";
		result[count] = 1;
		count++;
	} else if ((scoreOf1 % 6) < (scoreOf2 % 6)) {
		cout << "\nTerrific! Player " << user2.getUsername() << " won!!!\n";
		result[count] = 2;
		count++;
	} else {
		cout << "\nThe game has drawn!!!\n";
		result[count] = 0;
		count++;
	}
}

//询问第一个加入游戏的玩家是否重新开始 
int Game::continueOrNot(void) {
	cout << "Now the first player " << user1.getUsername() << " should input 's' to restart the game, or 'q' to quit.\n\n";
	while (cin.get(ch)) {
    	if (ch == '\n') {
            continue;
		} else if (ch == 's') {
			return 1;
		} else if (ch == 'q') {
			return -1;
		} else {
			cout << "\nYour command is '" << ch << "', which is not a legitimate input!\n";
			cout << "Please input 's' to roll the dice, or 'q' to quit.\n\n";
		}
	}
}

//初始化数据以便重新开始 
void Game::reBegin(int t) {
	scoreOf1 = 0;
	scoreOf2 = 0;
	modeOf1 = 0;
	modeOf2 = 0;
	ch = ' ';
}

//展示之前的胜负情况 
void Game::recount(int t) {
	int i;
	cout << "\nThe following are the results of the game: \n";
	for (i = 0; i < count; i++) {
		if (result[i] == 1) {
			cout << "Round " << (i + 1) << ": player " << user1.getUsername() << " won.\n";
		} else if (result[i] == 2) {
			cout << "Round " << (i + 1) << ": player " << user2.getUsername() << " won.\n";
		} else if (result[i] == 0) {
			cout << "Round " << (i + 1) << ": drawn. \n";
		}
	}
}

int main() {
	char ch = ' ';
	
	//系统发起了一场游戏，并提前打印游戏规则 
	Game game;
    game.beforeGame();
    
    //模拟用户登录的过程
    cout << "------------模拟用户登录的过程------------\n";
    cout << "The system detects that you have two accounts,\n";
    cout << "Input 'A' to start by accout A, or input 'B' to start by B, or 'q' to quit.\n\n";
    while (cin.get(ch)) {
    	if (ch == '\n') {
    		continue;
		} else {
			if (ch == 'A' || ch == 'B') {
				//以玩家A/B的身份登录 
    			Player player1(ch);
    			game.setUser1(player1);
    			break;
			} else if (ch == 'q') {
				printf("GAME OVER!");
				return 0;
			} else {
				cout << "\nYour command is '" << ch << "', which is not a legitimate input!\n";
            	cout << "Please input 'A' to start by accout A, or input 'B'to start by B, or 'q' to quit.\n\n";
			}
		}
	}
	
	while(1) {
	//第一个参与游戏的玩家选择计分方式 
    	if (game.chooseMode(1) == -1) {
    		break;
		}
				
		//第一个参与游戏的玩家第一次掷骰子
		if (game.rollTheDice(1) == -1) {
			break;
		}
				
		//第一个参与游戏的玩家第二次掷骰子
		if (game.rollTheDice(1) == -1) {
			break;
		}
		
		if (game.getNumOfPlayer() == 1) {
			//以玩家A/B的身份登录
			cout << "------------模拟用户登录的过程------------\n";
			if (game.getUser1().getUsername() == 'A') {
				cout << "Now another account 'B' has joined the game.\n";
				Player player2('B');
				game.setUser2(player2);
			} else if (game.getUser1().getUsername() == 'B') {
				cout << "Now another account 'A' has joined the game.\n";
				Player player2('A');
				game.setUser2(player2);
			} else {
				return -1;
			}
		}
			
		//第二个参与游戏的玩家选择计分方式 
    	if (game.chooseMode(2) == -1) {
    		break;
		}
			
		//第二个参与游戏的玩家第一次掷骰子
		if (game.rollTheDice(2) == -1) {
			break;
		}
				
		//第二个参与游戏的玩家第二次掷骰子
		if (game.rollTheDice(2) == -1) {
			break;
		}
			
		//比较二人的得分，判断胜负
		game.findWinner();
		
		//询问第一个加入的用户是否开始下一轮
		if (game.continueOrNot() == 1) {
			game.recount(1);
			game.reBegin(1);
		} else {
			game.recount(1);
			break;
		}
	}
	
	printf("GAME OVER!");
	return 0;
}
