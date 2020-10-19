package Piece;

public class GameState {

    public enum GameColor {
        WHITE,
        BLACK
    }

    static final int N = 8;

    GameColor colorWhoMove = GameColor.WHITE;

    Figure[][] A;

    public GameState() {

        A = new Figure[N][N];
        // расстановка

        for (int i = 0; i < N; ++i) {

            for (int j = 0; j < N; j++) {

                A[i][j] = null;
            }
        }

        for (int i = 0; i < N; ++i) {
            A[1][i] = new Pawn(GameColor.WHITE);
            A[N - 2][i] = new Pawn(GameColor.BLACK);
        }

        A[0][0] = new Rook(GameColor.WHITE);
        A[0][N - 1] = new Rook(GameColor.WHITE);
        A[N - 1][0] = new Rook(GameColor.BLACK);
        A[N - 1][N - 1] = new Rook(GameColor.BLACK);

        A[0][1] = new Knight(GameColor.WHITE);
        A[0][N - 2] = new Knight(GameColor.WHITE);
        A[N - 1][1] = new Knight(GameColor.BLACK);
        A[N - 1][N - 2] = new Knight(GameColor.BLACK);

        A[0][2] = new Bishop(GameColor.WHITE);
        A[0][N - 3] = new Bishop(GameColor.WHITE);
        A[N - 1][2] = new Bishop(GameColor.BLACK);
        A[N - 1][N - 3] = new Bishop(GameColor.BLACK);

        A[0][4] = new King(GameColor.WHITE);
        A[N - 1][4] = new King(GameColor.BLACK);
        A[0][3] = new Queen(GameColor.WHITE);
        A[N - 1][3] = new Queen(GameColor.BLACK);
    }

    public Figure at(int x, int y) {

        return A[y][x];
    }

    static boolean isCheckFor(GameState gs, GameColor kingColor){

        int kx = -1;
        int ky = -1;

        int kx2 = -1;
        int ky2 = -1;

        for(int x = 0; x < N; ++x){
            for(int y = 0; y < N; ++y){

                if(gs.at(x, y)!=null && gs.at(x,y).color==kingColor && gs.at(x,y).getChar()=='♔'){

                    kx = x;
                    ky = y;
                }
                if(gs.at(x, y)!=null && gs.at(x,y).color!=kingColor && gs.at(x,y).getChar()=='♔'){

                    kx2 = x;
                    ky2 = y;
                }
            }
        }

        // check to right
        for(int i = kx+1; i < N; ++i){

            if(gs.at(i, ky)==null){
                continue;
            }

            // кто-то
            if(gs.at(i, ky).color!=kingColor && (gs.at(i, ky).getChar()=='♕' || gs.at(i, ky).getChar()=='R')){
                return true;
            }
            else{
                break;
            }
        }
        // check to left
        for(int i = kx-1; i >= 0; --i){

            if(gs.at(i, ky)==null){
                continue;
            }
            if(gs.at(i, ky)!=null && gs.at(i, ky).color!=kingColor && (gs.at(i, ky).getChar()=='♕' || gs.at(i, ky).getChar()=='R')){
                return true;
            }
        }
        // check to up
        for(int i = ky+1; i < N; ++i){

            if(gs.at(kx, i)==null){
                continue;
            }
            if(gs.at(kx, i).color!=kingColor && (gs.at(kx, i).getChar()=='♕' || gs.at(kx, i).getChar()=='R')){

                return true;
            }
            else{
                break;
            }
        }
        // check to down
        for(int i = ky-1; i >= 0; --i){

            if(gs.at(kx, i)==null){
                continue;
            }
            if(gs.at(kx, i).color!=kingColor && (gs.at(kx, i).getChar()=='♕' || gs.at(kx, i).getChar()=='R')){
                return true;
            }
            else{
                break;
            }
        }

        // check up-right
        for(int k = 1; kx+k<N && ky+k<N; k++){

            if(gs.at(kx+k, ky+k)==null){
                continue;
            }
            if(gs.at(kx+k, ky+k).color!=kingColor && (gs.at(kx+k, ky+k).getChar()=='♗' || gs.at(kx+k, ky+k).getChar()=='♕' ||
                    kingColor==GameColor.WHITE && k==1 && gs.at(kx+k,ky+k).getChar()=='p'))
            {
                return true;
            }
            else{
                break;
            }
        }

        // check up-left

        for(int k = 1; kx-k >= 0 && ky+k<N; k++){

            if(gs.at(kx-k, ky+k)==null){
                continue;
            }
            if(gs.at(kx-k, ky+k).color!=kingColor && (gs.at(kx-k, ky+k).getChar()=='♗' || gs.at(kx-k, ky+k).getChar()=='♕' ||
                    kingColor==GameColor.WHITE && k==1 && gs.at(kx-k,ky+k).getChar()=='p'))
            {
                return true;
            }
            else{
                break;
            }
        }

        // check down-right
        for(int k = 1; kx+k<N && ky-k>=0; k++){

            if(gs.at(kx+k, ky-k)==null){
                continue;
            }
            if(gs.at(kx+k, ky-k).color!=kingColor && (gs.at(kx+k, ky-k).getChar()=='♗' || gs.at(kx+k, ky-k).getChar()=='♕' ||
                    kingColor==GameColor.BLACK && k==1 && gs.at(kx+k,ky-k).getChar()=='p'))
            {
                return true;
            }
            else{
                break;
            }
        }
        // check down-right
        for(int k = 1; kx-k<N && ky-k>=0; k++){

            if(gs.at(kx-k, ky-k)==null){
                continue;
            }
            if(gs.at(kx-k, ky-k).color!=kingColor && (gs.at(kx-k, ky-k).getChar()=='♗' || gs.at(kx-k, ky-k).getChar()=='♕' ||
                    kingColor==GameColor.BLACK && k==1 && gs.at(kx-k,ky-k).getChar()=='p'))
            {
                return true;
            }
            else{
                break;
            }
        }

        for(int dx = -2; dx <= 2; dx++){

            if(dx==0)continue;

            int ady = 0;
            if(Math.abs(dx)==1){
                ady = 2;
            }
            else{
                ady = 1;
            }

            for(int dy = -ady; dy <= ady; dy += 2*ady){

                int x2 = kx+dx;
                int y2 = ky+dy;

                if(x2 < 0 || x2 >= N || y2 < 0 || y2 >= N){
                    continue;
                }
                else{

                    if(gs.at(x2, y2)==null)continue;
                    if(gs.at(x2, y2).color!=kingColor && gs.at(x2, y2).getChar()=='N'){
                        return true;
                    }
                }
            }
        }

        if(Math.abs(kx-kx2)<=1 && Math.abs(ky-ky2)<=1){

            // два короля рядом - нельзя!
            return true;
        }

        return false;
    }

