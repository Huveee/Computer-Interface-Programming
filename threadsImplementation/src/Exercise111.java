public class Exercise111 extends Thread{
    private int counter;
    private static int countThreads=0;
    private int threadNum= ++countThreads;

    public Exercise111(){
        System.out.println("Creating a Thread: "+threadNum);
    }

    @Override
    public void run(){

        System.out.println("Thread: "+threadNum+ "("+counter+")");
        counter--;

    }

    public static void main(String[] args) {

        for (int i=0;i<5;i++){
            Exercise111 exercise111= new Exercise111();
            exercise111.start();
            System.out.println("Thread name:"+ exercise111.getName());
            System.out.println("Thread priority: "+ exercise111.getPriority());
            System.out.println("Is the thread alive "+ exercise111.isAlive());

        }

}









}


