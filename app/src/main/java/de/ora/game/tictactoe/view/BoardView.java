package de.ora.game.tictactoe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import de.ora.game.tictactoe.TicTacToeActivity;
import de.ora.game.tictactoe.game.GameEngine;
import de.ora.game.tictactoe.game.Player;

public class BoardView extends View {

    private static final int LINE_THICK = 5;
    private static final int ELT_MARGIN = 20;
    private static final int ELT_STROKE_WIDTH = 15;
    private int width, height, eltW, eltH;
    private Paint gridPaint, oPaint, xPaint;
    private GameEngine gameEngine;
    private TicTacToeActivity activity;

    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        gridPaint = new Paint();
        oPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        oPaint.setColor(Color.RED);
        oPaint.setStyle(Paint.Style.STROKE);
        oPaint.setStrokeWidth(ELT_STROKE_WIDTH);
        xPaint = new Paint(oPaint);
        xPaint.setColor(Color.BLUE);
    }

    public void setMainActivity(TicTacToeActivity a) {
        activity = a;
    }

    public void setGameEngine(GameEngine g) {
        gameEngine = g;
        gameEngine.newGame();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        eltW = (width - LINE_THICK) / 3;
        eltH = (height - LINE_THICK) / 3;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawGrid(canvas);
        drawBoard(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Player computerPlayer = gameEngine.getComputerPlayer();
        Player activePlayer = gameEngine.getActivePlayer();
        if (computerPlayer == activePlayer) {
            return super.onTouchEvent(event);
        }


        Player winner = gameEngine.checkWinner();
        int possibleMoves = gameEngine.possibleMoves();
        if (possibleMoves == 0) {
            activity.gameEnded(winner);
        } else if (Player.NONE == winner && event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) (event.getX() / eltW);
            int y = (int) (event.getY() / eltH);
            boolean valid = gameEngine.play(x, y);
            invalidate();

            winner = gameEngine.checkWinner();
            possibleMoves = gameEngine.possibleMoves();
            if (winner != Player.NONE) {
                activity.gameEnded(winner);
            } else if (possibleMoves == 0) {
                activity.gameEnded(winner);
            } else {
                // computer plays ...
                if (!gameEngine.computer()) {
                    valid = gameEngine.play(x, y);
                }
                invalidate();

                winner = gameEngine.checkWinner();
                if (winner != Player.NONE) {
                    activity.gameEnded(winner);
                }
            }
        }

        return super.onTouchEvent(event);
    }

    private void drawBoard(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                drawElt(canvas, gameEngine.elements(i, j), i, j);
            }
        }
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < 2; i++) {
            // vertical lines
            float left = eltW * (i + 1);
            float right = left + LINE_THICK;
            float top = 0;
            float bottom = height;

            canvas.drawRect(left, top, right, bottom, gridPaint);

            // horizontal lines
            float left2 = 0;
            float right2 = width;
            float top2 = eltH * (i + 1);
            float bottom2 = top2 + LINE_THICK;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);
        }
    }

    private void drawElt(Canvas canvas, Double value, int x, int y) {
        Player currentPlayer = Player.from(value.intValue());

        switch (currentPlayer) {
            case PLAYER1:
                float cx = (eltW * x) + eltW / 2;
                float cy = (eltH * y) + eltH / 2;

                canvas.drawCircle(cx, cy, Math.min(eltW, eltH) / 2 - ELT_MARGIN * 2, oPaint);
                break;
            case PLAYER2:

                float startX = (eltW * x) + ELT_MARGIN;
                float startY = (eltH * y) + ELT_MARGIN;
                float endX = startX + eltW - ELT_MARGIN * 2;
                float endY = startY + eltH - ELT_MARGIN;

                canvas.drawLine(startX, startY, endX, endY, xPaint);

                float startX2 = (eltW * (x + 1)) - ELT_MARGIN;
                float startY2 = (eltH * y) + ELT_MARGIN;
                float endX2 = startX2 - eltW + ELT_MARGIN * 2;
                float endY2 = startY2 + eltH - ELT_MARGIN;

                canvas.drawLine(startX2, startY2, endX2, endY2, xPaint);
                break;
        }
    }

}
