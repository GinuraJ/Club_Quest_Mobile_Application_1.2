package com.example.clubquest;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
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
public final class TeamsDAO_Impl implements TeamsDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Teams> __insertionAdapterOfTeams;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public TeamsDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTeams = new EntityInsertionAdapter<Teams>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `table2` (`Id`,`idTeam`,`Name`,`strTeamShort`,`strAlternate`,`intFormedYear`,`strLeague`,`idLeague`,`strStadium`,`strKeywords`,`strStadiumThumb`,`strStadiumLocation`,`intStadiumCapacity`,`strWebsite`,`strTeamJersey`,`strTeamLogo`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Teams entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getIdTeam() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getIdTeam());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getStrTeamShort() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getStrTeamShort());
        }
        if (entity.getStrAlternate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getStrAlternate());
        }
        if (entity.getIntFormedYear() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getIntFormedYear());
        }
        if (entity.getStrLeague() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getStrLeague());
        }
        if (entity.getIdLeague() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getIdLeague());
        }
        if (entity.getStrStadium() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getStrStadium());
        }
        if (entity.getStrKeywords() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getStrKeywords());
        }
        if (entity.getStrStadiumThumb() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getStrStadiumThumb());
        }
        if (entity.getStrStadiumLocation() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getStrStadiumLocation());
        }
        if (entity.getIntStadiumCapacity() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getIntStadiumCapacity());
        }
        if (entity.getStrWebsite() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getStrWebsite());
        }
        if (entity.getStrTeamJersey() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getStrTeamJersey());
        }
        if (entity.getStrTeamLogo() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getStrTeamLogo());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from table2";
        return _query;
      }
    };
  }

  @Override
  public Object insertTeam(final Teams league, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTeams.insert(league);
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
  public Object search(final String queryString,
      final Continuation<? super List<Teams>> $completion) {
    final String _sql = "SELECT * FROM table2 WHERE LOWER(strLeague) LIKE ? OR LOWER(Name) LIKE ? OR LOWER(strAlternate) LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, queryString);
    _argIndex = 2;
    _statement.bindString(_argIndex, queryString);
    _argIndex = 3;
    _statement.bindString(_argIndex, queryString);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Teams>>() {
      @Override
      @NonNull
      public List<Teams> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfIdTeam = CursorUtil.getColumnIndexOrThrow(_cursor, "idTeam");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
          final int _cursorIndexOfStrTeamShort = CursorUtil.getColumnIndexOrThrow(_cursor, "strTeamShort");
          final int _cursorIndexOfStrAlternate = CursorUtil.getColumnIndexOrThrow(_cursor, "strAlternate");
          final int _cursorIndexOfIntFormedYear = CursorUtil.getColumnIndexOrThrow(_cursor, "intFormedYear");
          final int _cursorIndexOfStrLeague = CursorUtil.getColumnIndexOrThrow(_cursor, "strLeague");
          final int _cursorIndexOfIdLeague = CursorUtil.getColumnIndexOrThrow(_cursor, "idLeague");
          final int _cursorIndexOfStrStadium = CursorUtil.getColumnIndexOrThrow(_cursor, "strStadium");
          final int _cursorIndexOfStrKeywords = CursorUtil.getColumnIndexOrThrow(_cursor, "strKeywords");
          final int _cursorIndexOfStrStadiumThumb = CursorUtil.getColumnIndexOrThrow(_cursor, "strStadiumThumb");
          final int _cursorIndexOfStrStadiumLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "strStadiumLocation");
          final int _cursorIndexOfIntStadiumCapacity = CursorUtil.getColumnIndexOrThrow(_cursor, "intStadiumCapacity");
          final int _cursorIndexOfStrWebsite = CursorUtil.getColumnIndexOrThrow(_cursor, "strWebsite");
          final int _cursorIndexOfStrTeamJersey = CursorUtil.getColumnIndexOrThrow(_cursor, "strTeamJersey");
          final int _cursorIndexOfStrTeamLogo = CursorUtil.getColumnIndexOrThrow(_cursor, "strTeamLogo");
          final List<Teams> _result = new ArrayList<Teams>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Teams _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpIdTeam;
            if (_cursor.isNull(_cursorIndexOfIdTeam)) {
              _tmpIdTeam = null;
            } else {
              _tmpIdTeam = _cursor.getString(_cursorIndexOfIdTeam);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpStrTeamShort;
            if (_cursor.isNull(_cursorIndexOfStrTeamShort)) {
              _tmpStrTeamShort = null;
            } else {
              _tmpStrTeamShort = _cursor.getString(_cursorIndexOfStrTeamShort);
            }
            final String _tmpStrAlternate;
            if (_cursor.isNull(_cursorIndexOfStrAlternate)) {
              _tmpStrAlternate = null;
            } else {
              _tmpStrAlternate = _cursor.getString(_cursorIndexOfStrAlternate);
            }
            final String _tmpIntFormedYear;
            if (_cursor.isNull(_cursorIndexOfIntFormedYear)) {
              _tmpIntFormedYear = null;
            } else {
              _tmpIntFormedYear = _cursor.getString(_cursorIndexOfIntFormedYear);
            }
            final String _tmpStrLeague;
            if (_cursor.isNull(_cursorIndexOfStrLeague)) {
              _tmpStrLeague = null;
            } else {
              _tmpStrLeague = _cursor.getString(_cursorIndexOfStrLeague);
            }
            final String _tmpIdLeague;
            if (_cursor.isNull(_cursorIndexOfIdLeague)) {
              _tmpIdLeague = null;
            } else {
              _tmpIdLeague = _cursor.getString(_cursorIndexOfIdLeague);
            }
            final String _tmpStrStadium;
            if (_cursor.isNull(_cursorIndexOfStrStadium)) {
              _tmpStrStadium = null;
            } else {
              _tmpStrStadium = _cursor.getString(_cursorIndexOfStrStadium);
            }
            final String _tmpStrKeywords;
            if (_cursor.isNull(_cursorIndexOfStrKeywords)) {
              _tmpStrKeywords = null;
            } else {
              _tmpStrKeywords = _cursor.getString(_cursorIndexOfStrKeywords);
            }
            final String _tmpStrStadiumThumb;
            if (_cursor.isNull(_cursorIndexOfStrStadiumThumb)) {
              _tmpStrStadiumThumb = null;
            } else {
              _tmpStrStadiumThumb = _cursor.getString(_cursorIndexOfStrStadiumThumb);
            }
            final String _tmpStrStadiumLocation;
            if (_cursor.isNull(_cursorIndexOfStrStadiumLocation)) {
              _tmpStrStadiumLocation = null;
            } else {
              _tmpStrStadiumLocation = _cursor.getString(_cursorIndexOfStrStadiumLocation);
            }
            final String _tmpIntStadiumCapacity;
            if (_cursor.isNull(_cursorIndexOfIntStadiumCapacity)) {
              _tmpIntStadiumCapacity = null;
            } else {
              _tmpIntStadiumCapacity = _cursor.getString(_cursorIndexOfIntStadiumCapacity);
            }
            final String _tmpStrWebsite;
            if (_cursor.isNull(_cursorIndexOfStrWebsite)) {
              _tmpStrWebsite = null;
            } else {
              _tmpStrWebsite = _cursor.getString(_cursorIndexOfStrWebsite);
            }
            final String _tmpStrTeamJersey;
            if (_cursor.isNull(_cursorIndexOfStrTeamJersey)) {
              _tmpStrTeamJersey = null;
            } else {
              _tmpStrTeamJersey = _cursor.getString(_cursorIndexOfStrTeamJersey);
            }
            final String _tmpStrTeamLogo;
            if (_cursor.isNull(_cursorIndexOfStrTeamLogo)) {
              _tmpStrTeamLogo = null;
            } else {
              _tmpStrTeamLogo = _cursor.getString(_cursorIndexOfStrTeamLogo);
            }
            _item = new Teams(_tmpId,_tmpIdTeam,_tmpName,_tmpStrTeamShort,_tmpStrAlternate,_tmpIntFormedYear,_tmpStrLeague,_tmpIdLeague,_tmpStrStadium,_tmpStrKeywords,_tmpStrStadiumThumb,_tmpStrStadiumLocation,_tmpIntStadiumCapacity,_tmpStrWebsite,_tmpStrTeamJersey,_tmpStrTeamLogo);
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
