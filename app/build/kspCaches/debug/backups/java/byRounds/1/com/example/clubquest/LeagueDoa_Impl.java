package com.example.clubquest;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class LeagueDoa_Impl implements LeagueDoa {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<League> __insertionAdapterOfLeague;

  private final EntityInsertionAdapter<League> __insertionAdapterOfLeague_1;

  private final EntityDeletionOrUpdateAdapter<League> __deletionAdapterOfLeague;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public LeagueDoa_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLeague = new EntityInsertionAdapter<League>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `League` (`Id`,`leagueId`,`strLeague`,`strSport`,`strLeagueAlternate`,`strLogo`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final League entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getLeagueId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getLeagueId());
        }
        if (entity.getStrLeague() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getStrLeague());
        }
        if (entity.getStrSport() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getStrSport());
        }
        if (entity.getStrLeagueAlternate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getStrLeagueAlternate());
        }
        if (entity.getStrLogo() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getStrLogo());
        }
      }
    };
    this.__insertionAdapterOfLeague_1 = new EntityInsertionAdapter<League>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `League` (`Id`,`leagueId`,`strLeague`,`strSport`,`strLeagueAlternate`,`strLogo`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final League entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getLeagueId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getLeagueId());
        }
        if (entity.getStrLeague() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getStrLeague());
        }
        if (entity.getStrSport() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getStrSport());
        }
        if (entity.getStrLeagueAlternate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getStrLeagueAlternate());
        }
        if (entity.getStrLogo() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getStrLogo());
        }
      }
    };
    this.__deletionAdapterOfLeague = new EntityDeletionOrUpdateAdapter<League>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `League` WHERE `Id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final League entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from league";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final League[] league, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLeague.insert(league);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertUser(final League league, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLeague_1.insert(league);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUser(final League league, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLeague.handle(league);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<League>> $completion) {
    final String _sql = "select * from league";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<League>>() {
      @Override
      @NonNull
      public List<League> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfLeagueId = CursorUtil.getColumnIndexOrThrow(_cursor, "leagueId");
          final int _cursorIndexOfStrLeague = CursorUtil.getColumnIndexOrThrow(_cursor, "strLeague");
          final int _cursorIndexOfStrSport = CursorUtil.getColumnIndexOrThrow(_cursor, "strSport");
          final int _cursorIndexOfStrLeagueAlternate = CursorUtil.getColumnIndexOrThrow(_cursor, "strLeagueAlternate");
          final int _cursorIndexOfStrLogo = CursorUtil.getColumnIndexOrThrow(_cursor, "strLogo");
          final List<League> _result = new ArrayList<League>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final League _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpLeagueId;
            if (_cursor.isNull(_cursorIndexOfLeagueId)) {
              _tmpLeagueId = null;
            } else {
              _tmpLeagueId = _cursor.getString(_cursorIndexOfLeagueId);
            }
            final String _tmpStrLeague;
            if (_cursor.isNull(_cursorIndexOfStrLeague)) {
              _tmpStrLeague = null;
            } else {
              _tmpStrLeague = _cursor.getString(_cursorIndexOfStrLeague);
            }
            final String _tmpStrSport;
            if (_cursor.isNull(_cursorIndexOfStrSport)) {
              _tmpStrSport = null;
            } else {
              _tmpStrSport = _cursor.getString(_cursorIndexOfStrSport);
            }
            final String _tmpStrLeagueAlternate;
            if (_cursor.isNull(_cursorIndexOfStrLeagueAlternate)) {
              _tmpStrLeagueAlternate = null;
            } else {
              _tmpStrLeagueAlternate = _cursor.getString(_cursorIndexOfStrLeagueAlternate);
            }
            final String _tmpStrLogo;
            if (_cursor.isNull(_cursorIndexOfStrLogo)) {
              _tmpStrLogo = null;
            } else {
              _tmpStrLogo = _cursor.getString(_cursorIndexOfStrLogo);
            }
            _item = new League(_tmpId,_tmpLeagueId,_tmpStrLeague,_tmpStrSport,_tmpStrLeagueAlternate,_tmpStrLogo);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object search(final String queryString,
      final Continuation<? super List<League>> $completion) {
    final String _sql = "SELECT * FROM League WHERE LOWER(strLeague) LIKE ? OR LOWER(strSport) LIKE ? OR LOWER(strLeagueAlternate) LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, queryString);
    _argIndex = 2;
    _statement.bindString(_argIndex, queryString);
    _argIndex = 3;
    _statement.bindString(_argIndex, queryString);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<League>>() {
      @Override
      @NonNull
      public List<League> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfLeagueId = CursorUtil.getColumnIndexOrThrow(_cursor, "leagueId");
          final int _cursorIndexOfStrLeague = CursorUtil.getColumnIndexOrThrow(_cursor, "strLeague");
          final int _cursorIndexOfStrSport = CursorUtil.getColumnIndexOrThrow(_cursor, "strSport");
          final int _cursorIndexOfStrLeagueAlternate = CursorUtil.getColumnIndexOrThrow(_cursor, "strLeagueAlternate");
          final int _cursorIndexOfStrLogo = CursorUtil.getColumnIndexOrThrow(_cursor, "strLogo");
          final List<League> _result = new ArrayList<League>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final League _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpLeagueId;
            if (_cursor.isNull(_cursorIndexOfLeagueId)) {
              _tmpLeagueId = null;
            } else {
              _tmpLeagueId = _cursor.getString(_cursorIndexOfLeagueId);
            }
            final String _tmpStrLeague;
            if (_cursor.isNull(_cursorIndexOfStrLeague)) {
              _tmpStrLeague = null;
            } else {
              _tmpStrLeague = _cursor.getString(_cursorIndexOfStrLeague);
            }
            final String _tmpStrSport;
            if (_cursor.isNull(_cursorIndexOfStrSport)) {
              _tmpStrSport = null;
            } else {
              _tmpStrSport = _cursor.getString(_cursorIndexOfStrSport);
            }
            final String _tmpStrLeagueAlternate;
            if (_cursor.isNull(_cursorIndexOfStrLeagueAlternate)) {
              _tmpStrLeagueAlternate = null;
            } else {
              _tmpStrLeagueAlternate = _cursor.getString(_cursorIndexOfStrLeagueAlternate);
            }
            final String _tmpStrLogo;
            if (_cursor.isNull(_cursorIndexOfStrLogo)) {
              _tmpStrLogo = null;
            } else {
              _tmpStrLogo = _cursor.getString(_cursorIndexOfStrLogo);
            }
            _item = new League(_tmpId,_tmpLeagueId,_tmpStrLeague,_tmpStrSport,_tmpStrLeagueAlternate,_tmpStrLogo);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
