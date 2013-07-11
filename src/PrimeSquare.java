
public class PrimeSquare {

    final static int size = 800;

    public static void printArray(int[][] array){
        for(int[] i : array){
            for(int j : i){
                System.out.print(j + "\t");
            }
            System.out.print("\n");
        }
        System.out.flush();
    }
    public static void main(String[] args) {

        Boolean[][] valueArray = new Boolean[size][size];
        PrimeFinder primes = new PrimeFinder(size*size);

        printArray(getUlamArray(3))  ;

//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//               valueArray[i][j] = false;
//            }
//        }
//
//        Window window = new Window(size,size, Color.WHITE, "Prime Square", valueArray);
//        window.frame.setVisible(true);
//
//        int newPrime = -666;
//        while((newPrime = primes.getNext()) > 0 && newPrime < size*size){
//            int x =   newPrime/size   ;
//            int y =   newPrime%size;
//
//            System.out.println("x: " + x + " y: " + y + " %: "+newPrime%size + " newprime: "+newPrime);
//            valueArray[x][y] = true;
//        }
//        window.invalidate();
//        window.validate();
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
                for(int j = 1; j <= 2*(int)Math.abs(i); j++) array[i+n][i+n+j]= array[i+n][i+n+j-1]-1 ;
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
            for(int j = 1; j <= 2*(int)Math.abs(i); j++) array[i+n][i+n-j]= array[i+n][i+n-j+1]-1 ;
        }

        // fill bottom half
        for(int r = n; r > 0; r--) {
            for (int c = -n; c <= n; c++) {
                if (c < -r)  array[r + n][c + n] = r - c + array[c + n][c + n];
                if (c > r) array[r + n][c + n] =  -(r + c) + array[n - c][c + n];
            }
        }

        return  array;
    }

}
