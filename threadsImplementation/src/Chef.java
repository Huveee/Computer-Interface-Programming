public class Chef implements Runnable {

    private String[] specialties;

    private String name;

    private ServingCounter servingCounter;


    public Chef(String[] specialties, String name, ServingCounter servingCounter) {
        this.specialties = specialties;
        this.name = name;
        this.servingCounter = servingCounter;
    }

    public void run(){

        while(true){
            String dish = specialties[(int)(Math.random()* specialties.length)];
            System.out.println("Chef"+name+ "is cooking a"+dish);



            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            servingCounter.putDish(name,dish);
        }




    }




}
