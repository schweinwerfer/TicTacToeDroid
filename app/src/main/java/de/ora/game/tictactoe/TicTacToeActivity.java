package de.ora.game.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import de.ora.game.tictactoe.game.GameEngine;
import de.ora.game.tictactoe.game.Player;
import de.ora.game.tictactoe.view.BoardView;

/**
 * See https://www.ssaurel.com/blog/learn-to-create-a-tic-tac-toe-game-for-android/
 */
public class TicTacToeActivity extends AppCompatActivity {
    private BoardView boardView;
    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameEngine = new GameEngine();

        boardView = findViewById(R.id.board);
        boardView.setGameEngine(gameEngine);
        boardView.setMainActivity(this);

        TextView textView = findViewById(R.id.TextView01);
        textView.setBackgroundColor(Color.BLACK);
        textView.setTextColor(Color.LTGRAY);
        textView.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");

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
        if (id == R.id.action_newgame) {
            newGame();
        }

        return super.onOptionsItemSelected(item);
    }

    private void newGame() {
        gameEngine.newGame();
        boardView.invalidate();
    }

    public void gameEnded(Player winner) {
        AlertDialog alertDialog = new AlertDialog.Builder(TicTacToeActivity.this).create();
        alertDialog.setTitle("Spiel beendet");
        alertDialog.setMessage("Gewinner: " + winner.name());
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
