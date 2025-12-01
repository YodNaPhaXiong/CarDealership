package utils;

import java.util.List;
import java.util.Optional;

public interface IFileHandler {

    public <T> Optional<T> uploadData(String filePath);

    public <T, U> List<U> parse(T data);

    <T, U> void exportVehicles(List<T> vehicleList, U dealerId);
}