    public boolean isCorrectMove(int x, int y, int x2, int y2){

        if(at(x, y)==null) return false;

        GameColor aColor = at(x,y).color;

        if(at(x2, y2)==null) {

            if (at(x, y).checkMove(this, x, y, x2, y2)) {

                // проверим, что после этого нет шаха самому себе.

                A[y2][x2] = A[y][x];
                A[y][x] = null;

                if (isCheckFor(this, aColor)) {
                    // ход невозможен

                    A[y][x] = A[y2][x2];
                    A[y2][x2] = null;
                    return false;
                } else {

                    A[y][x] = A[y2][x2];
                    A[y2][x2] = null;
                    return true;
                }
            }
            else{
                return false;
            }
        }
        else if(at(x2,y2).color != aColor){

            if(at(x,y).checkEat(this, x, y, x2, y2)){

                Figure enemyFig = A[y2][x2];

                A[y2][x2] = A[y][x];
                A[y][x] = null;

                if(isCheckFor(this, aColor)){
                    // ход невозможен

                    A[y][x] = A[y2][x2];
                    A[y2][x2] = enemyFig;
                    return  false;
                }
                else{
                    A[y][x] = A[y2][x2];
                    A[y2][x2] = enemyFig;
                    return true;
                }
            }
            else{

                return false;
            }
        }
        else{
            return false;
        }
    }

    /*
    GameColor enemyColor(Figure anyFigure) {
        if (anyFigure.color == GameColor.BLACK) {
            return GameColor.WHITE;
        } else {
            return GameColor.BLACK;
        }

    }
     */

    public void print() {

        int cnt = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {

                if (A[i][j] == null) {
                    System.out.format("\t");
                } else {

                    System.out.format("%c" + "\t", A[i][j].getChar());

                }


            }
            System.out.format("\n");
        }
        System.out.format("---------------------------------\n");


    }

    public void move(int x, int y, int x2, int y2){

        if(at(x,y)==null){
            System.exit(1);
        }

        if(at(x,y).color!=colorWhoMove){
            System.exit(1);
        }

        if (!isCorrectMove(x, y, x2, y2)) {
            System.out.format("Not a correct move!\n");
            System.exit(1);
        }

        A[y2][x2] = A[y][x];
        A[y][x] = null;

        if(colorWhoMove==GameColor.WHITE){
            colorWhoMove=GameColor.BLACK;
        }
        else{
            colorWhoMove=GameColor.WHITE;
        }
    }
}
