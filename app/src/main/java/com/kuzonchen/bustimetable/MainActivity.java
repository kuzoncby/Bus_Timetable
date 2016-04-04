package com.kuzonchen.bustimetable;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.kuzonchen.model.DaoMaster;
import com.kuzonchen.model.DaoSession;
import com.kuzonchen.model.Timetable;
import com.kuzonchen.model.TimetableDao;

import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

public class MainActivity extends AppCompatActivity {
    private DaoMaster.DevOpenHelper helper;
    private DaoMaster master;
    private DaoSession session;
    private TimetableDao dao;

    private EditText editData;
    private ListView listData;
    private SimpleCursorAdapter adapter;
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        editData = (EditText) findViewById(R.id.editData);
        listData = (ListView) findViewById(R.id.listData);

        helper = new DaoMaster.DevOpenHelper(this, "timtable-db", null);
        db = helper.getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        dao = session.getTimetableDao();

        cursor = db.query(dao.getTablename(), dao.getAllColumns(), null, null, null, null, null, null);

        String[] from = {TimetableDao.Properties.Id.columnName, TimetableDao.Properties.StopName.columnName, TimetableDao.Properties.StopTime.columnName};
        int[] to = {android.R.id.text1, android.R.id.text2};
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to, SimpleCursorAdapter.NO_SELECTION);
        listData.setAdapter(adapter);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnInsert:
                insert();
                Cursor cursorAdd = db.query(dao.getTablename(), dao.getAllColumns(), null, null, null, null, null);
                adapter.swapCursor(cursorAdd);
                break;
            case R.id.btnDelete:
                delete();
                Cursor cursorDelete = db.query(dao.getTablename(), dao.getAllColumns(), null, null, null, null, null);
                adapter.swapCursor(cursorDelete);
                break;
            case R.id.btnUpdate:
                update();
                Cursor cursorUpdate = db.query(dao.getTablename(), dao.getAllColumns(), null, null, null, null, null);
                adapter.swapCursor(cursorUpdate);
                break;
            case R.id.btnSearch:
                search();
                Cursor cursorSearch = db.query(dao.getTablename(), dao.getAllColumns(), null, null, null, null, null);
                adapter.swapCursor(cursorSearch);
                break;
            default:
                break;
        }
    }

    private void insert() {
        String content = editData.getText().toString().trim();
        Timetable timetable = new Timetable(null, "宜浩家园", content);
        dao.insert(timetable);
    }

    private void delete() {
        String content = editData.getText().toString().trim();
        dao.deleteByKey(Long.valueOf(content));
    }

    private void update() {
        String content = editData.getText().toString().trim();
        dao.update(new Timetable(Long.valueOf(content), "修改的", content));
    }

    private void search() {
        String content = editData.getText().toString().trim();
        Query<Timetable> query = dao.queryBuilder().where(TimetableDao.Properties.StopTime.eq(content)).build();
        List<Timetable> list = query.list();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        new AlertDialog.Builder(this).setMessage(list.get(0).getStopName() + ", " + list.get(0).getStopTime()).setPositiveButton("关闭", null).create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
