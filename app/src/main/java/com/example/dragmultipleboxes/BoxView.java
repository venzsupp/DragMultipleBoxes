package com.example.dragmultipleboxes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
public class BoxView extends View {
    float x,y;

    boolean actionType = true;

    boolean clickAction = true;

    float[][] coordinates = new float[1][2];

    RectF rectangle[] = new RectF[1];

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
                        int i = 0;
                        while (i < coordinates.length ) {
                            if (rectangle[i] != null) {
                                if (rectangle[i].contains(event.getX(),event.getY())) {
                                    this.clickAction = false;
                                    break;
                                }
                            } else if (coordinates[i][0] == 0) {
                                coordinates[i][0] = this.x;
                                coordinates[i][1] = this.y;
                                this.clickAction = true;
                                break;
                            }
                            i++;
                        }
                    }
                }
                this.actionType = true;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                // on mouse event
                this.actionType = false;
                this.clickAction = false;
                int i = 0;
                while (i < coordinates.length ) {
                    if (rectangle[i] != null) {
                        if (rectangle[i].contains(event.getX(),event.getY())) {
                            this.moveIndex = i;
                            break;
                        }
                    }
                    i++;
                }

                float boxRight = event.getX()+(this.getWidth()*10)/100;
                float boxBottom = event.getY()+(this.getWidth()*10)/100;
                if (event.getX() > 0 && event.getY() > 0 && boxRight < this.getWidth() && boxBottom < this.getHeight()) {
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
        int i = 0;
        int coordinateSize = 0;
        boolean addCoord = true;
        while(coordinateSize < coordinates.length ){
            if (coordinates[i][0] > 0)  {
                float ltCord = coordinates[i][0];
                float topCord = coordinates[i][1];
                int boxSize = (this.getWidth()*10)/100;
                float rtCord = ltCord + boxSize;
                float botCord = topCord + boxSize;

                RectF rectf = new RectF(ltCord, topCord, rtCord,botCord);
                rectangle[i] = rectf;
                canvas.drawRect(rectangle[i], paint);
                i++;

                if (addCoord  && this.clickAction) {
                    //dynamically increase rectangle array
                    RectF tempRectangle[] = new RectF[rectangle.length+1];
                    System.arraycopy(rectangle,0,tempRectangle,0,rectangle.length);
                    rectangle = tempRectangle;

                    //dynamically increase coordinate array
                    float[][] tempCoord = new float[coordinates.length+1][2];
                    System.arraycopy(coordinates,0,tempCoord,0,coordinates.length);
                    coordinates = tempCoord;

                    addCoord = false;
                }

            }
            coordinateSize++;
        }
    }

}
