public class YahtzeeRun {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new flowLayout());

        for(int i = 0; i<5; i++) {
            Jlabel label = new Jlabel();
            
            frame.add(label);
        }
        frame.setVisible(true);

    }
}