package DataPack;

public class Blob implements Comparable<Blob> {
    public int x1, y1, x2, y2;
    public int lastY, lastX1, lastX2;

    public boolean stillPresent;
    public boolean toAdd;

    public int compareTo(Blob b2) {
        int area = getWidth() * getHeight();
        int area2 = b2.getWidth() * b2.getHeight();
        if(area > area2) {
            return 1;
        }
        if(area < area2) {
            return -1;
        }
        return 0;
    }
    
    public Blob() {
        x1 = y1 = x2 = y2 = 0;
        lastY = lastX1 = lastX2 = 0;
        stillPresent = false;
        toAdd = false;
    }
    
    public int getCenterX() {
        return ((x1+x2)/2);
    }
    public int getCenterY() {
        return ((y1+y2)/2);
    }
    public int getWidth() {
        return (1+x2-x1);
    }
    public int getHeight() {
        return (1+y2-y1);
    }

    public Blob(Blob b) {
        x1 = b.x1;
        y1 = b.y1;
        x2 = b.x2;
        y2 = b.y2;
        lastY = b.lastY;
        lastX1 = b.lastX1;
        lastX2 = b.lastX2;
        stillPresent = b.stillPresent;
    }

    public boolean inRange(int sx, int ex, int sy, int ey) {
        if(x1 > sx && x2 < ex && y1 > sy && y2 > ey) {
            return true;
        }
        return false;
    }

    public boolean inRange(int sx, int ex) {
        if(x1 > sx && x2 < ex) {
            return true;
        }
        return false;
    }

    public void update(Blob b) {
        x1 = b.x1;
        y1 = b.y1;
        x2 = b.x2;
        y2 = b.y2;
        lastY = b.lastY;
        lastX1 = b.lastX1;
        lastX2 = b.lastX2;
        stillPresent = b.stillPresent;
    }

    public boolean addable(int xx1, int xx2, int y) {
        // if does not correspond to last line...
        //System.out.println("ADD ABLE METHOD 1 "+xx1+" "+xx2+" "+y);
       //  System.out.println("ADD ABLE METHOD 2 "+lastX1+" "+lastX2+" "+lastY);

        if(lastY!=(y-1)) {
            return false;
        }
        if(xx1>=lastX1 && xx1<=lastX2) {
            return true;
        }
        if(xx2>=lastX1 && xx2<=lastX2) {
            return true;
        }
        if(lastX1>=xx1 && lastX1<=xx2) {
            return true;
        }
        if(lastX2>=xx1 && lastX2<=xx2) {
            return true;
        }
        return false;
    }

    public boolean mergable(Blob b2) {
        int xx1 = b2.x1;
        int yy1 = b2.y1;
        int xx2 = b2.x2;
        int yy2 = b2.y2;
       // System.out.println("MERGABLE  METHOD !!!!!!!!!!!!!!!!!"+x1+" "+y1+" "+xx1+" "+" "+yy1+" "+xx2+" "+yy2);
       
        // rectangle overlap logic
        if(in(x1, y1, xx1, yy1, xx2, yy2)) {
            return true;
        }
        if(in(x2, y2, xx1, yy1, xx2, yy2)) {
            return true;
        }
        if(in(x1, y2, xx1, yy1, xx2, yy2)) {
            return true;
        }
        if(in(x2, y1, xx1, yy1, xx2, yy2)) {
            return true;
        }
        
        // reverse case rectagle overlap logic 
        if(in(xx1, yy1, x1, y1, x2, y2)) {
            return true;
        }
        if(in(xx2, yy2, x1, y1, x2, y2)) {
            return true;
        }
        if(in(xx2, yy1, x1, y1, x2, y2)) {
            return true;
        }
        if(in(xx1, yy2, x1, y1, x2, y2)) {
            return true;
        }
        
        return false;
    }    

    public boolean in(int x, int y, int x1, int y1, int x2, int y2) {
        if(x>=x1 && x<=x2 && y>=y1 && y<=y2) {
            return true;
        }
        return false;
    }
   
}

