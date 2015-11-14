package game.MindGame;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by DVitinnik on 04-Nov-15.
 */
public abstract class Level {
    protected boolean haveAddittionalBitmap;//����������, ���� �� �������������� �����������
    protected int time;//����� ��� ������ ����������� �������� ������ � �������������
    protected int mark;//������ �� ���������� �����
    protected int count;//���������� ��������
    protected int x0, y0, x1, y1;//���������� ��������������, � �������� �������� ���� ������
    protected ImageView imageView;//�������� �����������

    protected ImageView additionalImage;//�������������� ����������� (������/������������������ �����)
    protected int additionalTime; //����� ������ ��������������� ����������� � �������������

    public int getCount() {
        return count;
    }

    public int getAdditionalTime() {
        return additionalTime;
    }

    public boolean isHaveAddittionalBitmap() {
        return haveAddittionalBitmap;
    }

    public Level(ImageView imageView) {
        this.imageView = imageView;
        haveAddittionalBitmap = false;
    }

    public boolean TryClick(float x, float y) {
        return x >= x0 && x <= x1 && y >= y0 && y <= y1;
    }

    public abstract Bitmap Next();

    public abstract Bitmap getAdditionalBitmap();

    public int getMark() {
        return mark;
    }

    public int getTime() {
        return time;
    }
}
