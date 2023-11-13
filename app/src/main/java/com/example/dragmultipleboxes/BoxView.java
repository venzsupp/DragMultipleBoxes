package com.example.dragmultipleboxes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.icu.text.SymbolTable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class BoxView extends View {
    float x,dx;
    float y,dy;


    Rect rectangle;

    boolean actionType = true;

    float[][] coordinates = new float[10][2];

    RectF rectArr[] = new RectF[10];

    int moveIndex = 0;
    public BoxView(Context context){
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                this.x = event.getX();
                this.y = event.getY();
                if (coordinates[0][0] == 0) {
                    coordinates[0][0] = this.x;
                    coordinates[0][1] = this.y;
                } else {
                    if (this.actionType) {
                        for (int i=0; i < 10; i++) {
                            if(coordinates[i][0] == 0){
                                coordinates[i][0] = this.x;
                                coordinates[i][1] = this.y;
                                break;
                            }

                        }
                    }
                }
                this.actionType = true;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                // on mouse event
                this.actionType = false;
                for ( int i=0; i<10; i++) {
                    System.out.println(rectArr[i]);
                    if (rectArr[i] != null) {
                        if (rectArr[i].contains(event.getX(),event.getY())) {
                            this.moveIndex = i;
                            break;
                        }
                    }
                }
                if (event.getX() > 0 && event.getY() > 0) {
                    coordinates[this.moveIndex ][0] = event.getX();
                    coordinates[this.moveIndex ][1] = event.getY();
                }

                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        Paint paint = new Paint();
        for (int i=0; i < 10; i++) {
            if (coordinates[i][0] > 0) {
                float ltCord = coordinates[i][0];
                float topCord = coordinates[i][1];
                float boxSize = (this.getWidth()*10)/100;
                float rtCord = ltCord + boxSize;
                float botCord = topCord + boxSize;

                RectF rectf = new RectF(ltCord, topCord, rtCord,botCord);
                rectArr[i] = rectf;
                canvas.drawRect(rectArr[i], paint);

                System.out.println("coord===="+i+"  :: "+coordinates[i][0]);
            }

        }

    }

}
