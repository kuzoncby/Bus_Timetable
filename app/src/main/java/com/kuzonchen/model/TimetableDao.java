package com.kuzonchen.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.kuzonchen.model.Timetable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "TIMETABLE".
 */
public class TimetableDao extends AbstractDao<Timetable, Long> {

    public static final String TABLENAME = "TIMETABLE";

    /**
     * Properties of entity Timetable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StopName = new Property(1, String.class, "StopName", false, "STOP_NAME");
        public final static Property StopTime = new Property(2, String.class, "StopTime", false, "STOP_TIME");
    }

    ;


    public TimetableDao(DaoConfig config) {
        super(config);
    }

    public TimetableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"TIMETABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"STOP_NAME\" TEXT," + // 1: StopName
                "\"STOP_TIME\" TEXT);"); // 2: StopTime
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TIMETABLE\"";
        db.execSQL(sql);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, Timetable entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String StopName = entity.getStopName();
        if (StopName != null) {
            stmt.bindString(2, StopName);
        }

        String StopTime = entity.getStopTime();
        if (StopTime != null) {
            stmt.bindString(3, StopTime);
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Timetable readEntity(Cursor cursor, int offset) {
        Timetable entity = new Timetable( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // StopName
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // StopTime
        );
        return entity;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, Timetable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStopName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStopTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Long updateKeyAfterInsert(Timetable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long getKey(Timetable entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
