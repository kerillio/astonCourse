package Step1.module3;

public class Adapter_example {

    static class Youtube {
        private String video;
        public Youtube() {video = "Me at the zoo";}
        public void watchVideo() {
            System.out.println("Watching " + video);
        }
    }

    static class Vkvideo {
        private String video;
        public Vkvideo() {video = "Students day";}
        public void watchVideo() {
            System.out.println("Watching " + video);
        }
    }

    static class YoutubeAdapter extends Youtube {
        private Vkvideo vkvideo;
        public YoutubeAdapter (Vkvideo vkvideo) {
            this.vkvideo = vkvideo;
        }

        @Override
        public void watchVideo() {
            vkvideo.watchVideo();
        }
    }
}

class AdapterTest {
    static void main(String[] args) {
        Adapter_example.Youtube youtube = new Adapter_example.Youtube();
        youtube.watchVideo();
        Adapter_example.YoutubeAdapter vkvideo = new Adapter_example.YoutubeAdapter(new Adapter_example.Vkvideo());
        vkvideo.watchVideo();
    }
}
