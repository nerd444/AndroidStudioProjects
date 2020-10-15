package com.nerd.addmemo.util;

public class Util {
    public static final String DATABASE_NAME = "memo_db";     // 상수만드는 키워드 = public static final, 전부 대문자 = 상수
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "memos";

    // 컬럼이름 : 컬럼명은 String (문자열이기때문에)
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MEMO = "memo";
}
