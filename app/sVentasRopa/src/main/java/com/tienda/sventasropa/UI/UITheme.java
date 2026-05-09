/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.sventasropa.UI;

/**
 *
 * @author marco
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class UITheme {

    public static final Color BG_DARK = new Color(18, 18, 28);

    public static final Color BG_MEDIUM = new Color(28, 28, 42);

    public static final Color BG_FIELD = new Color(40, 40, 58);

    public static final Color ACCENT_PURPLE = new Color(140, 92, 255);

    public static final Color ACCENT_GREEN = new Color(46, 204, 113);

    public static final Color ACCENT_RED = new Color(231, 76, 60);

    public static final Color TEXT_WHITE = new Color(240, 240, 250);

    public static final Color TEXT_GRAY = new Color(170, 170, 190);

    public static final Color BORDER = new Color(65, 65, 90);

    // la fuente

    private static Font pixelFont;

    private static Font loadPixelFont(float size) {

    try {

        if (pixelFont == null) {

            pixelFont = Font.createFont(
                Font.TRUETYPE_FONT,
                new java.io.File("src/fonts/PixelUI.ttf")
            );

        }

        return pixelFont.deriveFont(size);

    } catch (Exception e) {

        e.printStackTrace();

        return new Font("Segoe UI", Font.PLAIN, (int) size);

        }
    }   

    public static void apply(JInternalFrame frame) {

        frame.getContentPane().setBackground(BG_DARK);

        applyToContainer(frame.getContentPane());

    }

    private static void applyToContainer(Container container) {

        for (Component comp : container.getComponents()) {


            if (comp instanceof JPanel panel) {

                panel.setBackground(BG_DARK);

                if (panel.getBorder() instanceof TitledBorder tb) {

                    tb.setTitleColor(TEXT_GRAY);

                    tb.setBorder(BorderFactory.createLineBorder(BORDER));

                    tb.setTitleFont(loadPixelFont(14f));

                }

                applyToContainer(panel);

            }

            else if (comp instanceof JLabel label) {

                label.setForeground(TEXT_WHITE);

                label.setFont(loadPixelFont(14f));

            }

            // los text fields

            else if (comp instanceof JTextField field) {

                field.setBackground(BG_FIELD);

                field.setForeground(TEXT_WHITE);

                field.setCaretColor(TEXT_WHITE);

                field.setFont(loadPixelFont(14f));

                field.setBorder(new CompoundBorder(
                        new LineBorder(BORDER, 1, true),
                        new EmptyBorder(6, 10, 6, 10)
                ));

                if (!field.isEditable()) {

                    field.setBackground(BG_MEDIUM);

                    field.setForeground(TEXT_GRAY);

                }

            }

            else if (comp instanceof JComboBox<?> combo) {

                combo.setBackground(BG_FIELD);

                combo.setForeground(TEXT_WHITE);

                combo.setFont(loadPixelFont(14f));

                combo.setBorder(new LineBorder(BORDER, 1, true));

            }

            // botones

            else if (comp instanceof JButton btn) {

                String text = btn.getText().toLowerCase();

                if (text.contains("delete")
                        || text.contains("eliminar")
                        || text.contains("borrar")) {

                    btn.setBackground(ACCENT_RED);

                } else if (text.contains("save")
                        || text.contains("guardar")
                        || text.contains("add")
                        || text.contains("register")
                        || text.contains("update")
                        || text.contains("actualizar")
                        || text.contains("finalizar")) {

                    btn.setBackground(ACCENT_PURPLE);

                } else if (text.contains("venta")) {

                    btn.setBackground(ACCENT_GREEN);

                } else {

                    btn.setBackground(BG_FIELD);

                }

                btn.setForeground(Color.WHITE);

                btn.setFont(loadPixelFont(14f).deriveFont(Font.BOLD));

                btn.setFocusPainted(false);

                btn.setBorderPainted(false);

                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                btn.setPreferredSize(new Dimension(140, 38));

                btn.setBorder(new EmptyBorder(8, 16, 8, 16));

                btn.setOpaque(true);

                Color original = btn.getBackground();

                btn.addMouseListener(new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {

                        btn.setBackground(original.brighter());

                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {

                        btn.setBackground(original);

                    }

                });

            }

            // scroll

            else if (comp instanceof JScrollPane scroll) {

                scroll.setBorder(BorderFactory.createLineBorder(BORDER, 1));

                scroll.getViewport().setBackground(BG_MEDIUM);

                if (scroll.getViewport().getView() instanceof JTable table) {

                    applyToTable(table);

                }

            }

            else if (comp instanceof Container subContainer) {

                applyToContainer(subContainer);

            }
        }
    }

    // table

    public static void applyToTable(JTable table) {

        table.setBackground(BG_MEDIUM);

        table.setForeground(TEXT_WHITE);

        table.setFont(loadPixelFont(14f));

        table.setRowHeight(34);

        table.setGridColor(BORDER);

        table.setSelectionBackground(ACCENT_PURPLE);

        table.setSelectionForeground(Color.WHITE);

        table.setShowHorizontalLines(true);

        table.setShowVerticalLines(true);

        table.setIntercellSpacing(new Dimension(1,1));

        table.setIntercellSpacing(new Dimension(0, 4));

        table.setFillsViewportHeight(true);

        table.setRowMargin(4);

        JTableHeader header = table.getTableHeader();

        header.setBackground(BG_FIELD);

        header.setForeground(TEXT_WHITE);

        header.setFont(loadPixelFont(14f).deriveFont(Font.BOLD));

        header.setBorder(BorderFactory.createLineBorder(BORDER));

    }
}