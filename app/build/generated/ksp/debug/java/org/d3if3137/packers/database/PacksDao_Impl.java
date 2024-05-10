package org.d3if3137.packers.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
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
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PacksDao_Impl implements PacksDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Packs> __insertionAdapterOfPacks;

  private final EntityDeletionOrUpdateAdapter<Packs> __deletionAdapterOfPacks;

  private final EntityDeletionOrUpdateAdapter<Packs> __updateAdapterOfPacks;

  public PacksDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPacks = new EntityInsertionAdapter<Packs>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Packs` (`id`,`judul`,`isi`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Packs entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getJudul());
        statement.bindString(3, entity.getIsi());
      }
    };
    this.__deletionAdapterOfPacks = new EntityDeletionOrUpdateAdapter<Packs>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Packs` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Packs entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPacks = new EntityDeletionOrUpdateAdapter<Packs>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Packs` SET `id` = ?,`judul` = ?,`isi` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Packs entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getJudul());
        statement.bindString(3, entity.getIsi());
        statement.bindLong(4, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Packs packs, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPacks.insert(packs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Packs packs, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPacks.handle(packs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Packs packs, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPacks.handle(packs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Packs>> getCatatan() {
    final String _sql = "SELECT * FROM packs ORDER BY judul";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"packs"}, new Callable<List<Packs>>() {
      @Override
      @NonNull
      public List<Packs> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfJudul = CursorUtil.getColumnIndexOrThrow(_cursor, "judul");
          final int _cursorIndexOfIsi = CursorUtil.getColumnIndexOrThrow(_cursor, "isi");
          final List<Packs> _result = new ArrayList<Packs>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Packs _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpJudul;
            _tmpJudul = _cursor.getString(_cursorIndexOfJudul);
            final String _tmpIsi;
            _tmpIsi = _cursor.getString(_cursorIndexOfIsi);
            _item = new Packs(_tmpId,_tmpJudul,_tmpIsi);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
