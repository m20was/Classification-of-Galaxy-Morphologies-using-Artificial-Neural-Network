package DataPack;


import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Ravee
 */
public class Blobs {

    public Vector<Blob> vectorBlob;
    
    public Blobs() {
        vectorBlob = new Vector<Blob>();
    }
    
    public void clear() {
        vectorBlob.clear();
    }
    
    public void sort() {
        Collections.sort(vectorBlob);
    }
    
    public void addBlob(int x1, int x2, int y) {
        boolean existingBlob = false;
        for (int i = 0; i < vectorBlob.size(); i++) {
            Blob b = vectorBlob.get(i);
            if (b.addable(x1, x2, y)) {
                //   System.out.println("ADD BLOB METHOD "+x1+"  "+x2+"  "+y);
                b.y2 = y;
                if (b.x1 > x1) {
                    b.x1 = x1;
                }
                if (b.x2 < x2) {
                    b.x2 = x2;
                }
                b.lastX1 = x1;
                b.lastX2 = x2;
                b.lastY = y;
                existingBlob = true;
                break;
            }
            
        }
        if (!existingBlob) {
            //  System.out.println("ADD ABLE METHOD *********");

            Blob b = new Blob();
            b.x1 = x1;
            b.y1 = y;
            b.x2 = x2;
            b.y2 = y;
            b.lastX1 = x1;
            b.lastX2 = x2;
            b.lastY = y;
            
            vectorBlob.add(b);
        }
    }
    
    public void findBlobs(int pixels[][], int w, int h, int color, int minWidth, int minHeight, int maxWidth, int maxHeight) {
        // detect blobs...
        clear();
        int startXX;
        for (int yy = 0; yy < h; yy++) {
            startXX = -1;
            for (int xx = 0; xx < w; xx++) {

                // if white pixel or Black pixel found
                if (pixels[yy][xx] == color) {
                    // mark start of a line.
                    
                    if (startXX == -1) {
                        startXX = xx;
                        //System.out.println("FIND BLOB  METHOD 1 "+xx);
                    }
                } else {
                    // if black found check if white was being marked...
                    if (startXX != -1) {
                        //  System.out.println("FIND BLOB METHOD 2 "+xx+" "+yy);
                        addBlob(startXX, xx - 1, yy);
                        startXX = -1;
                    }
                }
            }
            // check if there is a blob still present at the end of line.
            if (startXX != -1) {
                //   System.out.println("ADD ABLE METHOD+++++++++++++ "+yy);

                addBlob(startXX, w - 1, yy);
            }
        }
        mergeBlobs();

        // remove unwanted blobs
        for (int i = vectorBlob.size() - 1; i >= 0; i--) {
            Blob b = vectorBlob.get(i);
            if (minWidth != -1) {
                if (b.getWidth() < minWidth) {
                    vectorBlob.remove(i);
                    continue;
                }
            }
            if (minHeight != -1) {
                if (b.getHeight() < minHeight) {
                    vectorBlob.remove(i);
                    continue;
                }
            }
            if (maxWidth != -1) {
                if (b.getWidth() > maxWidth) {
                    vectorBlob.remove(i);
                    continue;
                }
            }
            if (maxHeight != -1) {
                if (b.getHeight() > maxHeight) {
                    vectorBlob.remove(i);
                    continue;
                }
            }
        }
    }
    
    public int size() {
        return vectorBlob.size();
    }
    
    public Blob get(int i) {
        return vectorBlob.get(i);
    }
    
    public void mergeBlobs() {
        boolean dirty = true;
        while (dirty) {
            dirty = false;
           // System.out.println("size: " + vectorBlob.size()+" "+vectorBlob.get(0).);
            for (int i = 0; i < vectorBlob.size() - 1; i++) {
                for (int j = i + 1; j < vectorBlob.size(); j++) {
                    Blob b1 = vectorBlob.get(i);
                    Blob b2 = vectorBlob.get(j);
                    
                    if (b1.x1 != -1 && b2.x1 != -1 && b1.mergable(b2)) {
                        dirty = true;
                        b1.x1 = Math.min(b1.x1, b2.x1);
                        b1.x2 = Math.max(b1.x2, b2.x2);
                        b1.y1 = Math.min(b1.y1, b2.y1);
                        b1.y2 = Math.max(b1.y2, b2.y2);
                        b2.x1 = -1;
                    }
                }
            }
            
            for (int i = vectorBlob.size() - 1; i >= 0; i--) {
                Blob b1 = vectorBlob.get(i);
                if (b1.x1 == -1) {
                    vectorBlob.remove(i);
                }
            }
        }
    }
}
