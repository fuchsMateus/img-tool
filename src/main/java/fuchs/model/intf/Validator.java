package fuchs.model.intf;

public interface Validator<T> {
    public boolean isValid(T value);
}
