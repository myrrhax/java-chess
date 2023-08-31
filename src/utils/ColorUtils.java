package utils;

import java.awt.Color;

public class ColorUtils {
    public static final int[] BLACK_SQUARE_RGB = new int[] { 181, 136, 99 };
    public static final int[] WHITE_SQUARE_RGB = new int[] { 240, 217, 181 };
    public static final int[] SELECTED_SQUARE_COLOR = new int[] { 41, 108, 20 };
    public static final int[] POSSIBLE_MOVE_COLOR = new int[] { 103, 94, 235 };
    public static Color getColor(Colors color) {
        int[] colors;
        switch (color) {
            case BLACK_SQUARE_COLOR -> colors = BLACK_SQUARE_RGB;
            case WHITE_SQUARE_COLOR -> colors = WHITE_SQUARE_RGB;
            case SELECTED_PIECE_COLOR ->  colors = SELECTED_SQUARE_COLOR;
            case POSSIBLE_MOVES_COLOR -> colors = POSSIBLE_MOVE_COLOR;
            default -> colors = new int[] {1, 1, 1};
        }
        return new Color(colors[0], colors[1], colors[2]);
    }
}
