/* 
 * trans.c - Matrix transpose B = A^T
 * by刘人豪 17307110112
 * Each transpose function must have a prototype of the form:
 * void trans(int M, int N, int A[N][M], int B[M][N]);
 *
 * A transpose function is evaluated by counting the number of misses
 * on a 1KB direct mapped cache with a block size of 32 bytes.
 */ 
#include <stdio.h>
#include "cachelab.h"

int is_transpose(int M, int N, int A[N][M], int B[M][N]);
void trans_1(int M, int N, int A[N][M], int B[M][N]);
void trans_2(int M, int N, int A[N][M], int B[M][N]);
void trans_3(int M, int N, int A[N][M], int B[M][N]);

/* 
 * transpose_submit - This is the solution transpose function that you
 *     will be graded on for Part B of the assignment. Do not change
 *     the description string "Transpose submission", as the driver
 *     searches for that string to identify the transpose function to
 *     be graded. 
 */
char transpose_submit_desc[] = "Transpose submission";
void transpose_submit(int M, int N, int A[N][M], int B[M][N])
{
	if (M == 32 && N == 32) {
		trans_1(M, N, A, B);
	} else if (M == 64 && N == 64) {
		trans_2(M, N, A, B);
	} else if (M == 61 && N == 67) {
		trans_3(M, N, A, B);
	}
}

/* 
 * You can define additional transpose functions below. We've defined
 * a simple one below to help you get started. 
 */ 

/* 
 * trans - A simple baseline transpose function, not optimized for the cache.
 */
char trans_desc[] = "Simple row-wise scan transpose";
void trans(int M, int N, int A[N][M], int B[M][N])
{
    int i, j, tmp;

    for (i = 0; i < N; i++) {
        for (j = 0; j < M; j++) {
            tmp = A[i][j];
            B[j][i] = tmp;
        }
    }    

}

/*
 * 8*8分块，利用临时变量减少冲突不命中次数
 */
char trans_1_desc[] = "Transpose for Part B.1";
void trans_1(int M, int N, int A[N][M], int B[M][N]) {
	int i, m, n, val1, val2, val3, val4, val5, val6, val7, val0;
	for(m = 0; m < 32; m += 8) {
		for(n = 0; n < 32; n += 8) {
			for(i = n; i < n + 8; i++) {
				val0 = A[i][m];
				val1 = A[i][m + 1];
				val2 = A[i][m + 2];
				val3 = A[i][m + 3];
				val4 = A[i][m + 4];
				val5 = A[i][m + 5];
				val6 = A[i][m + 6];
				val7 = A[i][m + 7];
				B[m][i] = val0;
				B[m + 1][i] = val1;
				B[m + 2][i] = val2;
				B[m + 3][i] = val3;
				B[m + 4][i] = val4;
				B[m + 5][i] = val5;
				B[m + 6][i] = val6;
				B[m + 7][i] = val7;
			}
		}
	}
}

/*
 * 8*8分块的同时，利用临时变量对4个4*4块针对性处理
 */
char trans_2_desc[] = "Transpose for Part B.2";
void trans_2(int M, int N, int A[N][M], int B[M][N]) {
	int i, j, m, n, val0, val1, val2, val3, val4, val5, val6, val7;
	for(m = 0; m < N; m += 8) {
		for(n = 0; n < M; n += 8) {
			for(i = 0; i < 4; i++) {
				val0 = A[m + i][n + 0];
				val1 = A[m + i][n + 1];
				val2 = A[m + i][n + 2];
				val3 = A[m + i][n + 3];
				val4 = A[m + i][n + 4];
				val5 = A[m + i][n + 5];
				val6 = A[m + i][n + 6];
				val7 = A[m + i][n + 7];
				B[n + 0][m + i] = val0;
				B[n + 1][m + i] = val1;
				B[n + 2][m + i] = val2;
				B[n + 3][m + i] = val3;
				B[n + 0][m + 4 + i] = val4;
				B[n + 1][m + 4 + i] = val5;
				B[n + 2][m + 4 + i] = val6;
				B[n + 3][m + 4 + i] = val7;
				
			}

			for(i = 0; i < 4; i++) {
				val0 = B[n + i][m + 4];
				val1 = B[n + i][m + 5];
				val2 = B[n + i][m + 6];
				val3 = B[n + i][m + 7];

				val4 = A[m + 4][n + i];
				val5 = A[m + 5][n + i];
				val6 = A[m + 6][n + i];
				val7 = A[m + 7][n + i];
				
				B[n + i][m + 4] = val4;
				B[n + i][m + 5] = val5;
				B[n + i][m + 6] = val6;
				B[n + i][m + 7] = val7;

				B[n + 4 + i][m + 0] = val0;
				B[n + 4 + i][m + 1] = val1;
				B[n + 4 + i][m + 2] = val2;
				B[n + 4 + i][m + 3] = val3;
			}

			for(i = 4; i < 8; i++) {
				for(j = 4; j < 8; j++) {
					B[n + j][m + i] = A[m + i][n + j];
				}
			}
		}
	}
}

