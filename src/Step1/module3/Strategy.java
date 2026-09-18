package Step1.module3;

import java.net.ConnectException;

public class Strategy {

    interface Messenger {
        void sendMesssage (String message);
    }

    static class Telegram implements Messenger {

        @Override
        public void sendMesssage(String message) {
            System.out.println("Sending message in telegram: " + message);
        }
    }

    static class WhatsApp implements Messenger {

        @Override
        public void sendMesssage(String message) {
            System.out.println("Sending message in WhatsApp: " + message);
        }
    }

    static class Context {
        private Messenger messenger;
        public Context (Messenger messenger) {
            this.messenger = messenger;
        }
        public void sendMessage (String message) {
            messenger.sendMesssage(message);
        }
    }


}

class StrategyTest {
    public void main(String[] args) {
        Strategy.Context context = new Strategy.Context(new Strategy.Telegram());
        context.sendMessage("Тестовое сообщение в телеграм");

        context = new Strategy.Context(new Strategy.WhatsApp());
        context.sendMessage("Тестовое сообщение в ватсап");
    }
}