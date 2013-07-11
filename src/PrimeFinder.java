/**
 * Created with IntelliJ IDEA.
 * User: Ostah
 * Date: 11.07.13
 * Time: 12:18
 * To change this template use File | Settings | File Templates.
 */
public class PrimeFinder {
    Boolean[] primes;

    int lastPrime = -1;

    PrimeFinder(int size){
        primes = new Boolean[size];

        for(int i=0; i<primes.length; i++){
            primes[i] = new Boolean(true);
        }

    }

    private void invalidate(int nr){
        for(int i = 2; i*nr < primes.length ; i++){
            primes[i*nr] = false;
        }
    }

    int getNext(){
        if( lastPrime == -1){
            lastPrime = 2;
        }
        else {
            for( int i = lastPrime+1 ; i < primes.length ; i++){
                 if(primes[i]){
                     lastPrime = i;
                     break;
                 }
                if(i == primes.length-1){
                    System.out.println("End of primes");
                    return -1;
                }
            }
        }

       invalidate(lastPrime);
        return  lastPrime;
    }
}
