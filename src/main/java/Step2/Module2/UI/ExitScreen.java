package Step2.Module2.UI;

import Step2.Module2.DAO.HibernateSessionFactoryUtil;

public class ExitScreen implements Screen {

    @Override
    public Screen show() {
        HibernateSessionFactoryUtil.shutdown();
        return null;
    }
}
