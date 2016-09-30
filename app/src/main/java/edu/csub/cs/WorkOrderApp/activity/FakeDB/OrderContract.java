package edu.csub.cs.WorkOrderApp.activity.FakeDB;

import android.provider.BaseColumns;

public class OrderContract {
    public static final String DB_NAME = "edu.csub.cs.WorkOrderApp.activity.FakeDB";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }
}