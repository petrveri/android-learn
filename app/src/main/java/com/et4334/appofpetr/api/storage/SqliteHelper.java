package com.et4334.appofpetr.api.storage;

import android.content.Context;

import com.et4334.appofpetr.vo.River;

import java.util.List;

/**
 * Created by et4334 on 7/13/15.
 */
public class SqliteHelper {

    public void createDB() {

    }

    public River getRecord(Context ctx, String id) {
        River river = new ExtRiverSqliteHelper(ctx).getRecord(id);
        return river;
    }

    public void deleteRecord(Context ctx, String id) {
        new ExtRiverSqliteHelper(ctx).deleteRecord(id);
    }

    public List<River> listRecords(Context ctx) {
        List<River> rivers = new ExtRiverSqliteHelper(ctx).listRecords();
        return rivers;
    }

    public void persistRecord(Context ctx, String id, String name) {
        ExtRiverSqliteHelper rh = new ExtRiverSqliteHelper(ctx);
        River river = rh.getRecord(id);
        if (river.getId().equals("*")) {
            rh.insertRecord(id, name);
        } else {
            rh.updateRecord(id, name);
        }
    }

}