/*
 * 16*16分块，利用临时变量优化即可
 */
char trans_3_desc[] = "Transpose for Part B.3";
void trans_3(int M, int N, int A[N][M], int B[M][N]) {
	int i, j, m, n, val0, val1, val2, val3, val4, val5, val6, val7;
	for(m = 0; m + 16 < N; m += 16) {
		for(n = 0; n + 16 < M; n += 16) {
			for(i = m; i < m + 16; i++) {
				val0 = A[i][n + 0];
				val1 = A[i][n + 1];
				val2 = A[i][n + 2];
				val3 = A[i][n + 3];
				val4 = A[i][n + 4];
				val5 = A[i][n + 5];
				val6 = A[i][n + 6];
				val7 = A[i][n + 7];
				B[n + 0][i] = val0;
				B[n + 1][i] = val1;
				B[n + 2][i] = val2;
				B[n + 3][i] = val3;
				B[n + 4][i] = val4;
				B[n + 5][i] = val5;
				B[n + 6][i] = val6;
				B[n + 7][i] = val7;

				val0 = A[i][n + 8];
				val1 = A[i][n + 9];
				val2 = A[i][n + 10];
				val3 = A[i][n + 11];
				val4 = A[i][n + 12];
				val5 = A[i][n + 13];
				val6 = A[i][n + 14];
				val7 = A[i][n + 15];
				B[n + 8][i] = val0;
				B[n + 9][i] = val1;
				B[n + 10][i] = val2;
				B[n + 11][i] = val3;
				B[n + 12][i] = val4;
				B[n + 13][i] = val5;
				B[n + 14][i] = val6;
				B[n + 15][i] = val7;

			}
		}
	}

	for(i = m; i < N; i++) {
		for(j = 0; j < M; j++) {
			B[j][i] = A[i][j];
		}
	}

	for(i = 0; i < m; i++) {
		for(j = n; j < M; j++) {
			B[j][i] = A[i][j];
		}
	}
}


/*
 * registerFunctions - This function registers your transpose
 *     functions with the driver.  At runtime, the driver will
 *     evaluate each of the registered functions and summarize their
 *     performance. This is a handy way to experiment with different
 *     transpose strategies.
 */
void registerFunctions()
{
    /* Register your solution function */
    registerTransFunction(transpose_submit, transpose_submit_desc); 

    /* Register any additional transpose functions */
    registerTransFunction(trans, trans_desc); 
    registerTransFunction(trans_1, trans_1_desc);
    registerTransFunction(trans_2, trans_2_desc);
    registerTransFunction(trans_3, trans_3_desc);
}

/* 
 * is_transpose - This helper function checks if B is the transpose of
 *     A. You can check the correctness of your transpose by calling
 *     it before returning from the transpose function.
 */
int is_transpose(int M, int N, int A[N][M], int B[M][N])
{
    int i, j;

    for (i = 0; i < N; i++) {
        for (j = 0; j < M; ++j) {
            if (A[i][j] != B[j][i]) {
                return 0;
            }
        }
    }
    return 1;
}

