package android.example.ub_durensawit.DbConn.local;

import android.content.Context;
import android.example.ub_durensawit.Model.Cart;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = (Cart.class), version = 1)
public abstract class CartDatabase extends RoomDatabase {

public abstract CartDao cartDao();
private static CartDatabase instance;

public static CartDatabase getInstance(Context context){
    if(instance == null){
        instance = Room.databaseBuilder(context,CartDatabase.class,"UB_DurenSawit")
                .allowMainThreadQueries()
                .build();

    }
    return instance;
}

}
