package antes.domain;

/**
 * Representa la solicitud HTTP entrante (POST /atenciones) con token, datos y respuesta.
 */
public class Request {

    private String token;
    private String data;
    private User user;
    private String respuesta;

    public Request(String token, String data) {
        this(token, data, null);
    }

    public Request(String token, String data, User user) {
        this.token = token;
        this.data = data;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Request{" +
                "token='" + token + '\'' +
                ", data='" + data + '\'' +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }
}
