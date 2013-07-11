import java.awt.*;

public class PrimeSquare {

    final static int size = 900;
    Boolean[][] valueArray;
    int[][] ulamArray;
    PrimeFinder primes;
    Window window;

    public static void printArray(int[][] array){
        for(int[] i : array){
            for(int j : i){
                System.out.print(j + "\t");
            }
            System.out.print("\n");
        }
        System.out.flush();
    }

    void work(){
        int newPrime;
        Point pos;
        long count = 0;
        while((newPrime = primes.getNext()) > 0 && newPrime < size*size){

            count++;
            pos = findIn2DArray(ulamArray,newPrime);
            if(pos.getX()>= valueArray.length || pos.getY() >= valueArray.length) return;
            valueArray[(int)pos.getX()][(int)pos.getY()] = true;

            if(count % 100 == 0) window.frame.repaint();
        }
    }

    void init(){
        valueArray = new Boolean[size][size];
        ulamArray = getUlamArray(size/2+1);
        primes = new PrimeFinder(size*size);
        window = new Window(size,size, Color.WHITE, Color.BLACK, "Prime Square", valueArray);
        window.frame.setLocationRelativeTo(null);
        window.frame.setVisible(true);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                valueArray[i][j] = false;
            }
        }
    }

    public static void main(String[] args) {
        PrimeSquare app= new PrimeSquare();
        app.init();
        app.work();
    }

    public static Point findIn2DArray(int[][] array, int what){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <array[i].length ; j++) {
                if(array[i][j]==what){
                    return new Point(i,j);
                }
            }
        }
        return new Point(-1,-1);
    }

    public static int[][] getUlamArray(int n){

        //init
        int[][] array = new int[2*n+1][2*n+1];
        for (int i = 0; i < 2*n+1; i++) {
            for (int j = 0; j < 2*n+1; j++) {
                     array[i][j] = 0;
            }
        }
        array[array.length/2][array.length/2] = 1;

        // upside pyramid
        for(int i = -1; i>=-n; i--){
                array[i+n][i+n] = (2*i)*(2*i) + 1;
                for(int j = 1; j <= 2*Math.abs(i); j++) array[i+n][i+n+j]= array[i+n][i+n+j-1]-1 ;
        }

        // fill top half
        for(int r = -n; r <= 0; r++) {
            for (int c = -n; c <= n; c++) {
                if (c < r)  array[r + n][c + n] = r - c + array[c + n][c + n];
                if (c > -r) array[r + n][c + n] = -(r + c) + array[n - c][c + n];
            }
        }

        //down piramid
        for(int i = 1; i<=n; i++){
            array[i+n][i+n] = (int)Math.pow(2*i+1,2);
            for(int j = 1; j <= 2*Math.abs(i); j++) array[i+n][i+n-j]= array[i+n][i+n-j+1]-1 ;
        }

        // fill bottom half
        for(int r = n; r > 0; r--){
            for (int c = -n; c <= n; c++) {
                if (c < -r)  array[r + n][c + n] = r - c + array[c + n][c + n];
                if (c > r) array[r + n][c + n] =  -(r + c) + array[n - c][c + n];
            }
        }
        return  array;
    }

}
