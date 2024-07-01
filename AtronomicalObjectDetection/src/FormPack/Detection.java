package FormPack;

import HistoryPack.HistoryDB;
import HistoryPack.HistorySingleRow;
import ANN.NeuralNetwork;
import DataPack.Blob;
import DataPack.Blobs;
import FeaturePack.SingleFeatures;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import java.util.Vector;

/**
 *
 * @author Ravee
 */
public class Detection extends javax.swing.JFrame {

    BufferedImage thumbImage, thumbImage2;
    Graphics2D graphics2D, graphics2D2;
    int w, h;
    ImageIcon ii1, ii2;
    int inPixels[][], outPixels[][];
    public int maxX, maxY, minX, minY;
    Blobs blobs;
    MainForm parent;
    public String folderPath;
    java.util.Timer timer;
    File[] listOfFiles;
    String name = "";
    Vector<String> names;
    int totalOutput;
    HistoryDB historyDB;
    String fileName = "";

    /**
     * Creates new form Demo
     */
    public Detection(MainForm parent) {
        initComponents();
        Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth() / 2, sd.height / 2 - this.getHeight() / 2);
        historyDB = new HistoryDB();
        readFrom();
        this.parent = parent;
        jComboTh.removeAllItems();
        for (int i = 0; i <= 255; i++) {
            jComboTh.addItem("" + i);
        }
        jComboTh.setSelectedIndex(40);

        jComboHeight.removeAllItems();
        jComboWidth.removeAllItems();
        for (int i = 0; i < 200; i++) {
            jComboHeight.addItem("" + i);
            jComboWidth.addItem("" + i);
        }

        jComboHeight.setSelectedIndex(40);
        jComboWidth.setSelectedIndex(40);

        blobs = new Blobs();
        names = new Vector<String>();
        names.add("Circular");
        names.add("Elliptical");
        names.add("Others");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jComboTh = new javax.swing.JComboBox();
        jComboWidth = new javax.swing.JComboBox();
        jComboHeight = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jRadioBlackBlob = new javax.swing.JRadioButton();
        jRadioWhiteBlob = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabelIn = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabelOut = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Algorithms & Operations"));

        jComboTh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboWidth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboHeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Min Width");

        jLabel3.setText("Min Height");

        jLabel4.setText("Threshold");

