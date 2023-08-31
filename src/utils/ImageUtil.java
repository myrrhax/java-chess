package utils;

import game.Color;
import pieces.PieceType;

import javax.swing.*;

public final class ImageUtil {
    public static Icon getPieceIcon(PieceType pt, Color color) {
        String path = String.format("src/img/%s_%s.png", color.name().toLowerCase(), pt.name().toLowerCase());
        return new ImageIcon(path);
    }
}
