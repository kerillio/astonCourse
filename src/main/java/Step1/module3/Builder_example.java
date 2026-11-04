package Step1.module3;

public class Builder_example {

    static class House {
        public void buildBase() {
            System.out.println("Ставим фундамент");
        }

        public void buildWalls() {
            System.out.println("Возводим стены");
        }

        public void setRoof(Roof roof) {
            System.out.println("Ставим крышу");
            System.out.println("Теперь у дома " + roof.getRoofType() + " крыша");
        }
    }

    interface Roof {
        String getRoofType();
    }

    static class FlatRoof implements Roof {

        @Override
        public String getRoofType() {
            return "плоская";
        }
    }

    static class SingleRoof implements Roof {

        @Override
        public String getRoofType() {
            return "односкатная";
        }
    }

    static class DoubleRoof implements Roof {

        @Override
        public String getRoofType() {
            return "двухскатная";
        }
    }

    abstract static class Builder {
        private House house;
        public abstract House buildHouse();
    }

    static class OneBuilderImpl extends Builder {

        private final House house;

        public OneBuilderImpl() {
            house = new House();
        }

        @Override
        public House buildHouse() {
            house.buildBase();
            house.buildWalls();
            house.setRoof(new FlatRoof());
            return house;
        }
    }

    static class TwoBuilderImpl extends Builder {

        private final House house;

        public TwoBuilderImpl() {
            house = new House();
        }

        @Override
        public House buildHouse() {
            house.buildBase();
            house.buildWalls();
            house.setRoof(new SingleRoof());
            return house;
        }
    }

    static class  ThreeBuilderImpl extends Builder {
        private final House house;

        public ThreeBuilderImpl() {
            house = new House();
        }

        @Override
        public House buildHouse() {
            house.buildBase();
            house.buildWalls();
            house.setRoof(new DoubleRoof());
            return house;
        }
    }

    static class Build {
        private Builder builder;

        public Build (int i) {
            switch (i) {
                case 1 : builder = new OneBuilderImpl();
                    break;
                case 2 : builder = new TwoBuilderImpl();
                    break;
                case 3 : builder = new ThreeBuilderImpl();
                    break;
            }
        }
        public House buildHouse() {
            return builder.buildHouse();
        }
    }

}

class BuilderTest {
    public static void main (String[] args) {
        Builder_example.Build build = new Builder_example.Build(1);
        build.buildHouse();
    }
}

