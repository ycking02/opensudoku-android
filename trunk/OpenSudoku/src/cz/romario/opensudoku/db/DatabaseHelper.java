/* 
 * Copyright (C) 2009 Roman Masek
 * 
 * This file is part of OpenSudoku.
 * 
 * OpenSudoku is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OpenSudoku is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with OpenSudoku.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package cz.romario.opensudoku.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import cz.romario.opensudoku.game.Cell;
import cz.romario.opensudoku.game.CellCollection;
import cz.romario.opensudoku.game.SudokuGame;

/**
 * This class helps open, create, and upgrade the database file.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String TAG = "DatabaseHelper";
	
	public static final int DATABASE_VERSION = 8;
	
    DatabaseHelper(Context context) {
        super(context, SudokuDatabase.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SudokuDatabase.SUDOKU_TABLE_NAME + " ("
                + SudokuColumns._ID + " INTEGER PRIMARY KEY,"
                + SudokuColumns.FOLDER_ID + " INTEGER,"
                + SudokuColumns.CREATED + " INTEGER,"
                + SudokuColumns.STATE + " INTEGER,"
                + SudokuColumns.TIME + " INTEGER,"
                + SudokuColumns.LAST_PLAYED + " INTEGER,"
                + SudokuColumns.DATA + " Text,"
                + SudokuColumns.PUZZLE_NOTE + " Text"
                + ");");
        
        db.execSQL("CREATE TABLE " + SudokuDatabase.FOLDER_TABLE_NAME + " ("
                + FolderColumns._ID + " INTEGER PRIMARY KEY,"
                + SudokuColumns.CREATED + " INTEGER,"
                + FolderColumns.NAME + " TEXT"
                + ");");
        
        insertFolder(db, 1, "Easy");
        insertSudoku(db, 1, 1, "Easy1", "0 5 2 0 0 6 0 0 0 1 6 0 9 0 0 0 0 4 0 4 9 8 0 3 6 2 0 4 0 0 0 0 0 8 0 0 0 8 3 2 0 1 5 9 0 0 0 1 0 0 0 0 0 2 0 9 7 3 0 5 2 4 0 2 0 0 0 0 9 0 5 6 0 0 0 1 0 0 9 7 0");
        insertSudoku(db, 1, 2, "Easy2", "0 5 2 4 0 0 1 0 0 1 0 0 0 0 2 0 3 0 0 0 0 8 1 3 0 2 5 4 0 0 0 0 7 0 1 0 6 8 3 0 0 0 5 9 7 0 7 0 5 0 0 0 0 2 8 9 0 3 6 5 0 0 0 0 1 0 7 0 0 0 0 6 0 0 6 0 0 4 9 7 0");
        insertSudoku(db, 1, 3, "Easy3", "3 0 2 0 0 0 0 8 9 0 6 8 0 5 2 7 3 4 0 0 9 0 0 0 0 0 0 4 0 0 0 0 7 0 0 0 0 8 3 2 0 1 5 9 0 0 0 0 5 0 0 0 0 2 0 0 0 0 0 0 2 0 0 2 1 4 7 8 0 3 5 0 5 3 0 0 0 0 9 0 8");
        insertSudoku(db, 1, 4, "Easy4", "4 0 2 0 0 0 0 0 7 0 0 0 0 8 0 4 2 0 0 5 0 3 0 2 0 0 6 0 9 0 0 3 0 0 5 0 5 0 3 0 6 0 7 0 8 0 7 0 0 1 0 0 6 0 9 0 0 4 0 6 0 3 0 0 1 5 0 7 0 0 0 0 2 0 0 0 0 0 8 0 9");
        insertSudoku(db, 1, 5, "Easy5", "0 6 0 0 9 1 0 8 0 1 0 9 6 8 0 4 0 5 0 5 0 0 4 0 1 0 6 6 0 0 0 0 0 2 0 0 0 2 3 9 0 4 7 1 0 0 0 4 0 0 0 0 0 3 9 0 7 0 2 0 0 3 0 3 0 5 0 7 9 6 0 2 0 4 0 1 5 0 0 7 0");
        insertSudoku(db, 1, 6, "Easy6", "0 6 0 0 9 0 3 8 0 0 0 9 0 8 0 4 0 5 0 5 0 3 0 0 1 0 6 0 0 1 0 0 8 2 0 0 0 2 0 9 0 4 0 1 0 0 0 4 2 0 0 9 0 0 9 0 7 0 0 6 0 3 0 3 0 5 0 7 0 6 0 0 0 4 6 0 5 0 0 7 0");
        insertSudoku(db, 1, 7, "Easy7", "4 0 2 0 0 0 3 8 0 1 0 9 6 0 7 4 0 0 0 0 8 3 0 0 1 0 6 0 9 0 0 3 0 0 0 4 0 2 3 9 6 4 7 1 0 8 0 0 0 1 0 0 6 0 9 0 7 0 0 6 5 0 0 0 0 5 8 0 9 6 0 2 0 4 6 0 0 0 8 0 9");
        insertSudoku(db, 1, 8, "Easy8", "4 0 0 0 9 1 0 0 0 0 0 9 0 0 7 4 2 5 0 5 8 3 4 0 1 9 0 6 9 1 0 0 0 0 0 0 0 0 3 9 6 4 7 0 0 0 0 0 0 0 0 9 6 3 0 8 7 0 2 6 5 3 0 3 1 5 8 0 0 6 0 0 0 0 0 1 5 0 0 0 9");
        insertSudoku(db, 1, 9, "Easy9", "3 8 0 0 0 1 0 0 4 0 0 2 6 0 0 0 7 0 0 0 0 4 8 7 0 0 3 0 0 0 0 4 0 2 3 9 2 0 1 0 0 0 4 0 6 4 9 5 0 6 0 0 0 0 6 0 0 8 5 4 0 0 0 0 7 0 0 0 6 8 0 0 8 0 0 7 0 0 0 9 2");
        insertSudoku(db, 1, 10, "Easy10", "0 0 7 5 2 0 0 6 0 0 0 2 0 0 9 0 0 8 0 0 6 4 0 7 0 0 0 7 6 8 0 0 5 0 0 9 0 3 1 0 0 0 4 5 0 4 0 0 3 0 0 7 8 1 0 0 0 8 0 4 3 0 0 1 0 0 2 0 0 8 0 0 0 5 0 0 1 3 6 0 0");
        insertSudoku(db, 1, 11, "Easy11", "3 8 0 0 0 0 0 0 0 5 4 0 0 0 9 0 7 8 0 0 0 4 0 7 5 0 3 0 0 0 1 4 5 2 0 9 0 0 0 9 0 8 0 0 0 4 0 5 3 6 2 0 0 0 6 0 9 8 0 4 0 0 0 1 7 0 2 0 0 0 4 5 0 0 0 0 0 0 0 9 2");
        insertSudoku(db, 1, 12, "Easy12", "0 0 7 0 0 1 0 0 0 5 4 0 6 0 9 0 7 8 9 0 0 4 8 7 0 0 0 7 6 0 1 0 0 2 3 0 2 3 0 0 0 0 0 5 6 0 9 5 0 0 2 0 8 1 0 0 0 8 5 4 0 0 7 1 7 0 2 0 6 0 4 5 0 0 0 7 0 0 6 0 0");
        insertSudoku(db, 1, 13, "Easy13", "0 0 7 0 2 1 9 0 0 5 0 2 0 0 9 0 7 8 0 0 6 4 0 7 5 0 0 0 0 0 1 4 0 0 3 9 0 3 1 9 0 8 4 5 0 4 9 0 0 6 2 0 0 0 0 0 9 8 0 4 3 0 0 1 7 0 2 0 0 8 0 5 0 0 4 7 1 0 6 0 0");
        insertSudoku(db, 1, 14, "Easy14", "0 8 6 5 0 0 2 0 4 4 0 7 0 0 8 0 9 0 3 5 0 0 0 9 0 0 0 0 0 9 0 8 0 6 0 1 0 1 0 0 0 0 0 8 0 6 0 8 0 9 0 3 0 0 0 0 0 2 0 0 0 7 6 0 3 0 8 0 0 4 0 9 1 0 5 0 0 4 8 2 0");
        insertSudoku(db, 1, 15, "Easy15", "0 8 6 5 0 7 0 0 0 0 0 7 3 6 0 1 0 0 0 0 0 0 0 0 0 6 8 2 4 9 0 0 3 0 5 0 5 0 0 0 0 0 0 0 7 0 7 0 1 0 0 3 4 2 8 9 0 0 0 0 0 0 0 0 0 2 0 5 6 4 0 0 0 0 0 9 0 4 8 2 0");
        insertSudoku(db, 1, 16, "Easy16", "0 0 0 0 0 7 2 3 0 4 2 0 3 6 8 0 0 0 0 5 0 0 2 9 7 6 8 0 0 0 0 8 0 6 5 0 0 0 0 6 0 2 0 0 0 0 7 8 0 9 0 0 0 0 8 9 4 2 3 0 0 7 0 0 0 0 8 5 6 0 1 9 0 6 5 9 0 0 0 0 0");
        insertSudoku(db, 1, 17, "Easy17", "9 0 6 0 0 0 2 0 0 4 0 0 3 6 8 1 9 0 3 5 0 4 0 0 0 0 0 2 0 9 0 8 0 0 5 1 0 1 3 0 4 0 9 8 0 6 7 0 0 9 0 3 0 2 0 0 0 0 0 1 0 7 6 0 3 2 8 5 6 0 0 9 0 0 5 0 0 0 8 0 3");
        insertSudoku(db, 1, 18, "Easy18", "0 9 5 0 0 2 0 0 0 7 0 0 8 0 4 0 0 1 8 1 0 0 7 6 5 0 0 4 7 6 0 0 0 3 0 2 0 0 0 0 0 0 0 0 0 3 0 1 0 0 0 8 5 7 0 0 3 2 9 0 0 7 5 5 0 0 3 0 7 0 0 6 0 0 0 4 0 0 1 3 0");
        insertSudoku(db, 1, 19, "Easy19", "0 0 5 0 0 2 7 4 0 0 0 2 8 5 0 9 0 1 8 1 0 0 0 0 5 0 0 0 7 0 5 0 1 3 0 2 0 0 8 7 2 3 6 0 0 3 0 1 6 0 9 0 5 0 0 0 3 0 0 0 0 7 5 5 0 9 0 1 7 2 0 0 0 8 7 4 0 0 1 0 0");
        insertSudoku(db, 1, 20, "Easy20", "6 0 5 1 0 2 7 4 0 7 3 2 0 0 4 0 0 1 0 0 0 0 0 0 0 2 0 4 0 0 5 0 1 3 0 0 0 0 8 0 2 0 6 0 0 0 0 1 6 0 9 0 0 7 0 6 0 0 0 0 0 0 0 5 0 0 3 0 0 2 8 6 0 8 7 4 0 5 1 0 9");
        insertSudoku(db, 1, 21, "Easy21", "6 9 5 1 0 2 0 4 0 7 0 0 8 0 0 0 0 0 0 0 0 9 7 0 0 2 3 0 7 6 0 0 0 0 9 0 9 0 0 0 2 0 0 0 4 0 2 0 0 0 0 8 5 0 1 6 0 0 9 8 0 0 0 0 0 0 0 0 7 0 0 6 0 8 0 4 0 5 1 3 9");
        insertSudoku(db, 1, 22, "Easy22", "0 9 0 0 0 2 7 4 8 0 0 0 0 0 4 9 0 1 8 0 0 9 0 6 5 0 0 4 7 0 5 0 0 0 9 0 0 0 8 0 0 0 6 0 0 0 2 0 0 0 9 0 5 7 0 0 3 2 0 8 0 0 5 5 0 9 3 0 0 0 0 0 2 8 7 4 0 0 0 3 0");
        insertSudoku(db, 1, 23, "Easy23", "0 0 1 0 0 9 0 4 8 0 8 9 0 7 0 0 3 0 0 0 3 1 0 6 0 0 5 3 9 0 0 0 0 5 0 0 0 5 8 6 0 2 1 7 0 0 0 7 0 0 0 0 9 4 9 0 0 7 0 8 3 0 0 0 3 0 0 4 0 8 6 0 8 7 0 3 0 0 4 0 0");
        insertSudoku(db, 1, 24, "Easy24", "6 0 0 0 3 9 7 0 8 0 0 0 0 0 4 6 0 0 0 0 0 1 0 0 0 2 5 0 0 2 0 1 7 5 0 6 4 0 8 0 0 0 1 0 3 1 0 7 8 5 0 2 0 0 9 1 0 0 0 8 0 0 0 0 0 5 9 0 0 0 0 0 8 0 6 3 2 0 0 0 9");
        insertSudoku(db, 1, 25, "Easy25", "6 2 0 5 0 0 7 0 0 5 0 0 2 7 0 6 3 1 0 4 0 1 0 0 0 0 5 3 0 2 0 0 0 0 8 6 0 0 0 0 9 0 0 0 0 1 6 0 0 0 0 2 0 4 9 0 0 0 0 8 0 5 0 2 3 5 0 4 1 0 0 7 0 0 6 0 0 5 0 1 9");
        insertSudoku(db, 1, 26, "Easy26", "0 8 0 1 3 0 0 0 2 1 4 0 9 0 2 0 0 7 2 7 3 0 8 0 0 0 0 0 0 0 0 7 0 2 0 6 0 0 7 2 0 3 9 0 0 5 0 2 0 4 0 0 0 0 0 0 0 0 6 0 3 1 8 6 0 0 3 0 8 0 2 4 4 0 0 0 2 1 0 5 0");
        insertSudoku(db, 1, 27, "Easy27", "9 8 0 1 0 0 4 0 2 0 4 6 9 5 0 0 0 0 2 0 0 6 8 4 0 0 1 0 1 0 0 0 9 0 8 6 0 0 7 0 0 0 9 0 0 5 9 0 8 0 0 0 7 0 7 0 0 4 6 5 0 0 8 0 0 0 0 9 8 7 2 0 4 0 8 0 0 1 0 5 9");
        insertSudoku(db, 1, 28, "Easy28", "0 8 5 1 0 0 4 0 0 0 0 0 9 5 0 0 0 7 0 7 3 6 8 4 0 0 1 0 1 0 0 7 0 0 8 0 0 6 7 2 0 3 9 4 0 0 9 0 0 4 0 0 7 0 7 0 0 4 6 5 3 1 0 6 0 0 0 9 8 0 0 0 0 0 8 0 0 1 6 5 0");
        insertSudoku(db, 1, 29, "Easy29", "0 8 5 1 0 0 4 6 0 1 4 6 0 0 0 8 0 7 0 7 0 0 0 4 0 0 1 3 0 0 0 0 9 0 8 0 0 6 7 0 0 0 9 4 0 0 9 0 8 0 0 0 0 3 7 0 0 4 0 0 0 1 0 6 0 1 0 0 0 7 2 4 0 3 8 0 0 1 6 5 0");
        insertSudoku(db, 1, 30, "Easy30", "0 8 5 1 3 0 4 6 2 0 0 6 0 0 0 0 0 7 2 7 0 6 8 0 0 9 0 0 0 0 0 0 9 2 0 0 0 6 0 2 1 3 0 4 0 0 0 2 8 0 0 0 0 0 0 2 0 0 6 5 0 1 8 6 0 0 0 0 0 7 0 0 4 3 8 0 2 1 6 5 0");
        insertFolder(db, 2, "Medium");
        insertSudoku(db, 2, 31, "Medium1", "9 1 6 0 0 4 0 7 2 8 0 0 6 2 0 0 5 0 5 0 0 0 0 8 9 3 0 0 6 0 0 0 0 2 0 0 0 0 0 2 0 7 0 0 0 0 0 5 0 0 0 0 9 0 0 9 7 8 0 0 0 0 3 0 8 0 0 7 6 0 0 9 4 5 0 1 0 0 6 8 7");
        insertSudoku(db, 2, 32, "Medium2", "0 0 0 9 0 0 0 8 2 0 6 3 0 0 1 4 0 9 9 0 8 0 0 0 0 0 0 0 0 0 6 7 0 3 0 0 0 4 6 0 5 0 2 9 0 0 0 7 0 2 3 0 0 0 0 0 0 0 0 0 7 0 1 7 0 4 3 0 0 6 2 0 6 3 0 0 0 7 0 0 0");
        insertSudoku(db, 2, 33, "Medium3", "0 3 5 6 7 0 0 0 0 4 0 0 8 2 9 5 0 0 0 8 0 0 0 3 0 6 0 0 2 0 0 0 5 8 0 7 8 0 0 2 0 6 0 0 5 3 0 1 7 0 0 0 2 0 0 4 0 9 0 0 0 7 0 0 0 2 4 8 7 0 0 6 0 0 0 0 5 2 4 9 0");
        insertSudoku(db, 2, 34, "Medium4", "0 3 0 0 7 0 9 0 2 4 7 0 0 0 9 0 0 0 0 0 9 0 0 3 0 6 0 0 2 4 0 0 0 8 3 7 0 0 7 0 0 0 1 0 0 3 5 1 0 0 0 6 2 0 0 4 0 9 0 0 2 0 0 0 0 0 4 0 0 0 5 6 7 0 8 0 5 0 0 9 0");
        insertSudoku(db, 2, 35, "Medium5", "0 8 4 2 0 0 0 0 0 9 3 0 8 4 0 0 0 0 0 5 7 0 0 0 0 0 0 6 0 0 4 0 1 7 0 0 4 0 0 0 7 0 0 0 2 0 0 5 6 0 2 0 0 9 0 0 0 0 0 0 9 8 0 0 0 0 0 2 8 0 4 7 0 0 0 0 0 3 2 1 0");
        insertSudoku(db, 2, 36, "Medium6", "0 0 7 8 6 1 0 0 0 0 0 8 0 0 3 0 0 0 5 6 0 0 9 0 0 1 0 1 0 0 0 7 0 0 8 5 0 0 0 3 4 5 0 0 0 6 3 0 0 1 0 0 0 7 0 5 0 0 2 0 0 9 8 0 0 0 6 0 0 5 0 0 0 0 0 5 3 7 1 0 0");
        insertSudoku(db, 2, 37, "Medium7", "0 4 0 0 0 1 0 0 3 0 0 0 0 5 0 0 7 9 5 6 0 0 0 2 8 0 4 1 0 0 2 7 0 0 8 0 0 8 2 0 0 0 9 6 0 0 3 0 0 1 8 0 0 7 3 0 6 1 0 0 0 9 8 4 7 0 0 8 0 0 0 0 8 0 0 5 0 0 0 4 0");
        insertSudoku(db, 2, 38, "Medium8", "0 0 0 5 0 0 0 0 6 0 0 0 8 7 0 3 0 2 2 7 0 3 0 0 0 8 1 0 0 0 0 3 4 9 0 0 7 9 3 0 5 0 6 1 4 0 0 8 7 9 0 0 0 0 9 2 0 0 0 3 0 5 7 5 0 6 0 8 7 0 0 0 3 0 0 0 0 5 0 0 0");
        insertSudoku(db, 2, 39, "Medium9", "0 0 0 9 0 0 0 6 7 0 9 0 0 0 0 2 0 8 4 6 0 0 7 8 0 0 0 3 2 0 0 9 4 0 7 0 7 0 0 6 0 3 0 0 2 0 1 0 7 8 0 0 4 3 0 0 0 8 5 0 0 1 6 5 0 1 0 0 0 0 9 0 6 7 0 0 0 9 0 0 0");
        insertSudoku(db, 2, 40, "Medium10", "0 2 4 0 0 0 0 1 7 0 0 0 3 0 1 0 0 0 3 0 0 0 0 0 9 6 5 2 0 1 0 0 0 6 5 0 0 0 0 6 3 7 0 0 0 0 9 3 0 0 0 7 0 8 5 3 9 0 0 0 0 0 1 0 0 0 5 0 2 0 0 0 8 4 0 0 0 0 5 7 0");
        insertSudoku(db, 2, 41, "Medium11", "2 0 0 0 0 6 1 4 3 0 0 4 0 0 0 6 0 0 6 0 7 0 0 8 0 2 9 1 0 0 8 0 0 2 0 0 0 0 3 0 9 0 8 0 0 0 0 5 0 0 3 0 0 1 8 3 0 5 0 0 9 0 2 0 0 6 0 0 0 4 0 0 9 4 2 6 0 0 0 0 5");
        insertSudoku(db, 2, 42, "Medium12", "5 0 4 0 0 2 0 3 0 9 0 0 0 7 3 0 0 8 6 7 0 0 0 0 0 2 0 0 0 0 0 3 0 7 8 0 0 0 5 7 0 9 2 0 0 0 4 7 0 6 0 0 0 0 0 5 0 0 0 0 0 1 4 1 0 0 4 5 0 0 0 9 0 6 0 3 0 0 5 0 2");
        insertSudoku(db, 2, 43, "Medium13", "5 8 0 0 0 0 6 3 7 0 0 0 0 0 0 0 0 0 6 0 3 5 4 0 0 0 0 0 9 0 1 0 4 7 0 5 0 1 0 7 0 9 0 4 0 8 0 7 2 0 5 0 9 0 0 0 0 0 2 6 3 0 4 0 0 0 0 0 0 0 0 0 4 6 8 0 0 0 0 7 2");
        insertSudoku(db, 2, 44, "Medium14", "0 0 0 0 1 0 0 0 0 9 0 0 0 0 3 4 0 8 6 7 0 5 0 0 0 2 1 0 0 0 1 3 0 7 8 0 0 1 5 0 0 0 2 4 0 0 4 7 0 6 5 0 0 0 7 5 0 0 0 6 0 1 4 1 0 2 4 0 0 0 0 9 0 0 0 0 9 0 0 0 0");
        insertSudoku(db, 2, 45, "Medium15", "7 8 0 3 0 0 0 5 0 9 5 6 0 0 0 0 0 0 0 0 2 0 6 5 0 0 1 0 0 3 4 0 0 5 7 0 6 0 0 0 0 0 0 0 3 0 2 5 0 0 8 1 0 0 2 0 0 5 9 0 8 0 0 0 0 0 0 0 0 4 1 7 0 3 0 0 0 4 0 2 5");
        insertSudoku(db, 2, 46, "Medium16", "2 0 0 3 6 7 5 0 0 5 0 0 8 0 0 0 6 0 3 0 0 4 5 0 7 0 0 0 9 0 5 3 0 4 0 0 0 8 0 0 0 0 0 7 0 0 0 3 0 7 4 0 5 0 0 0 1 0 2 6 0 0 5 0 3 0 0 0 5 0 0 7 0 0 2 7 8 3 0 0 1");
        insertSudoku(db, 2, 47, "Medium17", "8 0 1 0 5 6 2 0 0 0 0 0 0 0 2 3 8 1 9 0 0 0 0 3 0 0 0 3 5 0 4 7 0 0 0 0 0 0 8 0 0 0 1 0 0 0 0 0 0 6 8 0 3 7 0 0 0 6 0 0 0 0 2 6 8 7 1 0 0 0 0 0 0 0 4 5 3 0 8 0 6");
        insertSudoku(db, 2, 48, "Medium18", "3 0 0 0 0 4 0 0 5 8 4 1 7 5 3 0 6 0 0 0 0 0 1 0 0 0 0 0 0 3 0 0 0 0 8 7 0 9 8 1 0 7 5 4 0 7 5 0 0 0 0 1 0 0 0 0 0 0 7 0 0 0 0 0 3 0 2 8 1 7 9 6 2 0 0 3 0 0 0 0 8");
        insertSudoku(db, 2, 49, "Medium19", "0 0 0 0 6 4 8 1 0 0 4 0 0 5 0 0 6 2 0 0 9 0 1 0 3 0 0 0 0 3 0 4 0 6 0 7 0 0 8 1 0 7 5 0 0 7 0 4 0 3 0 1 0 0 0 0 6 0 7 0 2 0 0 4 3 0 0 8 0 0 9 0 0 1 7 3 9 0 0 0 0");
        insertSudoku(db, 2, 50, "Medium20", "0 0 0 0 4 0 3 2 0 0 0 0 3 5 7 0 8 0 0 0 0 6 0 0 4 0 0 3 5 7 0 0 6 0 4 0 6 0 0 7 0 5 0 0 3 0 8 0 9 0 0 6 7 5 0 0 8 0 0 9 0 0 0 0 9 0 5 8 1 0 0 0 0 6 4 0 7 0 0 0 0");
        insertSudoku(db, 2, 51, "Medium21", "9 0 5 0 4 0 0 2 6 0 2 6 0 5 0 9 0 0 0 3 0 6 0 0 0 5 0 3 5 0 0 0 0 0 0 9 0 0 9 0 2 0 8 0 0 1 0 0 0 0 0 0 7 5 0 1 0 0 0 9 0 3 0 0 0 3 0 8 0 7 6 0 5 6 0 0 7 0 1 0 8");
        insertSudoku(db, 2, 52, "Medium22", "0 1 0 4 0 3 0 6 0 0 3 0 0 1 7 4 0 0 2 0 0 0 0 0 3 0 0 0 7 0 0 8 0 0 0 4 0 9 2 3 5 4 7 8 0 5 0 0 0 7 0 0 3 0 0 0 3 0 0 0 0 0 5 0 0 8 5 3 0 0 4 0 0 5 0 9 0 6 0 2 0");
        insertSudoku(db, 2, 53, "Medium23", "6 0 5 9 0 0 1 0 0 0 0 0 1 0 0 0 7 3 0 7 1 3 0 0 0 0 5 0 0 9 0 1 0 0 0 4 0 4 6 2 9 3 5 1 0 7 0 0 0 4 0 6 0 0 2 0 0 0 0 1 7 3 0 1 6 0 0 0 2 0 0 0 0 0 8 0 0 9 4 0 1");
        insertSudoku(db, 2, 54, "Medium24", "0 4 9 0 6 0 0 0 2 8 0 0 2 1 0 4 9 0 1 0 0 0 4 0 0 0 0 0 0 0 0 3 5 0 8 4 0 0 8 1 0 2 3 0 0 6 3 0 4 7 0 0 0 0 0 0 0 0 8 0 0 0 1 0 8 4 0 5 1 0 0 6 7 0 0 0 2 0 9 5 0");
        insertSudoku(db, 2, 55, "Medium25", "0 6 7 0 2 0 3 0 0 0 0 3 7 0 0 0 0 0 9 2 0 1 0 3 0 0 0 4 0 2 0 3 5 0 6 0 3 0 0 0 0 0 0 0 2 0 1 0 2 4 0 9 0 3 0 0 0 5 0 8 0 3 9 0 0 0 0 0 9 2 0 0 0 0 8 0 1 0 7 5 0");
        insertSudoku(db, 2, 56, "Medium26", "0 5 0 8 4 2 0 0 1 0 0 4 0 0 0 9 0 0 8 0 0 0 5 0 0 4 0 6 0 0 4 0 0 0 1 9 0 0 7 5 0 6 8 0 0 4 3 0 0 0 9 0 0 2 0 8 0 0 9 0 0 0 6 0 0 1 0 0 0 4 0 0 5 0 0 6 8 1 0 9 0");
        insertSudoku(db, 2, 57, "Medium27", "0 0 0 0 7 6 1 8 9 0 0 0 0 0 2 0 3 0 0 0 9 8 1 3 0 0 0 0 2 5 0 0 0 0 1 0 0 8 3 0 0 0 5 9 0 0 7 0 0 0 0 4 6 0 0 0 0 3 6 5 2 0 0 0 1 0 7 0 0 0 0 0 5 3 6 1 2 0 0 0 0");
        insertSudoku(db, 2, 58, "Medium28", "0 8 0 0 0 0 0 3 0 4 0 0 3 6 8 0 0 0 3 5 0 4 0 9 7 0 0 0 0 0 0 0 3 6 5 0 0 0 3 0 0 0 9 0 0 0 7 8 1 0 0 0 0 0 0 0 4 2 0 1 0 7 6 0 0 0 8 5 6 0 0 9 0 6 0 0 0 0 0 2 0");
        insertSudoku(db, 2, 59, "Medium29", "0 0 0 5 0 0 7 4 8 5 8 9 0 0 0 0 0 1 7 0 0 0 8 6 9 0 0 3 0 2 0 1 0 5 8 0 0 0 0 0 0 0 0 0 0 0 6 7 0 5 0 2 0 4 0 0 4 7 6 0 0 0 2 2 0 0 0 0 0 8 6 7 8 7 6 0 0 5 0 0 0");
        insertSudoku(db, 2, 60, "Medium30", "0 2 1 0 0 9 0 0 8 0 0 0 0 0 4 0 3 1 7 4 0 1 0 0 0 2 5 0 0 0 0 0 7 0 8 6 0 5 8 0 0 0 1 7 0 1 6 0 8 0 0 0 0 0 9 1 0 0 0 8 0 5 2 2 3 0 9 0 0 0 0 0 8 0 0 3 0 0 4 1 0");
        insertFolder(db, 3, "Hard");
        insertSudoku(db, 3, 61, "Hard1", "6 0 0 3 0 0 1 0 0 0 7 1 6 2 0 0 0 0 8 0 5 0 0 1 0 0 0 5 0 0 8 7 0 9 0 1 0 0 9 0 0 0 6 0 0 4 0 7 0 6 9 0 0 8 0 0 0 2 0 0 8 0 7 0 0 0 0 8 6 4 1 0 0 0 8 0 0 3 0 0 2");
        insertSudoku(db, 3, 62, "Hard2", "9 0 6 0 1 3 0 0 8 0 5 8 0 0 0 0 9 0 0 3 0 0 0 0 0 1 0 0 6 0 8 0 0 9 2 0 0 0 3 4 0 9 1 0 0 0 4 9 0 0 6 0 3 0 0 9 0 0 0 0 0 8 0 0 1 0 0 0 0 6 7 0 4 0 0 9 6 0 3 0 1");
        insertSudoku(db, 3, 63, "Hard3", "3 0 0 0 6 0 2 5 0 0 0 0 5 0 0 1 0 3 0 0 5 2 1 0 4 8 6 0 0 0 3 8 0 5 0 0 0 3 0 0 0 0 0 4 0 0 0 2 0 4 5 0 0 0 4 1 3 0 5 2 7 0 0 8 0 7 0 0 4 0 0 0 0 5 6 0 7 0 0 0 4");
        insertSudoku(db, 3, 64, "Hard4", "0 6 0 0 0 1 9 0 7 1 0 0 0 0 7 2 3 0 0 8 0 0 0 0 4 0 6 0 1 8 0 0 2 0 0 4 0 7 0 0 4 0 0 9 0 9 0 0 1 0 0 7 8 0 6 0 7 0 0 0 0 4 0 0 5 1 6 0 0 0 0 9 8 0 9 3 0 0 0 2 0");
        insertSudoku(db, 3, 65, "Hard5", "6 0 0 3 0 0 2 0 8 4 0 0 1 8 5 0 0 0 0 0 0 0 0 0 4 5 0 0 0 0 0 7 0 8 3 5 0 3 0 5 0 8 0 2 0 9 5 8 0 1 0 0 0 0 0 6 9 0 0 0 0 0 0 0 0 0 6 3 1 0 0 2 3 0 4 0 0 9 0 0 6");
        insertSudoku(db, 3, 66, "Hard6", "4 0 0 0 3 0 0 9 0 2 0 0 0 0 1 6 0 0 7 6 0 8 0 0 0 0 1 5 0 0 3 1 8 0 0 0 0 3 2 0 0 0 5 1 0 0 0 0 5 9 2 0 0 8 9 0 0 0 0 3 0 4 5 0 0 1 7 0 0 0 0 6 0 4 0 0 2 0 0 0 3");
        insertSudoku(db, 3, 67, "Hard7", "0 0 4 0 9 0 1 7 0 9 0 0 0 7 0 0 0 2 0 0 7 2 0 4 0 0 0 0 4 3 0 0 0 0 5 0 7 9 8 4 0 6 2 1 3 0 6 0 0 0 0 8 9 0 0 0 0 7 0 9 4 0 0 6 0 0 0 4 0 0 0 1 0 8 5 0 3 0 7 0 0");
        insertSudoku(db, 3, 68, "Hard8", "6 8 0 0 0 1 0 0 3 0 0 7 0 0 4 0 0 0 0 0 0 8 2 0 0 0 0 8 7 0 0 0 9 2 0 4 0 4 0 3 0 2 0 8 0 2 0 3 4 0 0 0 9 6 0 0 0 0 3 6 0 0 0 0 0 0 5 0 0 4 0 0 7 0 0 2 0 0 0 6 5");
        insertSudoku(db, 3, 69, "Hard9", "0 0 0 0 0 2 0 0 0 1 0 3 4 0 0 0 0 5 2 0 0 0 5 0 4 0 1 3 4 0 0 0 5 0 9 0 8 0 7 0 0 0 3 0 4 0 9 0 3 0 0 0 1 7 6 0 5 0 3 0 0 0 9 4 0 0 0 0 8 7 0 2 0 0 0 1 0 0 0 0 0");
        insertSudoku(db, 3, 70, "Hard10", "0 5 0 7 0 2 0 0 3 0 7 3 4 8 0 0 0 5 0 0 0 0 5 0 4 0 0 0 4 0 0 0 0 2 0 0 0 2 7 0 9 0 3 5 0 0 0 6 0 0 0 0 1 0 0 0 5 0 3 0 0 0 0 4 0 0 0 6 8 7 3 0 7 0 0 1 0 9 0 6 0");
        insertSudoku(db, 3, 71, "Hard11", "5 0 0 0 8 0 0 2 0 0 0 7 5 0 2 8 0 1 0 0 2 9 0 0 0 4 0 0 2 4 0 0 0 3 0 8 0 0 0 3 2 4 0 0 0 3 0 6 0 0 0 4 7 0 0 9 0 0 0 6 7 0 0 7 0 3 2 0 8 9 0 0 0 6 0 0 9 0 0 0 5");
        insertSudoku(db, 3, 72, "Hard12", "1 0 8 0 9 0 0 0 0 2 0 0 3 0 8 0 9 6 0 9 0 0 0 0 4 0 0 4 0 6 0 0 9 0 3 0 0 1 0 2 0 5 0 6 0 0 8 0 6 0 0 2 0 1 0 0 1 0 0 0 0 4 0 3 6 0 9 0 4 0 0 7 0 0 0 0 6 0 3 0 5");
        insertSudoku(db, 3, 73, "Hard13", "0 1 0 0 0 8 5 7 0 6 0 7 0 5 0 0 0 9 0 5 2 1 7 0 0 0 0 0 0 1 0 0 3 7 0 6 0 7 0 0 0 0 0 4 0 8 0 3 7 0 0 9 0 0 0 0 0 0 1 7 2 6 0 1 0 0 0 2 0 4 0 7 0 2 4 3 0 0 0 9 0");
        insertSudoku(db, 3, 74, "Hard14", "0 2 0 4 3 9 8 0 0 0 8 0 0 0 0 0 0 1 0 0 3 0 0 1 5 2 0 0 5 0 0 9 2 7 0 3 0 0 0 0 0 0 0 0 0 3 0 9 7 4 0 0 8 0 0 7 1 3 0 0 9 0 0 6 0 0 0 0 0 0 3 0 0 0 8 9 2 4 0 1 0");
        insertSudoku(db, 3, 75, "Hard15", "0 0 0 5 0 0 2 0 1 8 0 0 0 0 6 0 0 5 0 0 5 2 0 7 0 8 0 0 1 7 9 6 0 8 0 4 0 0 0 0 0 0 0 0 0 9 0 8 0 7 4 6 1 0 0 8 0 4 0 5 3 0 0 7 0 0 6 0 0 0 0 9 5 0 4 0 0 9 0 0 0");
        insertSudoku(db, 3, 76, "Hard16", "9 2 0 0 0 0 0 0 0 5 0 0 8 7 0 0 0 0 0 3 8 0 9 1 0 0 0 0 5 2 9 3 0 1 6 0 0 9 0 0 0 0 0 3 0 0 7 3 0 6 4 9 8 0 0 0 0 4 1 0 2 5 0 0 0 0 0 5 3 0 0 1 0 0 0 0 0 0 0 7 3");
        insertSudoku(db, 3, 77, "Hard17", "5 9 0 0 0 6 0 1 0 0 0 1 2 5 4 7 0 9 0 0 0 0 0 1 4 0 0 0 0 3 7 1 5 0 0 8 1 0 0 0 0 0 0 0 4 2 0 0 6 4 8 1 0 0 0 0 2 5 0 0 0 0 0 7 0 8 4 6 3 9 0 0 0 5 0 1 0 0 0 4 7");
        insertSudoku(db, 3, 78, "Hard18", "3 0 9 8 7 0 0 0 4 0 0 0 0 0 5 0 0 8 8 7 0 4 0 0 0 0 0 1 0 4 5 8 0 0 0 3 0 0 0 7 0 6 0 0 0 7 0 0 0 3 4 1 0 5 0 0 0 0 0 9 0 8 1 9 0 0 3 0 0 0 0 0 4 0 0 0 5 7 2 0 6");
        insertSudoku(db, 3, 79, "Hard19", "8 0 0 2 0 0 0 0 0 9 1 0 3 0 0 7 0 6 0 0 0 0 0 7 0 0 2 0 8 4 0 0 0 0 0 9 0 9 5 1 0 4 8 6 0 1 0 0 0 0 0 2 3 0 5 0 0 6 0 0 0 0 0 6 0 9 0 0 3 0 7 1 0 0 0 0 0 5 0 0 8");
        insertSudoku(db, 3, 80, "Hard20", "0 0 5 0 3 7 0 0 1 0 0 0 0 5 0 6 2 7 6 0 0 0 0 2 5 3 0 0 2 0 0 7 0 0 0 0 0 0 1 9 6 8 2 0 0 0 0 0 0 1 0 0 9 0 0 1 3 7 0 0 0 0 8 4 8 6 0 9 0 0 0 0 7 0 0 8 4 0 1 0 0");
        insertSudoku(db, 3, 81, "Hard21", "0 9 0 3 5 0 7 0 0 0 0 0 8 0 0 0 2 9 0 0 0 4 0 2 0 0 8 7 1 0 0 0 0 0 0 0 4 6 3 5 0 8 2 9 7 0 0 0 0 0 0 0 5 1 3 0 0 2 0 4 0 0 0 9 4 0 0 0 5 0 0 0 0 0 8 0 3 7 0 4 0");
        insertSudoku(db, 3, 82, "Hard22", "0 0 0 0 0 5 9 0 4 0 8 0 0 9 0 6 0 5 0 0 6 0 0 0 0 3 0 0 3 0 7 0 1 4 5 0 0 0 8 0 4 0 7 0 0 0 7 4 2 0 6 0 9 0 0 6 0 0 0 0 3 0 0 8 0 1 0 6 0 0 7 0 3 0 9 8 0 0 0 0 0");
        insertSudoku(db, 3, 83, "Hard23", "0 3 0 0 0 4 0 8 7 9 4 8 7 0 0 5 0 0 0 6 0 8 0 0 0 0 9 0 1 0 5 8 6 7 2 0 0 0 0 0 0 0 0 0 0 0 8 7 3 1 2 0 5 0 8 0 0 0 0 3 0 7 0 0 0 3 0 0 7 8 6 5 5 7 0 2 0 0 0 9 0");
        insertSudoku(db, 3, 84, "Hard24", "3 0 0 6 8 7 0 1 5 0 0 0 0 3 0 0 8 2 0 5 0 0 0 0 3 0 0 4 0 0 3 0 0 0 0 0 6 0 1 0 5 0 7 0 9 0 0 0 0 0 4 0 0 3 0 0 8 0 0 0 0 2 0 2 1 0 0 4 0 0 0 0 9 7 0 5 2 1 0 0 4");
        insertSudoku(db, 3, 85, "Hard25", "7 0 2 0 0 0 0 0 4 0 3 0 7 0 2 0 1 0 4 0 0 0 9 3 0 0 8 0 0 0 8 2 7 0 9 0 0 0 7 0 3 0 8 0 0 0 8 0 9 5 6 0 0 0 3 0 0 5 7 0 0 0 9 0 2 0 3 0 9 0 8 0 6 0 0 0 0 0 5 0 3");
        insertSudoku(db, 3, 86, "Hard26", "3 0 0 0 4 0 0 5 7 4 0 0 8 5 3 0 6 0 0 2 5 7 0 0 0 0 0 0 0 0 0 0 0 4 3 0 8 0 0 4 0 6 0 0 1 0 3 4 0 0 0 0 0 0 0 0 0 0 0 5 6 9 0 0 9 0 6 2 4 0 0 3 1 6 0 0 8 0 0 0 2");
        insertSudoku(db, 3, 87, "Hard27", "0 0 0 2 6 0 0 5 0 0 0 0 0 0 5 9 0 0 0 0 0 3 8 0 0 4 6 0 2 0 0 9 4 0 1 8 0 0 4 0 0 0 5 0 0 9 5 0 8 1 0 0 7 0 3 8 0 0 2 1 0 0 0 0 0 5 7 0 0 0 0 0 0 4 0 0 5 8 0 0 0");
        insertSudoku(db, 3, 88, "Hard28", "0 6 2 0 8 0 5 0 4 0 0 8 0 5 0 0 9 0 7 0 0 3 2 0 0 0 1 0 0 0 7 4 0 6 2 0 0 0 0 2 0 3 0 0 0 0 2 7 0 6 5 0 0 0 2 0 0 0 3 6 0 0 7 0 4 0 0 7 0 1 0 0 8 0 3 0 9 0 2 4 0");
        insertSudoku(db, 3, 89, "Hard29", "0 0 2 0 0 1 0 0 0 0 6 8 0 0 0 0 0 3 0 0 0 0 8 6 0 9 0 9 0 0 0 0 2 0 8 6 8 0 4 0 0 0 1 0 2 5 2 0 8 0 0 0 0 9 0 8 0 1 4 0 0 0 0 1 0 0 0 0 0 9 2 0 0 0 0 7 0 0 5 0 0");
        insertSudoku(db, 3, 90, "Hard30", "0 0 0 0 3 0 0 6 5 4 6 0 9 5 0 2 0 0 0 0 0 0 8 6 0 0 4 0 0 3 0 7 0 0 0 6 0 0 4 0 9 0 1 0 0 5 0 0 0 1 0 3 0 0 2 0 0 1 4 0 0 0 0 0 0 7 0 6 5 0 2 8 6 3 0 0 2 0 0 0 0");
        
        createIndexes(db);
    }
    
    private void insertFolder(SQLiteDatabase db, long folderID, String folderName) {
    	long now = System.currentTimeMillis();
    	db.execSQL("INSERT INTO " + SudokuDatabase.FOLDER_TABLE_NAME + " VALUES ("+ folderID + ", " + now + ", '" + folderName + "');");
    }
    
    private void insertSudoku(SQLiteDatabase db, long folderID, long sudokuID, String sudokuName, String data) {
    	String[] values = data.split(" ");
    	
    	CellCollection cellCollection = CellCollection.createEmpty();
    	Cell[][] cells = cellCollection.getCells();
    	
    	int pos = 0;
    	for (int r=0; r<9; r++) {
    		for (int c=0; c<9; c++) {
    			int value = Integer.parseInt(values[pos++]);
    			cells[r][c] = new Cell();
				cells[r][c].setValue(value);
    			cells[r][c].setEditable(value == 0);
    		}
    	}
    	
    	String cellsString = cellCollection.toString();

    	String sql = "INSERT INTO " + SudokuDatabase.SUDOKU_TABLE_NAME + " VALUES ("+sudokuID+", "+ folderID + ", 0, " + SudokuGame.GAME_STATE_NOT_STARTED + ", 0, null, '"+ cellsString + "', null);";
    	db.execSQL(sql);
    	
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ".");
        
        createIndexes(db);
    }
    
    private void createIndexes(SQLiteDatabase db) {
    	db.execSQL("create index "+SudokuDatabase.SUDOKU_TABLE_NAME+
     		   "_idx1 on "+
     		   SudokuDatabase.SUDOKU_TABLE_NAME+" ("+SudokuColumns.FOLDER_ID+");");    	
    }
}
