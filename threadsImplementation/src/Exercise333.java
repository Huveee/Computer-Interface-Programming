public class Exercise333 {

    private int counter;

    public synchronized void increment(){
        for (int i=0; i<1000; i++){
            counter++;
        }


    }

    public static void main(String[] args) {

        Exercise333 exercise333= new Exercise333();


        Thread thread1 = new Thread(()-> {

        for (int i=0;i<1000;i++){
            System.out.println("Thread 1:"+ exercise333.counter);
            exercise333.increment();


        }

        });

        Thread thread2 = new Thread(()-> {

            for (int i=0;i<1000;i++){

                System.out.println("Thread 2:"+ exercise333.counter);
                exercise333.increment();

            }
        });

        thread1.start();
        thread2.start();

        System.out.println("Final counter value: "+exercise333.counter);



    }


}
