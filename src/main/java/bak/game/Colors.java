package bak.game;

public class Colors {
    // Используется для изменения цвета текста при выводе
    public static final String F_RESET = "\u001B[0m";
    public static final String F_WHITE = "\u001B[30m";
    public static final String F_RED = "\u001B[31m";
    public static final String F_GREEN = "\u001B[32m";
    public static final String F_YELLOW = "\u001B[33m";
    public static final String F_BLUE = "\u001B[34m";
    public static final String F_PURPLE = "\u001B[35m";
    public static final String F_CYAN = "\u001B[36m";
    public static final String F_BLACK = "\u001B[37m";
    // Используется для цвета фона при выводе
    public static final String BG_WHITE = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_BLACK = "\u001B[47m";

    public static void printColors() {
        // Цвета текста
        System.out.print(
                F_RESET + " RESET " +
                F_WHITE + " WHITE " +
                F_RED + " RED " +
                F_GREEN + " GREEN " +
                F_YELLOW + " YELLOW " +
                F_BLUE + " BLUE " +
                F_PURPLE + " PUPRLE " +
                F_CYAN + " CYAN " +
                F_BLACK + " BLACK \n"
        );
        // Цвета фона
        System.out.print(
                BG_WHITE + " WHITEBG " +
                F_WHITE + BG_RED + " REDBG " +
                F_BLACK + BG_GREEN + " GREEBG " +
                BG_YELLOW + " YELLOWBG " +
                BG_BLUE + " BLUEBG " +
                BG_PURPLE + " PURPLEBG " +
                BG_CYAN + " CYANBG " +
                F_WHITE + BG_BLACK + " BLACKBG "
        );
        System.exit(0);
    }
}
