package liam.mazedroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: game display class
 */
public class gameview extends View {
    int moves = 0;
    int bestmoves = 0;
    maze [][] map = new maze[30][30];
    int with = 820;
    int hight = 820;
    int bpp = 32;
    boolean win = false;
    int beginx = 0;
    int beginy = 0;
    int numFrames = 0;
    int mousex = 0;
    int mousey = 0;
    Paint paint;
    Path path;

    public gameview(Context context, int mode) {
        super(context);
        init(mode);
    }

    public gameview(Context context, AttributeSet attrs, int mode) {
        super(context, attrs);
        init(mode);
    }

    public gameview(Context context, AttributeSet attrs, int defStyle, int mode) {
        super(context, attrs, defStyle);
        init(mode);
    }

    private void init(int mode) {
        switch (mode) {
            case 1: // completable random
                startcompleatablerandom();
                break;
            case 2: // random
                startrandom();
                break;
            case 3: // load maze
                startload();
                break;
            case 4: // preset
                startpreset();
            //case 5: // edit mode moveing to new view for save and exit button
             //   startedit();
        }
    }
    public void startcompleatablerandom()
    {

    }
    public void startrandom()
    {

    }
    public void startload()
    {

    }
    public void startpreset()
    {

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
