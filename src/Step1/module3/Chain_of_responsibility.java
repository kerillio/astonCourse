package Step1.module3;

public class Chain_of_responsibility {

    interface Support {
        void setNext (Support support);
        void tryHelp(int difficulty);
    }

    static class JuniorSupport implements Support {
        private static final int MAXDIFFICULT = 10;
        private Support nextLevel;



        @Override
        public void setNext(Support next) {
            this.nextLevel = next;
        }

        @Override
        public void tryHelp(int difficulty) {
            System.out.print("Вызван младший сотрудник поддержки. ");
            if (difficulty <= MAXDIFFICULT) {
                System.out.println("Сотрудник смог решить проблему. ");
            } else if (nextLevel != null) {
                System.out.println("Проблема слишком сложная. Проблема передается выше. ");
                nextLevel.tryHelp(difficulty);
            } else {
                System.out.println("Проблема слишком сложная, и решить ее некому.");
            }

        }
    }

    static class MiddleSupport implements Support {

        private static final int MAXDIFFICULT = 25;
        private Support nextLevel;

        @Override
        public void setNext(Support next) {
            this.nextLevel = next;
        }

        @Override
        public void tryHelp(int difficulty) {
            System.out.print("Вызван старший сотрудник поддержки. ");
            if (difficulty <= MAXDIFFICULT) {
                System.out.println("Сотрудник смог решить проблему. ");
            } else if (nextLevel != null) {
                System.out.println("Проблема слишком сложная. Проблема передается выше. ");
                nextLevel.tryHelp(difficulty);
            } else {
                System.out.println("Проблема слишком сложная, и решить ее некому.");
            }

        }
    }

}

class ChainTest {
    public static void main (String[] args) {

        Chain_of_responsibility.Support juniorSupport = new Chain_of_responsibility.JuniorSupport();
        Chain_of_responsibility.Support middleSupport = new Chain_of_responsibility.MiddleSupport();

        juniorSupport.setNext(middleSupport);
        juniorSupport.tryHelp(9);

        System.out.println();
        juniorSupport.tryHelp(24);

        System.out.println();
        juniorSupport.tryHelp(30);

    }
}
