package pl.seremak.misc;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messenger {

    public static void success(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }

    public static void fatal(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, "Błąd!"));
    }
}
