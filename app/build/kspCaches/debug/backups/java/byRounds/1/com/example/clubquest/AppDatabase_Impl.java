package com.example.clubquest;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile LeagueDoa _leagueDoa;

  private volatile TeamsDAO _teamsDAO;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `table1` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `leagueId` TEXT, `strLeague` TEXT, `strSport` TEXT, `strLeagueAlternate` TEXT, `strLogo` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `table2` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idTeam` TEXT, `Name` TEXT, `strTeamShort` TEXT, `strAlternate` TEXT, `intFormedYear` TEXT, `strLeague` TEXT, `idLeague` TEXT, `strStadium` TEXT, `strKeywords` TEXT, `strStadiumThumb` TEXT, `strStadiumLocation` TEXT, `intStadiumCapacity` TEXT, `strWebsite` TEXT, `strTeamJersey` TEXT, `strTeamLogo` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c99ccb527e730e96a0bfffbc71a884ab')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `table1`");
        db.execSQL("DROP TABLE IF EXISTS `table2`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsTable1 = new HashMap<String, TableInfo.Column>(6);
        _columnsTable1.put("Id", new TableInfo.Column("Id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable1.put("leagueId", new TableInfo.Column("leagueId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable1.put("strLeague", new TableInfo.Column("strLeague", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable1.put("strSport", new TableInfo.Column("strSport", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable1.put("strLeagueAlternate", new TableInfo.Column("strLeagueAlternate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable1.put("strLogo", new TableInfo.Column("strLogo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTable1 = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTable1 = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTable1 = new TableInfo("table1", _columnsTable1, _foreignKeysTable1, _indicesTable1);
        final TableInfo _existingTable1 = TableInfo.read(db, "table1");
        if (!_infoTable1.equals(_existingTable1)) {
          return new RoomOpenHelper.ValidationResult(false, "table1(com.example.clubquest.League).\n"
                  + " Expected:\n" + _infoTable1 + "\n"
                  + " Found:\n" + _existingTable1);
        }
        final HashMap<String, TableInfo.Column> _columnsTable2 = new HashMap<String, TableInfo.Column>(16);
        _columnsTable2.put("Id", new TableInfo.Column("Id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("idTeam", new TableInfo.Column("idTeam", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("Name", new TableInfo.Column("Name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("strTeamShort", new TableInfo.Column("strTeamShort", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("strAlternate", new TableInfo.Column("strAlternate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("intFormedYear", new TableInfo.Column("intFormedYear", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("strLeague", new TableInfo.Column("strLeague", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("idLeague", new TableInfo.Column("idLeague", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("strStadium", new TableInfo.Column("strStadium", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("strKeywords", new TableInfo.Column("strKeywords", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("strStadiumThumb", new TableInfo.Column("strStadiumThumb", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("strStadiumLocation", new TableInfo.Column("strStadiumLocation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("intStadiumCapacity", new TableInfo.Column("intStadiumCapacity", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("strWebsite", new TableInfo.Column("strWebsite", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("strTeamJersey", new TableInfo.Column("strTeamJersey", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTable2.put("strTeamLogo", new TableInfo.Column("strTeamLogo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTable2 = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTable2 = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTable2 = new TableInfo("table2", _columnsTable2, _foreignKeysTable2, _indicesTable2);
        final TableInfo _existingTable2 = TableInfo.read(db, "table2");
        if (!_infoTable2.equals(_existingTable2)) {
          return new RoomOpenHelper.ValidationResult(false, "table2(com.example.clubquest.Teams).\n"
                  + " Expected:\n" + _infoTable2 + "\n"
                  + " Found:\n" + _existingTable2);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c99ccb527e730e96a0bfffbc71a884ab", "c5051dc6e1d26af3439ac531a0e46dc9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "table1","table2");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `table1`");
      _db.execSQL("DELETE FROM `table2`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LeagueDoa.class, LeagueDoa_Impl.getRequiredConverters());
    _typeConvertersMap.put(TeamsDAO.class, TeamsDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public LeagueDoa leagueDoa() {
    if (_leagueDoa != null) {
      return _leagueDoa;
    } else {
      synchronized(this) {
        if(_leagueDoa == null) {
          _leagueDoa = new LeagueDoa_Impl(this);
        }
        return _leagueDoa;
      }
    }
  }

  @Override
  public TeamsDAO teamsDoa() {
    if (_teamsDAO != null) {
      return _teamsDAO;
    } else {
      synchronized(this) {
        if(_teamsDAO == null) {
          _teamsDAO = new TeamsDAO_Impl(this);
        }
        return _teamsDAO;
      }
    }
  }
}
