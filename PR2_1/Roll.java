import java.util.Random;

class Roll implemetns Runnable {
    private JLabel label;
    Random random = new Random();
    public Roll(JLabel label) {
        this.label = label;
        int i = rand.nextInt(6);
        label.setText(Integer.toString(i));
    }
    public void run() {
        int x = rand.nextInt(1000);
        while(x!=0) {
            label.setText((Integer.toString(rand.nextInt(6)));
            x--;
        }
        try {
            Thread.sleep(100);
        } catch(InterruptedExceptation ex) {
            System.out.println("this failed");
        }
    }
}