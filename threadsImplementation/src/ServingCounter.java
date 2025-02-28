public class ServingCounter {

    private int capacity;
    private int count = 0;

    private String [] dishes;

    public ServingCounter(int capacity) {
        this.capacity = capacity;

        dishes= new String [capacity];

    }

    public synchronized void putDish(String chef, String dish){
        try {
        while(capacity<=count){

            System.out.println("Chef "+chef+"is waiting ");
            wait();

        }
            System.out.println("Chef "+chef+"is placing ");
             Thread.sleep(2000);
             dishes[count-1]=dish;
             count++;

            System.out.println("Chef "+chef+"is already placed the dish on the counters ");
            Thread.sleep(2000);

            notifyAll();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }




    }


    public synchronized void takeDish(String waiter){
        try {
            while(count<= 0){
                System.out.println("Waiter "+waiter+"is waiting ");
                wait();
            }


            System.out.println("Waiter "+waiter+"is placing the meal from the counter ");
            Thread.sleep(2000);
            count++;


            System.out.println("Waiter "+waiter+"is delivering the meal to the customers ");

            Thread.sleep(2000);

            System.out.println("Waiter "+waiter+"is already placed the dish on the counters ");
            Thread.sleep(2000);

            notifyAll();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }









}