        jButton7.setText("Detect");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioBlackBlob);
        jRadioBlackBlob.setText("Black Blob");

        buttonGroup1.add(jRadioWhiteBlob);
        jRadioWhiteBlob.setSelected(true);
        jRadioWhiteBlob.setText("White Blob");

        jButton4.setText("LOAD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jRadioBlackBlob)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jRadioWhiteBlob))
                    .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 194, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jComboWidth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jComboHeight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboTh, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel2)
                            .add(jComboWidth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel3)
                            .add(jComboHeight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton7)
                            .add(jButton4))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jRadioBlackBlob)
                                .add(jRadioWhiteBlob))
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel4)
                                .add(jComboTh, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("                                                                       ASTRONOMICAL OBJECT DETECTION");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabelIn.setBackground(new java.awt.Color(102, 102, 102));
        jLabelIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(jLabelIn);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabelOut.setBackground(new java.awt.Color(102, 102, 102));
        jLabelOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOut.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane2.setViewportView(jLabelOut);

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 509, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel5.setForeground(new java.awt.Color(204, 0, 51));
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(45, 45, 45))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
// TODO add your handling code here:
        NeuralNetwork nn = null;
        try {

            ObjectInputStream out = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir") + "\\train.dat")));
            nn = (NeuralNetwork) out.readObject();
            out.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        int col, r, g, b, gs;
        int th = jComboTh.getSelectedIndex();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                col = inPixels[y][x];

                b = col & 0xff;
                g = (col >> 8) & 0xff;
                r = (col >> 16) & 0xff;

                gs = (r + g + b) / 3;
                if (gs < th) {
                    gs = 0;
                } else {
                    gs = 255;
                }
                outPixels[y][x] = (gs | (gs << 8) | (gs << 16));
                thumbImage2.setRGB(x, y, outPixels[y][x]);
            }
        }

        // call to find blob
        if (jRadioWhiteBlob.isSelected()) {
            blobs.findBlobs(outPixels, w, h, 0xFFFFFF, jComboWidth.getSelectedIndex(), jComboHeight.getSelectedIndex(), -1, -1);
        } else {
            blobs.findBlobs(outPixels, w, h, 0, jComboWidth.getSelectedIndex(), jComboHeight.getSelectedIndex(), -1, -1);
        }

        System.out.println("Total Blobs Detected: " + blobs.size());
        readFrom();
        int cntE = 0;
        int cntC = 0;
        int cntO = 0;

        for (int i = 0; i < blobs.size(); i++) {
            SingleFeatures sf = new SingleFeatures();
            Blob bl = blobs.get(i);
            if (bl.x1 == -1) {
                continue;
            }
            int currW = bl.getWidth();
            int currH = bl.getHeight();

            int blackCnt = 0;
            int whiteCnt = 0;

            for (int yy = bl.y1; yy < bl.y1 + currH; yy++) {
                for (int xx = bl.x1; xx < bl.x1 + currW; xx++) {
                    col = outPixels[yy][xx] & 0xff;
                    //  System.out.println("COL: "+col);
                    if (col == 0) {
                        blackCnt++;
                    } else {
                        whiteCnt++;
                    }

                }

            }

            double aspectRatio = 0.0;
            if (currH > currW) {
                aspectRatio = (currW * 1.0 / currH);
            } else {
                aspectRatio = (currH * 1.0 / currW);
            }
            sf.blackCnt = blackCnt;
            sf.whiteCnt = whiteCnt;
            sf.whitePerCnt = ((whiteCnt * 1.0) / (currW * currH));
            sf.blackPerCnt = ((blackCnt * 1.0) / (currW * currH));
            System.out.println("Features:  " + get3Decimal(sf.aspectRatio) + "    " + get3Decimal(sf.blackPerCnt) + "    " + get3Decimal(sf.whitePerCnt) + "   " + whiteCnt + "    " + blackCnt);

            nn.setInputs(new double[]{aspectRatio, sf.blackPerCnt, sf.whitePerCnt});
            double curr_out_D[] = nn.runNetwork();
            int output = convertToInt(curr_out_D);
            //  System.out.println("OP: " + output + "  (" + tr_out[i][output] + ")");
            String out = "";
            String type = "";
            if (output == 0) {
                out = "Circular";
                cntC++;
                type = "0000C"+cntC;
            } else if (output == 1) {
                out = "Elliptical";
                cntE++;
                type = "0000E" + cntE;
            } else {
                out = "Others";
                cntO++;
                type = "0000O" + cntO;
            }
System.out.println("Type:"+type);
            graphics2D2.setColor(Color.red);
            graphics2D2.drawRect(bl.x1, bl.y1, bl.getWidth(), bl.getHeight());
            //System.out.println(" " + out.toCharArray().length);
            graphics2D2.drawString(out, bl.x1, bl.y1);
            //HistorySingleRow sr = new HistorySingleRow();
            HistorySingleRow sr= new HistorySingleRow();
            sr.singleRow.add("" + historyDB.rows.size());
            sr.singleRow.add(fileName);
            sr.singleRow.add("" + blobs.size());
            sr.singleRow.add(type);
            sr.singleRow.add(out);
            sr.singleRow.add("" + bl.getCenterX());
            sr.singleRow.add("" + bl.getCenterY());
            sr.singleRow.add("" + h + "X" + w);
            sr.singleRow.add("" + th);
            sr.singleRow.add("" + jComboHeight.getSelectedIndex());
            sr.singleRow.add("" + jComboWidth.getSelectedIndex());
            historyDB.rows.add(sr);
            writeTo();
            readFrom();
            //jComboWidth.getSelectedIndex(), jComboHeight.getSelectedIndex()

        }
        jLabelOut.repaint();
        jLabel5.setText("");

        //fileName = "";
        saveTofile();

    }//GEN-LAST:event_jButton7ActionPerformed

    void saveTofile() {

        try {
            FileWriter fw = new FileWriter(new File(System.getProperty("user.dir") + "\\history.csv"));
            fw.write("\n\r");

            fw.write("Index No,File Name,NO.Of objects,Object ID,Galaxy Type,X,Y,Image Size,Th,Min H,Min W" + "\n\r");
            fw.write("\n\r");

            for (int i = 0; i < historyDB.rows.size(); i++) {
                String out = "";
                HistorySingleRow sr = historyDB.rows.get(i);
                for (int j = 0; j < sr.singleRow.size(); j++) {
                    out += sr.singleRow.get(j) + ",";
                }

                fw.write(out + "\n\r");
                fw.write("\n\r");
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
        setVisible(false);
        parent.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }

    double get3Decimal(double decimal) {

        int d = (int) (decimal * 1000);
        double dd = (d * 1.0) / 1000;
        return dd;
    }

    public int convertToInt(double outD[]) {
        int outI[] = new int[outD.length];
        double max = 0.0;
        int index = 0;
        for (int i = 0; i < outD.length; i++) {
            if (outD[i] > max) {
                max = outD[i];
                index = i;
            }
        }
        return index;
    }

    public double[][] fillInputFeature() {
        double ip[][] = new double[parent.db.featureSet.size()][totalOutput];
        int rowIndex = 0;
        //System.out.println("Char Size: " + parent.cs.charList.size());
        for (int i = 0; i < parent.db.featureSet.size(); i++) {
            SingleFeatures sc = parent.db.featureSet.get(i);
            ip[i][0] = sc.aspectRatio;
            ip[i][1] = sc.blackPerCnt;
            ip[i][2] = sc.whitePerCnt;
        }
        return ip;

    }

    public double[][] fillOutputFeature() {
        double op[][] = new double[parent.db.featureSet.size()][totalOutput];
        for (int i = 0; i < parent.db.featureSet.size(); i++) {
            for (int j = 0; j < totalOutput; j++) {
                op[i][j] = 0;
            }
        }

        for (int i = 0; i < parent.db.featureSet.size(); i++) {
            SingleFeatures sf = parent.db.featureSet.get(i);
            op[i][sf.output] = 1;
        }

        return op;
    }

    void writeTo() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir") + "\\history.dat")));
            out.writeObject(historyDB);
            out.close();
        } catch (Exception e) {

            System.out.println("Error:" + e);
            e.printStackTrace();
        }
    }

    void readFrom() {
        try {
            ObjectInputStream out = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir") + "\\history.dat")));
            historyDB = (HistoryDB) out.readObject();
            out.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
            writeTo();

        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
        String fname;
        FileDialog fd = new FileDialog(this, "Select File To Open", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null) {
            return;
        }
        fileName="";
        
        fileName = fd.getFile();
        fname = fd.getDirectory() + fileName;

        try {
            Image image = Toolkit.getDefaultToolkit().getImage(fname);
            MediaTracker mediaTracker = new MediaTracker(new Container());
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
            System.out.println("Input Image Read!");
            w = image.getWidth(null);
            h = image.getHeight(null);

            thumbImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            thumbImage2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            graphics2D = thumbImage.createGraphics();
            graphics2D2 = thumbImage2.createGraphics();
            graphics2D.drawImage(image, 0, 0, w, h, null);

            inPixels = new int[h][w];
            outPixels = new int[h][w];

            for (int yy = 0; yy < h; yy++) {
                for (int xx = 0; xx < w; xx++) {
                    inPixels[yy][xx] = thumbImage.getRGB(xx, yy) & 0xffffff;
                    outPixels[yy][xx] = 0;
                }
            }

            jLabelIn.setIcon(new ImageIcon(thumbImage));
            jLabelOut.setIcon(new ImageIcon(thumbImage2));

        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }

        int col, r, g, b, gs;
        int totalPix = h * w;
        int totalWhite = 0;
        int totalBlack = 0;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                col = inPixels[y][x];

                b = col & 0xff;
                g = (col >> 8) & 0xff;
                r = (col >> 16) & 0xff;

                gs = (r + g + b) / 3;
                if (gs < 40) {
                    totalBlack++;
                    gs = 0;
                } else {
                    totalWhite++;
                    gs = 255;
                }

            }
        }
        double perBlack = (totalBlack * 1.0 * 100) / totalPix;
        double perWhite = (totalWhite * 1.0 * 100) / totalPix;

        System.out.println("::  " + perBlack + "  " + perWhite);
        if (perBlack > 80) {
            jLabel5.setText("Loaded Image looks Galaxy Image");
        } else {
            jLabel5.setText("Loaded Image may not be Galaxy Image");

        }


    }//GEN-LAST:event_jButton4ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboHeight;
    private javax.swing.JComboBox jComboTh;
    private javax.swing.JComboBox jComboWidth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelIn;
    private javax.swing.JLabel jLabelOut;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioBlackBlob;
    private javax.swing.JRadioButton jRadioWhiteBlob;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
