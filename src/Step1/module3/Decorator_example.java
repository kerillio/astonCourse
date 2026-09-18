package Step1.module3;

public class Decorator_example {

    interface Car {
        void draw();
    }

    static class SportCar implements Car {

        @Override
        public void draw() {
            System.out.println("it's sport car");
        }
    }

    static class UsualCar implements Car {

        @Override
        public void draw() {
            System.out.println("it's usual civil car");
        }
    }

    abstract static class CarDecorator implements Car {
        protected Car decoratedCar;
        public CarDecorator(Car car){
            this.decoratedCar = car;
        }
        public void draw (){
            decoratedCar.draw();
        }
    }

    static class BlueCarDecorator extends CarDecorator {

        public BlueCarDecorator(Car car) {
            super(car);
        }

        public void draw() {
            decoratedCar.draw();
            setColour();
        }

        private void setColour() {
            System.out.println("Colour: blue");
        }
    }
}

class DecoratorTest {
    static void main(String[] args) {
        Decorator_example.Car sportCar = new Decorator_example.SportCar();
        Decorator_example.Car civilFord = new Decorator_example.BlueCarDecorator(new Decorator_example.UsualCar());

        sportCar.draw();
        civilFord.draw();
    }
}
