package ru.mail.homework;

import android.graphics.Color;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.String.valueOf;

class DataSource {

    private static final DataSource ourInstance = new DataSource();
    private final List<NewsModel> list;

    private int[] mColors = new int[]{
            Color.BLUE,
            Color.RED
    };

    public void addData(NewsModel number){
        list.add(number);
    }

    private DataSource() {
        list = new ArrayList<>();
        int num = 0;

        for (int i = 0; i < 100; i++) {
            num += 1;
            int color = mColors[0];
            if (num % 2 == 0) {
                color = mColors[1];
            }
            list.add(new NewsModel(num, color));
        }
    }

    static DataSource getInstance() {
        return ourInstance;
    }

    public List<NewsModel> getData() {
        return list;
    }


    public static class NewsModel {

        private int mNum;
        private int mColor;

        public NewsModel(int num, int color) {
            mNum = num;
            mColor = color;
        }

        public int getNum() {
            return mNum;
        }

        public int getColor() {
            return mColor;
        }
    }
}
