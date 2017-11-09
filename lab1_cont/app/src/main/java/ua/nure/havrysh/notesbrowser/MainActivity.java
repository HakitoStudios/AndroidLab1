package ua.nure.havrysh.notesbrowser;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor cursor = getContentResolver()
                .query(Uri.parse("content://NotesProvider"), null, null, null, null);
        TextView notesTv = findViewById(R.id.notes_tv);
        if(cursor!=null) {
            notesTv.setText(DatabaseUtils.dumpCursorToString(cursor));
        }else{
            notesTv.setText("No Notes found");
        }
    }
}
