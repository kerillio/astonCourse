package Step1.module3;

public class Proxy_example {

    interface Image {
        void show();
    }

    static class RealImage implements Image{
        private String path;
        public RealImage (String path) {
            this.path = path;
            load(path);
        }

        private void load (String path) {
            System.out.println("Загружаем картинку " + path);
        }

        @Override
        public void show() {
            System.out.println("Просмотр картинки " + path);
        }
    }

    static class ProxyImage implements Image{
        private String path;
        private RealImage image;

        public ProxyImage (String path) {
            this.path = path;
        }


        @Override
        public void show() {
            if (image == null) {
                image = new RealImage(path);
            }
            image.show();
        }
    }
}

class ProxyTest {
    static void main(String[] args) {
        Proxy_example.Image image = new Proxy_example.ProxyImage("Pictures/private/");
        image.show();
    }
}
