package main;

import java.io.IOException;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;


class Tasks {
    public int task1(int a, int b, int[][] m) {
        int ans = 0, max = 0, c = 0;

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b - 1; j++) {
                while (j < b - 1 && m[i][j] == m[i][j + 1]) {
                    c++;
                    j++;
                }
                c++;
                if (c > max) {
                    max = c;
                    ans = i;
                }
            }
        }

        return ans;
    }

    public int task2(int a, int b, int[][] m) {
        int ans = 0;

        if(m[0][0] < m[0][1] && m[0][0] < m[1][0]){
            ans++;
        }
        if(m[a-1][0] < m[a-1][1] && m[a-1][0] < m[a-2][0]){
            ans++;
        }
        if(m[0][b-1] < m[0][b-2] && m[0][b-1] < m[a-1][b-1]){
            ans++;
        }
        if(m[a-1][b-1] < m[a-1][b-2] && m[a-1][b-1] < m[a-2][b-1]){
            ans++;
        }


        for(int i = 1; i < a-1; i++){
            if(m[i][0] < m[i][1] && m[i][0] < m[i-1][0] && m[i][0] < m[i+1][0]){
                ans++;
            }
            if(m[i][b-1] < m[i][b-2] && m[i][b-1] < m[i-1][b-1] && m[i][b-1] < m[i+1][b-1]){
                ans++;
            }
        }
        for(int i = 1; i < b-1; i++){
            if(m[0][i] < m[1][i] && m[0][i] < m[0][i-1] && m[0][i] < m[0][i+1]){
                ans++;
            }
            if(m[a-1][i] < m[a-2][i] && m[a-1][i] < m[a-1][i-1] && m[a-1][i] < m[a-1][i+1]){
                ans++;
            }
        }

        for(int i = 1; i < a-1; i++){
            for(int j = 1; j < b-1; j++){
                if(m[i][j] < m[i+1][j] && m[i][j] < m[i][j+1] &&
                        m[i][j] < m[i-1][j] && m[i][j] < m[i][j-1]){
                    ans++;
                }
            }
        }

        return ans;
    }

    int[][] task3(int a, int b, int[][] m) {
        int max = m[0][0];
        int maxI = 0;
        int maxJ = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (m[i][j] > max) {
                    max = m[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        int buff;
        for (int i = 0; i < a; i++) {
            buff = m[i][0];
            m[i][0] = m[i][maxJ];
            m[i][maxJ] = buff;
        }
        for (int i = 0; i < a; i++) {
            buff = m[0][i];
            m[0][i] = m[maxI][i];
            m[maxI][i] = buff;
        }

        return m;
    }

    double task4(int a, int b, int[][] m) {
        int buff = 1;
        double ans = 1;
        int c;
        for (int i = 0; i < a - 1; i++) {
            for (int j = i + 1; j < b; j++) {
                c = m[j][i];
                if (c != 0) {
                    buff *= m[i][i];
                    for (int k = i; k < a; k++) {
                        m[j][k] = m[j][k] * m[i][i] - m[i][k] * c;
                    }
                }
            }
        }

        for (int i = 0; i < a; i++) {
            ans *= m[i][i];
        }

        if(buff!=0) {
            ans /= buff;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            File inFile = new File("input.txt");
            Scanner in = new Scanner(inFile);
            PrintStream out = new PrintStream("output.txt");
            int task = in.nextInt();

            int a, b;
            a = in.nextInt();
            b = in.nextInt();

            int[][] matrix = new int[a][b];
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    matrix[i][j] = in.nextInt();
                }
            }

            Tasks t = new Tasks();
            if (task == 1) {
                out.printf("Your answer is: %d", t.task1(a, b, matrix));
            }
            if (task == 2) {
                out.printf("Your answer is: %d", t.task2(a, b, matrix));
            }
            if (task == 3) {
                int[][] ans = t.task3(a, b, matrix);
                for (int i = 0; i < a; i++) {
                    for (int j = 0; j < b; j++) {
                        out.printf("%d ", ans[i][j]);
                    }
                    out.println();
                }
            }
            if (task == 4) {
                out.printf("Your answer is: %f", t.task4(a, b, matrix));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}